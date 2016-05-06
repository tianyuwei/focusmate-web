package com.focusmate.datasource.repository;

import com.focusmate.datasource.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
