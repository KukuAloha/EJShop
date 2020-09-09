package com.example.ejshop.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejshop.R;
import com.example.ejshop.model.NewProducts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.NewProductViewHolder> {

    Context context;
    ArrayList<NewProducts> newProducts;

    public NewProductsAdapter(ArrayList<NewProducts> newProducts, Context context){
        this.context = context;
        this.newProducts = newProducts;
    }


    @NonNull
    @Override
    public NewProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new NewProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductViewHolder holder, int position) {
        holder.txtName.setText(newProducts.get(position).getName());
        Picasso.get().load(newProducts.get(position).getImgUrl()).into(holder.imgProd);
    }

    @Override
    public int getItemCount() {
        return newProducts.size();
    }

    public class NewProductViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        ImageView imgProd;

        public NewProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtNewName);
            imgProd = itemView.findViewById(R.id.imgNewProd);
        }
    }
}
