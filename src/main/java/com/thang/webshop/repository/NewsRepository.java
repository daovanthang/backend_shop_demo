package com.thang.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thang.webshop.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
