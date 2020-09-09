package com.example.ejshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ejshop.adapter.DiscountAdapter;
import com.example.ejshop.adapter.NewProductsAdapter;
import com.example.ejshop.adapter.PopularProductAdapter;
import com.example.ejshop.model.Discount;
import com.example.ejshop.model.NewProducts;
import com.example.ejshop.model.PopularProduct;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProduct, recyclerViewDiscount, recyclerViewNewProduct;

    private FirebaseDatabase database;
    private DatabaseReference databaseReferenceProduct, databaseReferenceDiscount;

    ArrayList<PopularProduct> popularProductList;
    ArrayList<Discount> discountList;
    ArrayList<NewProducts> newProductsList;

    private PopularProductAdapter popularProductAdapter;
    private DiscountAdapter discountAdapter;
    private NewProducts newProductsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readProductData();
        readDiscountData();
        //readNewProductData();
    }

    public void readProductData(){
        popularProductList = new ArrayList<PopularProduct>();
        recyclerViewProduct = findViewById(R.id.productRV);
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(this));
        databaseReferenceProduct = FirebaseDatabase.getInstance().getReference().child("popularProducts");
        databaseReferenceProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    PopularProduct popularProduct = dataSnapshot.getValue(PopularProduct.class);
                    popularProductList.add(popularProduct);
                }
                popularProductAdapter = new PopularProductAdapter(popularProductList, MainActivity.this);
                recyclerViewProduct.setAdapter(popularProductAdapter);
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

    public void readNewProductData(){
        newProductsList = new ArrayList<NewProducts>();
        recyclerViewNewProduct = findViewById(R.id.newProductRV);
        //recyclerViewNewProduct.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        databaseReferenceDiscount = FirebaseDatabase.getInstance().getReference().child("newProducts");
        databaseReferenceDiscount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    NewProducts newProducts = dataSnapshot.getValue(NewProducts.class);
                    newProductsList.add(newProducts);
                }
                newProductsAdapter = new NewProductsAdapter(newProductsList,MainActivity.this);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Smth is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

}