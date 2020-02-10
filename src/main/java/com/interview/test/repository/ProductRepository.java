package com.interview.test.repository;

import org.springframework.data.repository.CrudRepository;

import com.interview.test.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
