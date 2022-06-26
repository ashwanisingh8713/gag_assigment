package com.gag.assignment.repository;

import com.gag.assignment.model.ProductAttribute;

import java.util.List;

/**
 * Created by ashwanisingh on 25/06/22.
 */

public interface OnResponse {

    void onSuccess(List<ProductAttribute> productList);
    void onFailed(Throwable e);
    void onCompleted();

}
