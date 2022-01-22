package com.example.excontroller.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface extensionRepository extends JpaRepository<extension, Long>{
    boolean existsByText(String text);
}
