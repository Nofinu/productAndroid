package com.example.tpvendredi01_09;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tpvendredi01_09.entity.Cart;
import com.example.tpvendredi01_09.entity.Product;
import com.google.gson.Gson;

import java.util.List;

public class ProductDetail extends AppCompatActivity {

    private TextView titleText;
    private TextView descriptionText;
    private TextView priceText;
    private TextView quantityText;
    private Button btnAdd;
    private Button btnBack;

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);

        titleText = findViewById(R.id.title);
        descriptionText = findViewById(R.id.description);
        quantityText = findViewById(R.id.quantity);
        priceText = findViewById(R.id.price);

        btnAdd = findViewById(R.id.btnadd);
        btnBack = findViewById(R.id.btnback);

        Intent intent = getIntent();

        if(intent != null){
            product = (Product) intent.getSerializableExtra("product");
            titleText.setText(product.getTitle());
            descriptionText.setText(product.getDescription());
            quantityText.setText(product.getQuantity()!=null? product.getQuantity().toString() : "error");
            priceText.setText(product.getPrice()!=null?  product.getPrice().toString() : "error");
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                String json = mPrefs.getString("cartList", "");
                Cart cart = gson.fromJson(json, Cart.class);

                if(cart != null){
                    cart.addProducts(product);
                }
                else{
                    cart = new Cart();
                    cart.addProducts(product);
                }

                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gsonBack = new Gson();
                String jsonBack = gsonBack.toJson(cart);
                prefsEditor.putString("cartList", jsonBack);
                prefsEditor.commit();
            }
        });
    }
}