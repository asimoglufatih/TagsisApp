package com.example.firestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddProduct extends AppCompatActivity {

    Product product;
    FirebaseFirestore db1;

    Button btnAddProduct;
    EditText editText_barcode, editText_brand, editText_company, editText_product, editText_unsuitability, editText_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        tanimla();
        writeData();
    }

    private void tanimla(){

        editText_barcode = findViewById(R.id.editText_barcode);
        editText_brand = findViewById(R.id.editText_brand);
        editText_company = findViewById(R.id.editText_company);
        editText_product = findViewById(R.id.editText_product);
        editText_unsuitability = findViewById(R.id.editText_unsuitability);
        editText_date = findViewById(R.id.editText_date);

        btnAddProduct = findViewById(R.id.btnAddProduct);

        db1 = FirebaseFirestore.getInstance();

    }

    private Product createProduct(){
        product = new Product(editText_date.getText().toString(), editText_company.getText().toString(),
                editText_unsuitability.getText().toString(),
                editText_brand.getText().toString(),
                editText_barcode.getText().toString(), editText_product.getText().toString());

        return product;
    }

    private void writeData(){

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db1.collection("tagsis").document("deneme2").set(createProduct());
                Context context = getApplicationContext();
                CharSequence text = "Başarıyla eklendi";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }



}
