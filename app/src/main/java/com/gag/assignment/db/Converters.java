package com.gag.assignment.db;

import androidx.room.TypeConverter;

import com.gag.assignment.model.ProductAttribute;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converters {

    @TypeConverter
    public static String productToString(ProductAttribute product) {
        Gson gson = new Gson();
        String json = gson.toJson(product);
        return json;
    }

    @TypeConverter
    public static ProductAttribute stringToProduct(String value) {
        Type listType = new TypeToken<ProductAttribute>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
}
