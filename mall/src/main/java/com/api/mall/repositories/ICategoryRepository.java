package com.api.mall.repositories;

import com.api.mall.models.CategoryModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryModel, Long> {
}
