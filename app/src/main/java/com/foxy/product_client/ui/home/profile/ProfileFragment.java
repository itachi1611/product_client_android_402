package com.foxy.product_client.ui.home.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.foxy.product_client.R;
import com.foxy.product_client.bases.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.foxy.product_client.ultis.Constants.ARG_BG_COLOR;

public class ProfileFragment extends BaseFragment implements ProfileContract.View {

    private int bgColorResId = R.color.blue_grey_inactive;

    private View view;

    private Unbinder unbinder;

    public ProfileFragment() {}

    private ProfileContract.Presenter mPresenter = new ProfilePresenter(this);   // Presenter

    public static ProfileFragment newInstance(int bgColorId) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_BG_COLOR, bgColorId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            bgColorResId = getArguments().getInt(ARG_BG_COLOR);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view.setBackgroundColor(ContextCompat.getColor(getContext(), bgColorResId));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
