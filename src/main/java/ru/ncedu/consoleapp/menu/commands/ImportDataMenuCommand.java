package ru.ncedu.consoleapp.menu.commands;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import ru.ncedu.consoleapp.models.Category;
import ru.ncedu.consoleapp.models.Product;
import ru.ncedu.consoleapp.repositories.CategoriesRepository;
import ru.ncedu.consoleapp.repositories.ProductsRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.io.IOException;
import java.util.*;

public class ImportDataMenuCommand implements Command {

    private static ImportDataMenuCommand instance;

    private ImportDataMenuCommand() {
    }

    public static ImportDataMenuCommand getInstance() {
        if (instance == null) {
            instance = new ImportDataMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute(){

        System.out.println("Start the import");

        Map<Long, String> categoriesIdAndLink  = getCategoriesIdAndLink();
        getProducts(categoriesIdAndLink);

        System.out.println("Categories and products have imported successfully");
        IOUtils.waitForEnter();
        return MainMenuCommand.getInstance();
    }

    private void getProducts(Map<Long, String> categoriesIdAndLink) {
        for (Long category:categoriesIdAndLink.keySet()) {
            setProduct(category, categoriesIdAndLink.get(category));
        }
    }

    private void setProduct(long categoryID, String categoryLink) {
        String url = "https://market.yandex.ru" + categoryLink;
        String notAddedProducts = "Все товары";

        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .get();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        Element bodyElement = document.body();
        Elements subCategoriesElements = bodyElement.select("div.main").select("div._1YdrMWBuYy");

        Iterator<Element> subCategoriesIterator = subCategoriesElements.iterator();

        ArrayList<String> productsName = new ArrayList<>();

        while (subCategoriesIterator.hasNext()) {
            Element subCategoryElement = subCategoriesIterator.next();
            Elements productsElements = subCategoryElement.select("div._1Y6X2G3jjK").select("a");
            Iterator<Element> productsElementIterator = productsElements.iterator();
            while (productsElementIterator.hasNext()) {
                Element productElement = productsElementIterator.next();
                Node nodeList = productElement.childNode(0);
                String productName = nodeList.toString();
                if(!productName.equals(notAddedProducts)){
                    productsName.add(productName);
                    Product product = new Product();
                    product.setName(productName);
                    product.setCategoryId(categoryID);
                    product.setDescription("-");
                    ProductsRepository.getInstance().add(product);
                }
            }
        }
    }

    private Map<Long,String> getCategoriesIdAndLink()  {
        Map<Long,String> categoriesIdAndLink = new HashMap<>();

        String url = "https://market.yandex.ru";
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .referrer("http://www.google.com")
                    .get();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Elements elements = document.select("div.main div.n-w-tabs-lazy.i-bem");
        String elementWithCategories = elements.attr("data-bem");

        JSONObject jsonObject = new JSONObject(elementWithCategories);

        JSONArray nodesArray = jsonObject.getJSONObject("n-w-tabs-lazy")
                .getJSONObject("n-w-tabs")
                .getJSONArray("nodes");

        Category category;

        for (int i = 1; i < nodesArray.length() - 1; i++) {
            JSONObject jsonObject1 = nodesArray.getJSONObject(i)
                    .getJSONArray("data")
                    .getJSONObject(0);
            String fullName = jsonObject1.getString("fullName");
            String link = jsonObject1.getString("link");

            category = new Category();
            category.setName(fullName);
            category.setDescription("-");
            category = CategoriesRepository.getInstance().add(category);
            categoriesIdAndLink.put(category.getId(),link);
        }

        return categoriesIdAndLink;
    }
    
}
