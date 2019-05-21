/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductDaoFileImpl implements ProductDao {

    Map<String, Product> allProducts = new HashMap<>();

    public static final String PRODUCT_FILE = "Products.txt";
    public static final String DELIMITER = ",";

    private void loadProduct() {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));

            String currentLine;
            String[] currentTokens;

            while (sc.hasNextLine()) {
                currentLine = sc.nextLine();
                currentTokens = currentLine.split(DELIMITER);

                String productName = currentTokens[0];
                BigDecimal costSqFt = new BigDecimal(currentTokens[1]);
                BigDecimal laborSqFt = new BigDecimal(currentTokens[2]);

                Product currentProduct = new Product(productName, costSqFt, laborSqFt);

                allProducts.put(productName, currentProduct);
            }
            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Was not able load product info in memory");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        loadProduct();
        return new ArrayList<>(allProducts.values());
    }

    @Override
    public Product getProduct(String name) {

        //        Product prod = null;
//        List<Product> productList = getAllProducts();
//        for (Product p : productList) {
//            if (p.getProductName().equalsIgnoreCase(name)) {
//                prod = p;
//            }
//        }
//        return prod;

        String prodFirstLetter = name.substring(0, 1); // Seperates first letter from remaining of word
        prodFirstLetter  = prodFirstLetter.toUpperCase(); 
        
        String restOfLetters = name.substring(1); //takes the rest of the letters after the 1st letter
        restOfLetters = restOfLetters.toLowerCase();
        
        String wholeProductName = prodFirstLetter + restOfLetters; // whole product name will be capital 1st letter 
        //and rest of letters are set to lower case
        
        
        
        return allProducts.get(wholeProductName);// uses hashamp to get product by its name

    }

}
