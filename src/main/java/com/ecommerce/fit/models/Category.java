package com.ecommerce.fit.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 3, max = 50, message = "O nome da categoria deve ter entre 3 e 50 caracteres.")
    private String name;

    @Column(name = "description", length = 255)
    private String description;
    
    @OneToMany(mappedBy = "cateogry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
}
