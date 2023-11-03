package com.example.onlinecashiersystem.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product", schema = "public")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_plane_id", referencedColumnName = "product_plane_id")
    private ProductPlane productPlane;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_category_id", referencedColumnName = "category_id")
    private Category category;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ProductPlane getProductPlane() {
        return productPlane;
    }

    public void setProductPlane(ProductPlane productPlane) {
        this.productPlane = productPlane;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
