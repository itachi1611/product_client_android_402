package com.foxy.product_client.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foxy.product_client.R;
import com.foxy.product_client.holders.InvoiceViewHolder;
import com.foxy.product_client.models.Invoice;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceViewHolder> {

    private List<Invoice> mInvoices;

    public InvoiceAdapter(List<Invoice> mInvoices) {
        this.mInvoices = mInvoices;
    }

    public List<Invoice> getListOrder() {
        return mInvoices;
    }

    public void setListOrder(List<Invoice> mList) {
        this.mInvoices.clear();
        mInvoices.addAll(mList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InvoiceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        Invoice invoice = mInvoices.get(position);
        holder.onBindData(invoice);
    }

    @Override
    public int getItemCount() {
        return (mInvoices != null) ? mInvoices.size() :0;
    }
}
