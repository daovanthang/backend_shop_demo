package com.thang.webshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thang.webshop.entity.Category;
import com.thang.webshop.entity.Product;
import com.thang.webshop.entity.type.EnumConstants.ProductStatus;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	public Optional<Product> findByNameSeo(String nameSeo);
	public List<Product> findByState(ProductStatus state);
	public List<Product> findByCategory(Category category);
	public List<Product> findByNameContaining(String name);
}
