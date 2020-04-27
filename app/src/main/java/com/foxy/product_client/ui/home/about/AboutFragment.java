package com.foxy.product_client.ui.home.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.foxy.product_client.R;
import com.foxy.product_client.bases.BaseFragment;
import com.foxy.product_client.ultis.CommonUtils;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

import static com.foxy.product_client.ultis.Constants.ARG_BG_COLOR;

public class AboutFragment extends BaseFragment implements AboutContract.View {

    private int bgColorResId = R.color.blue_grey_inactive;

    public AboutFragment() {}

    private AboutContract.Presenter mPresenter = new AboutPresenter(this);   // Presenter

    public static AboutFragment newInstance(int bgColorId) {
        AboutFragment fragment = new AboutFragment();
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
        CommonUtils.simulateDayNight(/* DAY */ 0, getActivity());
        Element adsElement = CommonUtils.createAboutElement("Advertise with us", null, null, null, null, null);

        return new AboutPage(getActivity())
                .isRTL(false)
                .setDescription(getString(R.string.about_description))
                .setImage(R.mipmap.ic_launcher_round)
                .addItem(new Element().setTitle("Version 1.0 alpha 1"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("")
                .addWebsite("")
                .addFacebook("")
                .addTwitter("")
                .addYoutube("")
                .addPlayStore("")
                .addInstagram("")
                .addGitHub("")
                .addItem(CommonUtils.getCopyRightsElement(getActivity()))
                .create();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
