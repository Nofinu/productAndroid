package com.example.tpvendredi01_09;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tpvendredi01_09.entity.Cart;
import com.google.gson.Gson;

public class CartView extends AppCompatActivity {
    private Cart cart;
    private LinearLayout linearLayout;

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);

        linearLayout = findViewById(R.id.cartList);
        btnBack = findViewById(R.id.cartbackbtn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Gson gson = new Gson();
        String json = mPrefs.getString("cartList", "");
        Cart cart = gson.fromJson(json, Cart.class);

        if(cart != null){
            cart.getProducts().forEach(p ->{
                Button button = new Button(CartView.this);
                button.setText(p.getTitle());
                linearLayout.addView(button);
            });
        }


    }
}