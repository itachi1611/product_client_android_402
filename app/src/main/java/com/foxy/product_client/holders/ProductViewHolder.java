package com.foxy.product_client.holders;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.faraaf.tictacdev.valueselector.ValueSelector;
import com.foxy.product_client.R;
import com.foxy.product_client.models.Product;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import java.text.NumberFormat;
import java.util.List;
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
    public ImageButton btnCart;

    @BindView(R.id.rbProduct)
    RatingBar rbProduct;

    Bitmap bitmap = null;

    private View itemView;

    private static float RATING_BAR_STEP_SIZE = 0.1F;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void onBindData(Product product) {
        tvName.setText(product.getName());
        tvPrice.setText(NumberFormat.getCurrencyInstance(Locale.JAPANESE).format(product.getPrice()).trim());
        tvDescription.setText(product.getDescription());

        rbProduct.setNumStars(5);
        rbProduct.setMax(5);
        rbProduct.setStepSize(RATING_BAR_STEP_SIZE);
        rbProduct.setSaveEnabled(true);
        rbProduct.setRating((float) (Math.random() * 5));

        rbProduct.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> Log.d("NamNT", String.valueOf(rating)));

        if(product.getBufferImage() != null) {
            //Convert List<Integer> to Byte Array
            List<Integer> list = product.getBufferImage().getData();
            byte[] bytes = new byte[list.size()];
            for (int i = 0; i < list.size(); i++) {
                bytes[i] = (byte) list.get(i).intValue();
            }
            bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            ivProduct.setImageBitmap(bitmap);
        } else {
            ivProduct.setImageResource(R.drawable.ic_no_image);
        }

        btnCart.setOnClickListener(v -> {
            Snackbar.make(itemView, "Added", Snackbar.LENGTH_LONG).show();
            Context context = itemView.getContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dialogView = inflater.inflate(R.layout.fragment_bottom, null);
            builder.setView(dialogView);
            Dialog dialog = builder.show();

            //Init elements for dialog
            CircleImageView ivProduct = dialog.findViewById(R.id.ivProduct);
            MaterialTextView tvName = dialog.findViewById(R.id.tvName);
            MaterialTextView tvPrice = dialog.findViewById(R.id.tvPrice);
            MaterialTextView tvDescription = dialog.findViewById(R.id.tvDescription);
            ValueSelector vsQuantity = dialog.findViewById(R.id.vsQuantity);
            MaterialButton btnOrder = dialogView.findViewById(R.id.btnOrder);

            //Add value to dialog
            if(product.getBufferImage() != null) {
                ivProduct.setImageBitmap(bitmap);
            } else {
                ivProduct.setImageResource(R.drawable.ic_no_image);
            }
            tvName.setText(product.getName());
            tvPrice.setText(NumberFormat.getCurrencyInstance(Locale.JAPANESE).format(product.getPrice()).trim());
            tvDescription.setText(product.getDescription());

            btnOrder.setOnClickListener(v1 -> {
                int quantity = vsQuantity.getValue();
            });
        });
    }

}
