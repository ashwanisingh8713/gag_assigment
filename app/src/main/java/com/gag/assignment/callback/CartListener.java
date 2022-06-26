package com.gag.assignment.callback;

import com.gag.assignment.model.ProductAttribute;

import java.util.ArrayList;

public interface CartListener {
    void updateDataOnProductSelection(int currentIndex, ArrayList<ProductAttribute> products);
}
