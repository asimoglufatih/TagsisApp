package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final Activity activity = this;
    Button btnBrandSearch, btnBarcodeSearch, btnProductSearch, btnGoAddProduct;
    EditText editText;
    RecyclerView recyclerView;

    FirebaseFirestore db;
    DocumentReference docRef;

    Product product;
    List<Product> productList;
    ProductAdapter productAdapter;

    Source source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        tanimla();

        searchBrand();
        scanBarcode();
        searchProduct();
        goAddProduct();
    }

    public void tanimla() {

        productList = new ArrayList<>();

        btnBrandSearch = findViewById(R.id.btnBrandSearch);
        btnBarcodeSearch = findViewById(R.id.btnBarcodeSearch);
        btnProductSearch = findViewById(R.id.btnProductSearch);
        btnGoAddProduct = findViewById(R.id.btnGoAddProduct);

        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        db = FirebaseFirestore.getInstance();
        source = Source.DEFAULT;

        productAdapter = new ProductAdapter(productList, getApplicationContext());
        recyclerView.setAdapter(productAdapter);

    }

    public void searchBrand() {
        btnBrandSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("tagsis")
                        .whereEqualTo("brand", editText.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult() == null) {
                                        Context context = getApplicationContext();
                                        CharSequence text = "Bulunamadı";
                                        int duration = Toast.LENGTH_LONG;

                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                    }
                                    else{
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            //Log.d("oldu", document.getId() + "=>" + document.getData());
                                            product = document.toObject(Product.class);
                                            productList.add(product);
                                            productAdapter.notifyDataSetChanged();
                                        }

                                    }
                                } else {
                                    Log.d("olmadı", "Başaramadık abi." + task.getException());
                                }
                            }
                        });

            }

        });
    }

    public void scanBarcode() {

        btnBarcodeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
            else {
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                db.collection("tagsis")
                        .whereEqualTo("barcode", result.getContents().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult() != null) {

                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            //Log.d("oldu", document.getId() + "=>" + document.getData());
                                            product = document.toObject(Product.class);
                                            productList.add(product);
                                            productAdapter.notifyDataSetChanged();
                                        }
                                    }
                                    else{
                                        Context context = getApplicationContext();
                                        CharSequence text = "Bulunamadı";
                                        int duration = Toast.LENGTH_SHORT;

                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                    }
                                }
                                else {
                                    Log.d("olmadı", "Başaramadık abi." + task.getException());
                                }
                            }
                        });
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void searchProduct() {
        btnProductSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("tagsis")
                        .whereEqualTo("productName", editText.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult() != null) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            //Log.d("oldu", document.getId() + "=>" + document.getData());
                                            product = document.toObject(Product.class);
                                            productList.add(product);
                                            productAdapter.notifyDataSetChanged();
                                        }
                                    }
                                    else{
                                        Context context = getApplicationContext();
                                        CharSequence text = "Bulunamadı";
                                        int duration = Toast.LENGTH_SHORT;

                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                    }
                                } else {
                                    Log.d("olmadı", "Başaramadık abi." + task.getException());
                                }
                            }
                        });

            }
        });
    }

    public void goAddProduct() {
        btnGoAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intGoAddProduct = new Intent(getApplicationContext(), AddProduct.class);
                startActivity(intGoAddProduct);
            }
        });
    }


}
