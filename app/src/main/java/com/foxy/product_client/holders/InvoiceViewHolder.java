package com.foxy.product_client.holders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faraaf.tictacdev.valueselector.ValueSelector;
import com.foxy.product_client.R;
import com.foxy.product_client.models.Invoice;
import com.google.android.material.textview.MaterialTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvName)
    MaterialTextView tvName;

    @BindView(R.id.vsQuantity)
    ValueSelector vsQuantity;

    private View itemView;

    public InvoiceViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void onBindData(Invoice invoice) {
        tvName.setText(invoice.getProductName());
        vsQuantity.setValue(invoice.getProductQuantity());
        vsQuantity.setFocusable(false);
        vsQuantity.setClickable(false);
    }

}
