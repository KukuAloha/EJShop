package com.example.ejshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejshop.adapter.DiscountAdapter;
import com.example.ejshop.adapter.ProductAdapter;
import com.example.ejshop.model.Discount;
import com.example.ejshop.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProduct, recyclerViewDiscount;

    private FirebaseDatabase database;
    private DatabaseReference databaseReferenceProduct, databaseReferenceDiscount;

    ArrayList<Product> productList;
    ArrayList<Discount> discountList;

    private ProductAdapter productAdapter;
    private DiscountAdapter discountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readProductData();
        readDiscountData();
    }

    public void readProductData(){
        productList = new ArrayList<Product>();
        recyclerViewProduct = findViewById(R.id.productRV);
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(this));
        databaseReferenceProduct = FirebaseDatabase.getInstance().getReference().child("product");
        databaseReferenceProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    productList.add(product);
                }
                productAdapter = new ProductAdapter(productList, MainActivity.this);
                recyclerViewProduct.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Smth is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void readDiscountData(){
        discountList = new ArrayList<Discount>();
        recyclerViewDiscount = findViewById(R.id.discountRV);
        recyclerViewDiscount.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        databaseReferenceDiscount = FirebaseDatabase.getInstance().getReference().child("discount");
        databaseReferenceDiscount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Discount discount = dataSnapshot.getValue(Discount.class);
                    discountList.add(discount);
                }
                discountAdapter = new DiscountAdapter(discountList, MainActivity.this);
                recyclerViewDiscount.setAdapter(discountAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Smth is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

}