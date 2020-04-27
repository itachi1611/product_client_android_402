package com.foxy.product_client.ui.home.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.foxy.product_client.R;
import com.foxy.product_client.adapters.ProductAdapter;
import com.foxy.product_client.bases.BaseFragment;
import com.foxy.product_client.models.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.foxy.product_client.ultis.Constants.ARG_BG_COLOR;

public class HomeFragment extends BaseFragment implements HomeContract.View {

    @BindView(R.id.rvList)
    RecyclerView rvList;

    private List<Product> mList;

    private int bgColorResId = R.color.blue_grey_inactive;

    private View view;

    private Unbinder unbinder;

    public HomeFragment() {}

    private HomeContract.Presenter mPresenter = new HomePresenter(this);   // Presenter

    public static HomeFragment newInstance(int bgColorId) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
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
        mList = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        onShowLoading();
        mPresenter.onFetchProductData();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(layoutManager);
        ProductAdapter adapter = new ProductAdapter(mList);
        rvList.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onFetchSuccess(List<Product> products) {
        if(products != null) {
            mList.addAll(products);
        }
        onHideLoading();
        initRecyclerView();
    }

    @Override
    public void onFetchError() {

    }

}
