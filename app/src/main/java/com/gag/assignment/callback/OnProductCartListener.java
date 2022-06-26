package com.gag.assignment.callback;

import com.gag.assignment.model.ProductAttribute;

public interface OnProductCartListener extends CartListener {
    void updateUIOnAddBtn(int currentIndex, ProductAttribute product);
}
