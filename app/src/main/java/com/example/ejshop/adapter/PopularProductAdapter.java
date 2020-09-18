package com.example.ejshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejshop.R;
import com.example.ejshop.model.PopularProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.ProductViewHolder> {

    Context context;
    ArrayList<PopularProduct> popularProducts;

    public PopularProductAdapter(ArrayList<PopularProduct> popularProducts, Context context){
        this.context = context;
        this.popularProducts = popularProducts;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.txtName.setText(popularProducts.get(position).getName());
        Picasso.get().load(popularProducts.get(position).getImgUrl()).into(holder.imgProd);
    }

    @Override
    public int getItemCount() {
        return popularProducts.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        ImageView imgProd;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            imgProd = itemView.findViewById(R.id.imgProd);
        }
    }

}
