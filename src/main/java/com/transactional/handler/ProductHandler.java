package com.transactional.handler;

import com.transactional.entity.Product;
import com.transactional.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductHandler {

    @Autowired
    private ProductRepo productRepo;


    @Transactional(propagation = Propagation.REQUIRED)
    public Product updateProdcutInventory(Product product)
    {
        if(product.getPrice()>50000)
        {
            throw new RuntimeException("DB crashed");
        }
        return productRepo.save(product);
    }

    public Product getProductById(int id)
    {
          return productRepo.findById(id).orElseThrow(()->new RuntimeException("nor found"));
    }
}
