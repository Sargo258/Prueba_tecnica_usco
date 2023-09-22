package com.api.mall.models;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ECategory getNombre() {
        return nombre;
    }

    public void setNombre(ECategory nombre) {
        this.nombre = nombre;
    }

    @Enumerated(EnumType.STRING)
    private ECategory nombre;



}
