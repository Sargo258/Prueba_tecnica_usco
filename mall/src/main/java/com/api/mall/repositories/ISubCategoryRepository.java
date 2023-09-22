package com.api.mall.repositories;

import com.api.mall.models.CategoryModel;
import com.api.mall.models.SubCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubCategoryRepository extends JpaRepository<SubCategoryModel, Long> {
    SubCategoryModel findByNombre(String nombre);
    List<SubCategoryModel> findByCategoria(CategoryModel categoria);
}
