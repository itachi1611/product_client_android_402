package com.foxy.product_client.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.foxy.product_client.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddOrderBottomDialogFragment extends BottomSheetDialogFragment {

    public AddOrderBottomDialogFragment() {}

    public static AddOrderBottomDialogFragment newInstance() {
        AddOrderBottomDialogFragment fragment = new AddOrderBottomDialogFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        return view;
    }
}
