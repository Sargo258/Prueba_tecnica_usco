package com.api.mall.models;

import jakarta.persistence.*;



@Entity
@Table(name = "sub_categories")
public class SubCategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Enumerated(EnumType.STRING)
    private ESubCategory nombre;

    @ManyToOne
    private CategoryModel categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ESubCategory getNombre() {
        return nombre;
    }

    public void setNombre(ESubCategory nombre) {
        this.nombre = nombre;
    }

    public CategoryModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoryModel categoria) {
        this.categoria = categoria;
    }
}
