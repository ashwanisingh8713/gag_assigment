package com.gag.assignment.repository;


import com.gag.assignment.db.CartDao;
import com.gag.assignment.db.ProductDB;
import com.gag.assignment.db.CartTable;
import com.gag.assignment.model.ProductAttribute;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ashwanisingh on 26/06/22.
 */

public class DataManager {

    public static void updateCartTable(ProductAttribute product) {
        Observable.just(product)
                .subscribeOn(Schedulers.io())
                .map(data -> {
                    CartDao dao = ProductDB.getInstance().productDao();
                    // If Selected Count is Zero the it should be removed from CartTable
                    if (data.getSelectedCount() == 0) {
                        deleteProduct(data.getId());
                    } else {
                        // If Selected Count is not Zero then it should update CartTable
                        int update = dao.updateArticleIds(data.getSelectedCount(), data.getId());
                        // If Selected Count is not Updated then it should be inserted into CartTable
                        if (update == 0) {
                            insertProduct(data);
                        }
                    }
                    return true;
                })
                .subscribe();


    }

    public static void insertProduct(ProductAttribute productAttribute) {
        Observable.just(productAttribute)
                .subscribeOn(Schedulers.io())
                .map(data -> {
                    CartTable product = new CartTable(data);
                    ProductDB.getInstance().productDao().insertProduct(product);
                    return true;
                })
                .subscribe();
    }

    public static void deleteProduct(String productId) {
        Observable.just(productId)
                .subscribeOn(Schedulers.io())
                .map(id -> {
                    int deleteCount = ProductDB.getInstance().productDao().delete(id);
                    return deleteCount == 1;
                })
                .subscribe();
    }

    public static Observable<List<CartTable>> getCartAllProduct() {
        return ProductDB.getInstance().productDao().getCartAllProduct()
                .subscribeOn(Schedulers.io());
    }

    public static Observable<ArrayList<ProductAttribute>> combineProductCartData(List<ProductAttribute> products) {
        return Observable.zip(Observable.just(products), getCartAllProduct(), (allProducts, cartProducts) -> {
                    for (ProductAttribute product : allProducts) {
                        String prodId = product.getId();
                        product.setSelectedCount(0); // Reset All selected count, because all count was not updating
                        for (CartTable cart : cartProducts) {
                            String cartProdId = cart.getProductId();
                            if (prodId.equals(cartProdId)) {
                                product.setSelectedCount(cart.getSelectedCount());
                            }
                        }
                    }
                    return new ArrayList<>(allProducts);
                }).subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> new ArrayList<>());
    }

    public static void removeAllCart() {
        Observable.just("logout")
                .subscribeOn(Schedulers.io())
                .map(v->{
                    ProductDB.getInstance().productDao().deleteAll();
                    return true;
                }).subscribe();
    }


}
