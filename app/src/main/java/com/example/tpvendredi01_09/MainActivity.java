package com.example.tpvendredi01_09;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tpvendredi01_09.data.FakeStoreAPI;
import com.example.tpvendredi01_09.entity.Cart;
import com.example.tpvendredi01_09.entity.Product;
import com.example.tpvendredi01_09.util.RetrofitClient;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout= findViewById(R.id.productlist);

        Cart cart = new Cart();
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gsonBack = new Gson();
        String jsonBack = gsonBack.toJson(cart);
        prefsEditor.putString("cartList", jsonBack);
        prefsEditor.commit();

        // Obtenez une instance de FakeStoreAPI
        FakeStoreAPI apiService = RetrofitClient.getRetrofitInstance().create(FakeStoreAPI.class);

        // Effectuez l'appel API
        Call<List<Product>> call = apiService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    products = response.body();
                    System.out.println(products);
                    products.forEach(p -> {
                        Button button = new Button(MainActivity.this);
                        button.setText(p.getTitle());
                        button.setId(products.indexOf(p));
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this, ProductDetail.class);
                                Product product = products.get(view.getId());
                                intent.putExtra("product",product);
                                startActivity(intent);
                            }
                        });
                        linearLayout.addView(button);
                    });

                } else {
                    Toast.makeText(MainActivity.this,"Error Network",Toast.LENGTH_SHORT).show();
                }

                Button cartButton = findViewById(R.id.btncart);
                cartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,CartView.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Internal Error",Toast.LENGTH_SHORT).show();
            }
        });

    }
}