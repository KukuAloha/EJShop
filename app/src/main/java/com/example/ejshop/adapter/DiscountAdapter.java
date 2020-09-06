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
import com.example.ejshop.model.Discount;
import com.example.ejshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder> {

    Context context;
    ArrayList<Discount> discounts;

    public DiscountAdapter(ArrayList<Discount> discounts, Context context){
        this.context = context;
        this.discounts = discounts;
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_item, parent, false);
        return new DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        //Picasso.Builder builder = new Picasso.Builder(context);
        //builder.build().load(products.get(position).getImgUrl()).into(holder.imgProd);
        Picasso.get().load(discounts.get(position).getImgDis()).into(holder.imgDis);
    }

    @Override
    public int getItemCount() {
        return discounts.size();
    }

    class DiscountViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDis;

        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDis = itemView.findViewById(R.id.imgDis);
        }
    }

}
