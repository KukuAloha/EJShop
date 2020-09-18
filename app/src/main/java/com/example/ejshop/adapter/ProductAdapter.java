package com.example.ejshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejshop.R;
import com.example.ejshop.model.Product;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ProductAdapter extends FirestoreRecyclerAdapter<Product,ProductAdapter.ProductHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductAdapter(@NonNull FirestoreRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductAdapter.ProductHolder holder, int position, @NonNull Product model) {
        holder.txtName.setText(model.getName());
        Picasso.get().load(model.getImg()).into(holder.imgProd);
    }

    @NonNull
    @Override
    public ProductAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,
                parent, false);
        return new ProductHolder(view);
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView imgProd;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            imgProd = itemView.findViewById(R.id.imgProd);
        }
    }
}
