package com.thang.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thang.webshop.entity.CustomerInfor;

@Repository
public interface CustomerInforRepository extends JpaRepository<CustomerInfor, Long> {
}
