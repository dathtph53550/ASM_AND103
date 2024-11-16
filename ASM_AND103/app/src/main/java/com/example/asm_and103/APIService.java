package com.example.asm_and103;

import com.example.asm_and103.Models.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    String DOMAIN = "http://10.24.61.185:3000";

    @GET("/list")
    Call<List<ProductModel>> getProducts();

    @POST("/add")
    Call<List<ProductModel>> addProduct(@Body ProductModel product);

    @DELETE("/delete/{id}")
    Call<List<ProductModel>> deleteProduct(@Path("id") String _id);

    @PUT("/update/{id}")
    Call<List<ProductModel>> updateProduct(@Path("id") String _id, @Body ProductModel product);
}
