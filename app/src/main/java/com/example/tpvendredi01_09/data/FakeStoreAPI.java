package com.example.tpvendredi01_09.data;

import com.example.tpvendredi01_09.entity.Product;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface FakeStoreAPI {
    @GET("products")
    Call<List<Product>> getProducts();

    // Ajoutez d'autres méthodes pour les autres points d'API
}
