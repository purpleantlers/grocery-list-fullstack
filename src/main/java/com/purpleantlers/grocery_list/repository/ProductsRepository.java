package com.purpleantlers.grocery_list.repository;

import com.purpleantlers.grocery_list.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
}
