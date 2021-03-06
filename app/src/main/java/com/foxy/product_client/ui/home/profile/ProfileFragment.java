package com.foxy.product_client.ui.home.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Priority;
import com.foxy.product_client.R;
import com.foxy.product_client.bases.BaseFragment;
import com.foxy.product_client.helpers.SharedPreferencesHelper;
import com.foxy.product_client.ui.authentication.LoginActivity;
import com.foxy.product_client.ultis.CommonUtils;
import com.foxy.product_client.ultis.ImageViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;

import static com.foxy.product_client.ultis.Constants.ARG_BG_COLOR;

public class ProfileFragment extends BaseFragment implements ProfileContract.View {

    @BindView(R.id.profile_image)
    CircleImageView profileImage;

    @BindView(R.id.edtName)
    EditText edtName;

    @BindView(R.id.edtEmail)
    EditText edtEmail;

    @BindView(R.id.edtPhone)
    EditText edtPhone;

    @BindView(R.id.edtAddress)
    EditText edtAddress;

    @BindView(R.id.edtRole)
    EditText edtRole;

    @BindView(R.id.btnSave)
    Button btnSave;

    @BindView(R.id.btnLogout)
    Button btnLogout;


    private int bgColorResId = R.color.blue_grey_inactive;

    private View view;

    private Unbinder unbinder;

    private SharedPreferencesHelper pref;

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

        initData();
    }

    private void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        pref = SharedPreferencesHelper.getInstance(getActivity());
    }

    private void initData() {
        String _id = pref.getDataFromPref("u_id", "");
        String name = pref.getDataFromPref("u_name", "");
        String email = pref.getDataFromPref("u_email", "");
        String phone = pref.getDataFromPref("u_phone", "");
        String address = pref.getDataFromPref("u_address", "Hanoi");
        boolean role = pref.getDataFromPref("u_role", false);

        if(!role) {
            edtRole.setText("Customer");
            ImageViewUtils.loadImage(getContext(), profileImage, R.drawable.ic_customer, Priority.NORMAL);
        } else {
            edtRole.setText("Admin");
            ImageViewUtils.loadImage(getContext(), profileImage, R.drawable.ic_admin, Priority.NORMAL);
        }

        edtName.setHint(name);
        edtEmail.setHint(email);
        edtPhone.setHint(phone);
        edtAddress.setHint(address);

        btnSave.setOnClickListener(v -> {
            onShowLoading();
            mPresenter.onEditUser(
                    _id,
                    edtName.getText().toString().trim(),
                    edtPhone.getText().toString().trim(),
                    edtAddress.getText().toString().trim()
            );
        });

        btnLogout.setOnClickListener(v -> {
            pref.saveDataToPref("isLogin", false);
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            getContext().startActivity(intent);
            getActivity().finish();
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onEditUserSuccess(ResponseBody responseBody) {
        onHideLoading();
        CommonUtils.showSuccessToast(getContext(), "Update user succeeded !");
    }

    @Override
    public void onEditUserError() {
        onHideLoading();
        CommonUtils.showErrorToast(getContext(), "Update user failed !");
    }

}
