package com.foxy.product_client.ui.home.cart;

import android.os.Bundle;
import android.os.Handler;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foxy.product_client.R;
import com.foxy.product_client.adapters.InvoiceAdapter;
import com.foxy.product_client.bases.BaseFragment;
import com.foxy.product_client.helpers.SharedPreferencesHelper;
import com.foxy.product_client.models.Invoice;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.foxy.product_client.ultis.Constants.ARG_BG_COLOR;

public class CartFragment extends BaseFragment implements CartContract.View {

    @BindView(R.id.rvList)
    RecyclerView rvListOrder;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    private List<Invoice> mList;

    private String customer_id;

    private int bgColorResId = R.color.blue_grey_inactive;

    private View view;

    private Unbinder unbinder;

    public CartFragment() {}

    private CartContract.Presenter mPresenter = new CartPresenter(this);   // Presenter

    public static CartFragment newInstance(int bgColorId) {
        CartFragment fragment = new CartFragment();
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
        view = inflater.inflate(R.layout.fragment_cart, container, false);
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

        //Handle swipe action to refresh data
        onSwipeRefreshData();
        customer_id = SharedPreferencesHelper.getInstance(getContext()).getDataFromPref("u_id", "");
    }

    @Override
    public void onResume() {
        super.onResume();
        onShowLoading();
        mPresenter.onFetchOrder(customer_id);
    }

    private void onSwipeRefreshData() {
        swipeContainer.setOnRefreshListener(() -> {
            onShowLoading();
            mPresenter.onFetchOrder(customer_id);
            handleSwipeRefreshAction();
        });
    }

    private void handleSwipeRefreshAction() {
        new Handler().postDelayed(() -> {
            swipeContainer.setRefreshing(false);
        }, 150);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvListOrder.setItemAnimator(new DefaultItemAnimator());
        rvListOrder.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvListOrder.setHasFixedSize(true);
        rvListOrder.setLayoutManager(layoutManager);
        rvListOrder.setNestedScrollingEnabled(true);
        InvoiceAdapter adapter = new InvoiceAdapter(mList);
        rvListOrder.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onFetchOrderSuccess(List<Invoice> invoices) {
        if(invoices != null) {
            mList.addAll(invoices);
            onHideLoading();
            initRecyclerView();
        }
    }

    @Override
    public void onFetchOrderError() {
        onHideLoading();
    }
}
