package com.foxy.product_client.ui.home.bottom_fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.faraaf.tictacdev.valueselector.ValueSelector;
import com.foxy.product_client.R;
import com.foxy.product_client.helpers.SharedPreferencesHelper;
import com.foxy.product_client.models.Product;
import com.foxy.product_client.ultis.CommonUtils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;


public class AddOrderBottomDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener, AddOrderBottomDialogContract.View {

    @BindView(R.id.ivProduct)
    CircleImageView ivProduct;

    @BindView(R.id.tvName)
    MaterialTextView tvName;

    @BindView(R.id.tvPrice)
    MaterialTextView tvPrice;

    @BindView(R.id.tvDescription)
    MaterialTextView tvDescription;

    @BindView(R.id.vsQuantity)
    ValueSelector vsQuantity;

    @BindView(R.id.btnOrder)
    MaterialButton btnOrder;

    private String name;
    private int price;
    private String description;
    private List<Integer> image = new ArrayList<>();

    private Unbinder unbinder;

    private AddOrderBottomDialogContract.Presenter mPresenter = new AddOrderBottomDialogPresenter(this);   // Presenter

    public AddOrderBottomDialogFragment() {}

    public static AddOrderBottomDialogFragment newInstance() {
        AddOrderBottomDialogFragment fragment = new AddOrderBottomDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            Product product = (Product) getArguments().getSerializable("p_obj");
            name = product.getName();
            price = product.getPrice();
            description = product.getDescription();
            if(product.getBufferImage() != null) {
                image = product.getBufferImage().getData();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        initData();

        btnOrder.setOnClickListener(this);
    }

    private void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    private void initData() {
        if(image != null && image.size() >0 && !image.isEmpty()) {
            //Convert List<Integer> to Byte Array
            byte[] bytes = new byte[image.size()];
            for (int i = 0; i < image.size(); i++) {
                bytes[i] = (byte) image.get(i).intValue();
            }
            ivProduct.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
        } else {
            ivProduct.setImageResource(R.drawable.ic_no_image);
        }
        tvName.setText(name.trim());
        tvPrice.setText(String.valueOf(price).trim());
        tvDescription.setText(description.trim());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnOrder) {
            String c_name = SharedPreferencesHelper.getInstance(getContext()).getDataFromPref("u_name", "");
            String c_id = SharedPreferencesHelper.getInstance(getContext()).getDataFromPref("u_id", "");
            String c_address = SharedPreferencesHelper.getInstance(getContext()).getDataFromPref("u_address", "");
            mPresenter.onCreateOrder(c_name, c_id, c_address, name, vsQuantity.getValue(), price);
        }
    }

    @Override
    public void onCreateSuccess(ResponseBody responseBody) {
        dismiss();
        CommonUtils.showSuccessToast(getContext(), "Create new order succeeded !");
    }

    @Override
    public void onCreateError() {
        CommonUtils.showErrorToast(getContext(), "Sorry for the trouble, let try make order later !");
    }
}
