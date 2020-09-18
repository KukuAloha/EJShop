package com.example.ejshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ejshop.adapter.DiscountAdapter;
import com.example.ejshop.adapter.NewProductsAdapter;
import com.example.ejshop.adapter.PopularProductAdapter;
import com.example.ejshop.adapter.ProductAdapter;
import com.example.ejshop.model.Discount;
import com.example.ejshop.model.NewProducts;
import com.example.ejshop.model.PopularProduct;
import com.example.ejshop.model.Product;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProduct, recyclerViewDiscount, recyclerViewNewProduct;

    private FirebaseDatabase database;
    private DatabaseReference databaseReferenceProduct, databaseReferenceDiscount;
    private FirebaseFirestore db;
    private CollectionReference productReference;

    ArrayList<PopularProduct> popularProductList;
    ArrayList<Discount> discountList;
    ArrayList<NewProducts> newProductsList;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;

    private PopularProductAdapter popularProductAdapter;
    private DiscountAdapter discountAdapter;
    private NewProductsAdapter newProductsAdapter;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        readFirestoreData();
        //readProductData();
        //readDiscountData();
        //readNewProductData();
    }


    public void readFirestoreData() {
        db = FirebaseFirestore.getInstance();
        productReference = db.collection("products");
        //Query query = db.collection("");
        Query query = productReference.orderBy("name", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Product> options = new FirestoreRecyclerOptions.Builder<Product>()
                .setQuery(query, Product.class)
                .build();
        productAdapter = new ProductAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.productRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        productAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        productAdapter.stopListening();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        recyclerViewNewProduct.setLayoutManager(new LinearLayoutManager(this));
        databaseReferenceDiscount = FirebaseDatabase.getInstance().getReference().child("newProducts");
        databaseReferenceDiscount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    NewProducts newProducts = dataSnapshot.getValue(NewProducts.class);
                    newProductsList.add(newProducts);
                }
                newProductsAdapter = new NewProductsAdapter(newProductsList,MainActivity.this);
                recyclerViewNewProduct.setAdapter(newProductsAdapter);
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Smth is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

}