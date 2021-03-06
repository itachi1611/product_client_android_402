package com.foxy.product_client.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foxy.product_client.R;
import com.foxy.product_client.holders.ProductViewHolder;
import com.foxy.product_client.models.Product;
import com.foxy.product_client.ultis.RecyclerViewUtils.RecyclerViewClickListener;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private RecyclerViewClickListener mListener;

    private List<Product> mProducts;

    public ProductAdapter(List<Product> mProducts, RecyclerViewClickListener listener) {
        this.mProducts = mProducts;
        this.mListener = listener;
    }

    public List<Product> getListProduct() {
        return mProducts;
    }

    public void setListProduct(List<Product> mList) {
        this.mProducts.clear();
        mProducts.addAll(mList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        if(holder instanceof ProductViewHolder) {
            ProductViewHolder productViewHolder = holder;
            Product product = mProducts.get(position);
            holder.onBindData(product);
        }
    }

    @Override
    public int getItemCount() {
        return (mProducts != null) ? mProducts.size() :0;
    }

}
