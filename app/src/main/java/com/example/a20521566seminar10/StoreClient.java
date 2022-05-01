package com.example.a20521566seminar10;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface StoreClient {
    @GET("products/categories")
    Call<ArrayList<String>> getCategories();

    @GET("products/category/{category}")
    Call<ArrayList<Product>> getProducts(
            @Path("category") String category
    );
}
