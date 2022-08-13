package com.javatechie.spring.data.jpa.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.data.jpa.api.model.Product;



public interface ProductRepository extends JpaRepository<Product,Integer> {

}
