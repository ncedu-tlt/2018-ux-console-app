package ru.ncedu.consoleapp.menu.commands.offering.utils;

import ru.ncedu.consoleapp.menu.commands.products.utils.ProductCommandsUtils;
import ru.ncedu.consoleapp.models.Offering;
import ru.ncedu.consoleapp.models.Product;
import ru.ncedu.consoleapp.repositories.ProductsRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;
import java.util.Scanner;

public class OfferingCommandsUtils {
    public static void printOffer(Offering city) {
        System.out.println(" Id = " + city.getId());
        System.out.println(" OfferingPrice = " + city.getOfferingPrice());
        System.out.println(" Product id = " + city.getProductId());
        System.out.println(" Office id = " + city.getOfficeId());
        System.out.println(" Description = " + city.getDescription());
    }
    public static String getStringFromConsole(Scanner scanner, String key){
        IOUtils.printSeparator();
        System.out.println("Enter " + key + ":");
        IOUtils.printPrompt();

        String box = scanner.nextLine();
        if (box.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println(key + " is empty");
            box = getStringFromConsole(scanner, key);
        }

        return box;
    }

    private static long createProduct(Scanner scanner){
        System.out.println("CREATE NEW PRODUCT");
        Product newProduct = new Product();
        newProduct.setName(ProductCommandsUtils.getName(scanner));
        long categoryId = ProductCommandsUtils.getCategoryId(scanner);
        while (categoryId == -1){
            categoryId = ProductCommandsUtils.getCategoryId(scanner);
        }
        newProduct.setCategoryId(categoryId);
        newProduct.setDescription(ProductCommandsUtils.getDescription(scanner));
        IOUtils.printSeparator();
        System.out.println("End create product");
        return ProductsRepository.getInstance().add(newProduct).getId();
    }

    public static long getProductId(Scanner scanner){

        long productId;
        List<Product> products = ProductsRepository.getInstance().get();

        IOUtils.printSeparator();

        if (products.isEmpty()) {
            System.out.println("No products have been found");
            IOUtils.waitForEnter();
            return createProduct(scanner);
        }
        for (Product product : products) {
            ProductCommandsUtils.printProduct(product);
        }
        System.out.println("Enter ID of product:");
        String productStringId = scanner.nextLine();
        if(productStringId.isEmpty()){
            IOUtils.printSeparator();
            System.out.println("ID can't be empty");
            productId = getProductId(scanner);
        }else {
            try{
                productId = Long.parseLong(productStringId);
            }
            catch(NumberFormatException e){
                System.out.println("ID must be a number");
                productId = getProductId(scanner);
            }
            Product product = ProductsRepository.getInstance().get(productId);
            if (product == null) {
                IOUtils.printSeparator();
                System.out.println("No products with such ID have been found");
                return createProduct(scanner);
            }
            else{
                productId = product.getId();
            }
        }
        return productId;
    }
}
