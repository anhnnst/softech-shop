/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.shop.controllers;

import com.softech.shop.model.Product;
import com.softech.shop.services.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @GetMapping("")
    public String index(ModelMap modelMap){
        List<Product> list = productService.findAll();
        
        modelMap.addAttribute("products", list);
        
        return "products/index";
    }
    
    @GetMapping("/new")
    public String newProduct(ModelMap modelMap){
        Product product = new Product();
        
        modelMap.addAttribute("product", product);
        
        return "products/addOrUpdate";
    }
    
    @GetMapping("/edit/{id}")
    public String editProduct(ModelMap modelMap,
            @PathVariable String id){
        Optional<Product> product = productService.findById(Long.parseLong(id));
        
        if (product != null){
            modelMap.addAttribute("product", product.get());
        }else{
            return "redirect:/products/find";
        }
        
        return "products/addOrUpdate";
    }
    
    @GetMapping("/view/{id}")
    public String viewProduct(ModelMap modelMap,
            @PathVariable String id){
        Optional<Product> product = productService.findById(Long.parseLong(id));
        
        if (product != null){
            modelMap.addAttribute("product", product.get());
        }else{
            return "redirect:/products/find";
        }
        
        return "products/view";
    }
    
    @PostMapping("/saveOrUpdate")
    public String index(ModelMap modelMap, 
            @ModelAttribute @Validated Product product,
            BindingResult result){
        if (result.hasErrors()){
            return "/products/addOrUpdate";
        }
        
        Product savedProduct = productService.save(product);
        
        return "redirect:/products/find";
    }
    
    //@GetMapping(value={"/find/{name}","/find"})
    @GetMapping(value="/find")
    public String findProducts(ModelMap modelMap, 
            @RequestParam(required = false) String name){
        List<Product> list = null;
        if (name != null)
            list = productService.findByName(name);
        else
            list = productService.findAll();
        
        modelMap.addAttribute("products", list);
        
        return "products/find";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(ModelMap modelMap, 
            @PathVariable String id){
        
        productService.deleteById(Long.parseLong(id));
        
        return "redirect:/products/find";
    }
}
