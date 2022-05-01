package com.example.a20521566seminar10;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        Log.d("hihi", "ProductAdapter: " + productList);
    }

    private Context context;
    private List<Product> productList;
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if(product == null) return;
        Glide.with(context).load(product.getImage()).into(holder.productImg);
        holder.productName.setText(product.getTitle());
        holder.productPrice.setText("$" + product.getPrice());
        Log.d("hihi", "onBindViewHolder: " + product.getPrice());
    }

    @Override
    public int getItemCount() {
        if(productList == null)
            return 0;
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImg;
        private TextView productName;
        private TextView productPrice;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.product_img);
            productName = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.price);
        }
    }
}
