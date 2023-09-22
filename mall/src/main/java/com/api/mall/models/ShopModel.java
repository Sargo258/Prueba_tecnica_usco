package com.api.mall.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

@Entity
@Table(name = "mall")
public class ShopModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column
    private String nombreNegocio;

    @NotBlank
    @Size(max = 100)
    @Column
    private String ubicacion;

    @NotBlank
    @Size(max = 100)
    @Column
    private String representanteLegal;

    @NotBlank
    @Size(max = 100)
    @Column
    private String telefonoContacto;

    @Enumerated(EnumType.STRING)
    private EEstado estado;

    @OneToOne()
    @JoinColumn(name = "subcategory_id")
    private SubCategoryModel subCategoria;

    @OneToOne(mappedBy = "shopModel", cascade = CascadeType.ALL)
    private UserModel userModel;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public EEstado getEstado() {
        return estado;
    }

    public void setEstado(EEstado estado) {
        this.estado = estado;
    }

    public SubCategoryModel getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoryModel subCategoria) {
        this.subCategoria = subCategoria;
    }
}

