package ru.ncedu.consoleapp.menu.commands.offering.utils;

import ru.ncedu.consoleapp.menu.commands.office.AddOfficeCommand;
import ru.ncedu.consoleapp.menu.commands.office.utils.OfficeCommandsUtils;
import ru.ncedu.consoleapp.menu.commands.products.AddProductCommand;
import ru.ncedu.consoleapp.menu.commands.products.utils.ProductCommandsUtils;
import ru.ncedu.consoleapp.models.Offering;
import ru.ncedu.consoleapp.models.Office;
import ru.ncedu.consoleapp.models.Product;
import ru.ncedu.consoleapp.repositories.OfficeRepository;
import ru.ncedu.consoleapp.repositories.ProductsRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;
import java.util.Scanner;

public class OfferingCommandsUtils {
    public static void printOffer(Offering city) {
        System.out.println(" Id = " + city.getId());
        System.out.println(" Offering price = " + city.getOfferingPrice());
        System.out.println(" Product id = " + city.getProductId());
        System.out.println(" Office id = " + city.getOfficeId());
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

    public static Double getDoubleFromConsole(Scanner scanner, String key){
        IOUtils.printSeparator();
        System.out.println("Enter " + key + ":");
        IOUtils.printPrompt();

        double newInt = 0;

        String box = scanner.nextLine();
        if (box.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println(key + " is empty");
            newInt = getDoubleFromConsole(scanner, key);
        }else{
            try {
                newInt = Double.parseDouble(box);
            }catch (NumberFormatException e){
                System.out.println(key + " must de a number");
                newInt = getDoubleFromConsole(scanner, key);
            }
        }

        return newInt;
    }

    public static long getProductId(Scanner scanner){

        long productId;
        List<Product> products = ProductsRepository.getInstance().get();

        IOUtils.printSeparator();

        if (products.isEmpty()) {
            System.out.println("No products have been found");
            IOUtils.waitForEnter();
            System.out.println("Create new product");
            AddProductCommand.getInstance().execute();
            return getProductId(scanner);
        }
        for (Product product : products) {
            ProductCommandsUtils.printProduct(product);
        }
        System.out.println("Enter ID of product:");
        IOUtils.printPrompt();
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
                System.out.println("Enter 1 for create new product.");
                System.out.println("Enter anything for view products again.");
                IOUtils.printPrompt();
                String userChoice = scanner.nextLine();
                if(userChoice.equals("1")){
                    AddProductCommand.getInstance().execute();
                    productId = getProductId(scanner);
                }else {
                    productId = getProductId(scanner);
                }
            }
            else{
                productId = product.getId();
            }
        }
        return productId;
    }

    public static long getOfficeId(Scanner scanner){

        long officeId;
        List<Office> offices = OfficeRepository.getInstance().get();

        IOUtils.printSeparator();

        if (offices.isEmpty()) {
            System.out.println("No offices have been found");
            IOUtils.waitForEnter();
            System.out.println("Create new offices");
            AddOfficeCommand.getInstance().execute();
            return getOfficeId(scanner);
        }
        for (Office office : offices) {
            OfficeCommandsUtils.printOffice(office);
        }
        System.out.println("Enter ID of office:");
        IOUtils.printPrompt();
        String officeStringId = scanner.nextLine();
        if(officeStringId.isEmpty()){
            IOUtils.printSeparator();
            System.out.println("ID can't be empty");
            officeId = getProductId(scanner);
        }else {
            try{
                officeId = Long.parseLong(officeStringId);
            }
            catch(NumberFormatException e){
                System.out.println("ID must be a number");
                officeId = getProductId(scanner);
            }
            Product product = ProductsRepository.getInstance().get(officeId);
            if (product == null) {
                IOUtils.printSeparator();
                System.out.println("No office with such ID have been found");
                System.out.println("Enter 1 for create new office.");
                System.out.println("Enter anything for view offices again.");
                IOUtils.printPrompt();
                String userChoice = scanner.nextLine();
                if(userChoice.equals("1")){
                    AddOfficeCommand.getInstance().execute();
                    officeId = getProductId(scanner);
                }else {
                    officeId = getProductId(scanner);
                }
            }
            else{
                officeId = product.getId();
            }
        }
        return officeId;
    }
}
