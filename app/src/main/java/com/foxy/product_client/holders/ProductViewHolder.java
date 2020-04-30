package com.foxy.product_client.holders;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foxy.product_client.R;
import com.foxy.product_client.models.Product;
import com.foxy.product_client.ultis.RecyclerViewUtils.RecyclerViewClickListener;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

    private RecyclerViewClickListener mListener;

    private View itemView;

    private static float RATING_BAR_STEP_SIZE = 0.1F;

    public ProductViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
        this.mListener = listener;
        btnCart.setOnClickListener(this);

    }

    public void onBindData(Product product) {
        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice().toString().trim() + " $");
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
            ivProduct.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
        } else {
            ivProduct.setImageResource(R.drawable.ic_no_image);
        }
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(v, getAdapterPosition());
    }

}
