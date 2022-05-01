package com.example.a20521566seminar10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static final String FAKE_STORE_API = "https://fakestoreapi.com/";
    Retrofit retrofit;
    ListView listView;
    List<String> categories;
    private ArrayAdapter<String> categoriesAdapter;
    private StoreClient storeClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(FAKE_STORE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        listView = findViewById(R.id.categories);
        initCategories();


        storeClient = retrofit.create(StoreClient.class);

        storeClient.getCategories().enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                    return;
                }
                categories.addAll(response.body());
                categoriesAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed, Error: " +  t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initCategories() {
        categories = new ArrayList<>();
        categoriesAdapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        listView.setAdapter(categoriesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailStore.class);
                intent.putExtra("category", categories.get(i));
                startActivity(intent);
            }
        });
    }
}