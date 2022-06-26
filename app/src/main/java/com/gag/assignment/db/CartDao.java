package com.gag.assignment.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ashwanisingh on 26/06/22.
 */

@Dao
public interface CartDao {

    @Insert
    void insertProduct(CartTable product);

    @Query("SELECT * from CartTable")
    Observable<List<CartTable>> getCartAllProduct();

    @Query("UPDATE CartTable SET selectedCount = :selectedCount WHERE productId = :productId")
    int updateArticleIds(int selectedCount, String productId);

    @Query("DELETE FROM CartTable WHERE productId = :productId")
    int delete(String productId);

    @Query("DELETE FROM CartTable")
    void deleteAll();
}
