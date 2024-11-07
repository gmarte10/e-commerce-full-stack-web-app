package com.giancarlos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product_to_category")
public class ProductToCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ptc_id")
    private int id;

    @Column(name="product_id")
    private int productId;

    @Column(name="category_id")
    private int categoryId;
}
