package com.example.a20521566seminar10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailStore extends AppCompatActivity {
    private static final String FAKE_STORE_API = "https://fakestoreapi.com/";
    private String category;
    private List<Product> products;
    private ProductAdapter productAdapter;
    private RecyclerView productRecycleView;
    private StoreClient storeClient;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);
        productRecycleView = findViewById(R.id.product_list);
        products = new ArrayList<>();
        getData();
        initProductRecycleView();
        initRetrofit();
        getProducts();
    }

    private void getProducts() {
        Call<ArrayList<Product>> call = storeClient.getProducts(category);

        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DetailStore.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                    return;
                }
                products.addAll(response.body());
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Log.d("hihi", "onFailure: " + t.toString());
                Toast.makeText(DetailStore.this, "Failed, Error: " +  t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(FAKE_STORE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        storeClient = retrofit.create(StoreClient.class);
    }

    private void initProductRecycleView() {
        products = new ArrayList<>();
        productAdapter = new ProductAdapter(this, products);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        productRecycleView.setLayoutManager(gridLayoutManager);
        productRecycleView.setAdapter(productAdapter);
    }

    private void getData() {
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        TextView title = findViewById(R.id.category);
        title.setText(category);
    }


}