/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.shop.services.impl;

import com.softech.shop.model.Product;
import com.softech.shop.repositories.ProductRepository;
import com.softech.shop.services.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> findAll(){
        return (List<Product>) productRepository.findAll();
    }
    public Product save(Product p){
        return productRepository.save(p);
    }
    public Optional< Product> findById(Long id){
        return productRepository.findById(id);
    }
    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
    public List<Product> findByName(String name){
        return productRepository.findProductsByNameLike("%"+name+"%");
    }
}
