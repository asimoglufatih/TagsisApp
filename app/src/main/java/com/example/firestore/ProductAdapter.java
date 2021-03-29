package com.example.firestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    public List<Product> productlist;
    public Context context;

    public ProductAdapter(List<Product> productlist, Context context) {
        this.productlist = productlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_layout, parent, false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        holder.dateText.setText(productlist.get(position).getDate().toString());
        holder.companyNameText.setText(productlist.get(position).getCompanyName().toString());
        holder.unsuitability.setText(productlist.get(position).getUnsuitability().toString());
        holder.brandText.setText(productlist.get(position).getBrand().toString());
        holder.barcodeText.setText(productlist.get(position).getBarcode().toString());
        holder.productNameText.setText(productlist.get(position).getProductName().toString());

    }

    @Override
    public int getItemCount() {

        return productlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView productNameText, brandText, companyNameText, barcodeText,unsuitability, dateText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameText = itemView.findViewById(R.id.productNameText);
            brandText = itemView.findViewById(R.id.brandText);
            companyNameText = itemView.findViewById(R.id.companyNameText);
            barcodeText = itemView.findViewById(R.id.barcodeText);
            unsuitability = itemView.findViewById(R.id.unsuitabilityText);
            dateText = itemView.findViewById(R.id.dateText);

        }
    }
}
