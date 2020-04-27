package com.foxy.product_client.holders;

import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foxy.product_client.R;
import com.foxy.product_client.models.Product;
import com.foxy.product_client.ultis.CommonUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.travijuu.numberpicker.library.NumberPicker;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvName)
    MaterialTextView tvName;

    @BindView(R.id.tvPrice)
    MaterialTextView tvPrice;

    @BindView(R.id.tvDescription)
    MaterialTextView tvDescription;

    @BindView(R.id.ivProduct)
    CircleImageView ivProduct;

    @BindView(R.id.btnCart)
    ImageButton btnCart;

    @BindView(R.id.npQuantity)
    NumberPicker npQuantity;

    int quantity;

    private View itemView;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void onBindData(Product product) {
        tvName.setText(product.getName());
        tvPrice.setText(NumberFormat.getCurrencyInstance(Locale.JAPANESE).format(product.getPrice()).trim());
        tvDescription.setText(product.getDescription());
        //ivProduct.setImageResource(product.getImage());
        btnCart.setOnClickListener(v -> {
            Snackbar.make(itemView, "Added", Snackbar.LENGTH_LONG).show();

            npQuantity.setValueChangedListener((value, action) -> {
                CommonUtils.showSuccessToast(itemView.getContext(), String.valueOf(value));
            });

            npQuantity.setLimitExceededListener((limit, exceededValue) -> {
                CommonUtils.showInfoToast(itemView.getContext(), "You reach the max" + limit + " product");
            });

            //npQuantity.setOnEditorActionListener(new DefaultOnEditorActionListener());

            npQuantity.setOnFocusChangeListener((v1, hasFocus) -> {
                CommonUtils.showInfoToast(itemView.getContext(), "Reach focus !");
            });

        });
    }

}
