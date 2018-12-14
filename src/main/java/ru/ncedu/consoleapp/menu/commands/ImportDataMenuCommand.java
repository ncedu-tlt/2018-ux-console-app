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
            getProduct(category, categoriesIdAndLink.get(category));
        }
    }

    private void getProduct(long categoryID, String categoryLink) {

        String url = "https://market.yandex.ru" + categoryLink;

        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element bodyElement = document.body();

        Elements productElements = bodyElement
                .select("div.n-snippet-cell2.i-bem.b-zone." +
                        "b-spy-visible.n-snippet-cell2_type_product");

        Iterator<Element> productsIterator = productElements.iterator();

        while(productsIterator.hasNext()){
            Element productElement = productsIterator.next();
            Element elementWithProductName = productElement.select("div.n-snippet-cell2__title a").first();
            String productName = elementWithProductName.attr("title");
            Product product = new Product();
            product.setName(productName);
            product.setCategoryId(categoryID);
            product.setDescription("-");
            ProductsRepository.getInstance().add(product);
        }
    }

    private Map<Long,String> getCategoriesIdAndLink(){
        ArrayList<String> mainCategoriesLinks = getMainCategoriesLinks();

        Map<Long, String> categoriesIdAndLinks = new HashMap<>();
        for (String mainCategoryLink: mainCategoriesLinks) {
            String url = "https://market.yandex.ru" + mainCategoryLink;
            String notAddedCategories = "Все товары";

            Document document = null;
            try {
                document = Jsoup.connect(url)
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Element bodyElement = document.body();
            Elements subCategoriesElements = bodyElement.select("div.main").select("div._1YdrMWBuYy");

            Iterator<Element> subCategoriesIterator = subCategoriesElements.iterator();

            while (subCategoriesIterator.hasNext()) {
                Element subCategoryElement = subCategoriesIterator.next();
                Elements categoryElements = subCategoryElement.select("div._1Y6X2G3jjK").select("a");
                Iterator<Element> categoriesElementIterator = categoryElements.iterator();
                while (categoriesElementIterator.hasNext()) {
                    Element categoryElement = categoriesElementIterator.next();
                    String link = categoryElement.attr("href");
                    Node nodeList = categoryElement.childNode(0);
                    String categoryName = nodeList.toString();
                    if (!categoryName.equals(notAddedCategories)) {
                        Category category = new Category();
                        category.setName(categoryName);
                        category.setDescription("-");
                        category = CategoriesRepository.getInstance().add(category);
                        categoriesIdAndLinks.put(category.getId(),link);
                    }
                }
            }
        }
        return categoriesIdAndLinks;
    }

    private ArrayList<String> getMainCategoriesLinks() {
        ArrayList<String> mainCategoriesLinks = new ArrayList<>();

        String url = "https://market.yandex.ru";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
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

        for (int i = 1; i < nodesArray.length() - 1; i++) {
            JSONObject jsonObject1 = nodesArray.getJSONObject(i)
                    .getJSONArray("data")
                    .getJSONObject(0);
            String link = jsonObject1.getString("link");
            mainCategoriesLinks.add(link);
        }
        return  mainCategoriesLinks;
    }
}
