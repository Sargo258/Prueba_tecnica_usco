package com.api.mall.controllers;

import com.api.mall.models.CategoryModel;
import com.api.mall.models.SubCategoryModel;
import com.api.mall.repositories.ICategoryRepository;
import com.api.mall.repositories.ISubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ISubCategoryRepository subCategoryRepository;

    @GetMapping
    public List<Map<String, Object>> getCategoriesWithSubCategories() {
        List<CategoryModel> categories = categoryRepository.findAll();
        List<Map<String, Object>> categoryList = new ArrayList<>();

        for (CategoryModel category : categories) {
            List<Map<String, Object>> subCategories = subCategoryRepository.findByCategoria(category)
                    .stream()
                    .map(subCategory -> {
                        Map<String, Object> subCategoryInfo = new HashMap<>();
                        subCategoryInfo.put("id", subCategory.getId());
                        subCategoryInfo.put("nombre", subCategory.getNombre().name());
                        return subCategoryInfo;
                    })
                    .collect(Collectors.toList());

            Map<String, Object> categoryInfo = new HashMap<>();
            categoryInfo.put("id", category.getId());
            categoryInfo.put("subCategories", subCategories);

            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put(category.getNombre().name(), categoryInfo);
            categoryList.add(categoryMap);
        }
        return categoryList;
    }






}



