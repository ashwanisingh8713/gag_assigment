package com.gag.assignment.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.gag.assignment.model.ProductAttribute;

import java.util.Objects;

@Entity
public class CartTable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    public CartTable(ProductAttribute product) {
        this.productId = product.getId();
        this.product = product;
        this.selectedCount = product.getSelectedCount();
    }

    private String productId;
    private ProductAttribute product;
    private int selectedCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductAttribute getProduct() {
        return product;
    }

    public void setProduct(ProductAttribute product) {
        this.product = product;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
        this.product.setSelectedCount(selectedCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        String productId = (String) o;
        return productId.equals(this.productId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }
}
