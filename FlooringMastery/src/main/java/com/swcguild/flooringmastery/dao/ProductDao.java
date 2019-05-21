/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author zissah
 */
public interface ProductDao {

    List<Product> getAllProducts();

    Product getProduct(String name);

}
