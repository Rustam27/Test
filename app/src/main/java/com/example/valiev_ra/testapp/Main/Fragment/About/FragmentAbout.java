package com.example.valiev_ra.testapp.Main.Fragment.About;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valiev_ra.testapp.R;

public class FragmentAbout extends Fragment {

    public static FragmentAbout getInstance() {
        return FragmentAbout.FragmentAboutHolder.HOLDER_INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_about, null);

        TextView tvCopyright = view.findViewById(R.id.tv_copyright);
        TextView tvVersion = view.findViewById(R.id.tv_version);

        try {
            tvCopyright.setText("© Copyright " + getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).packageName);
            tvVersion.setText("Version: " + getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException ignore) {
        }

        return view;
    }

    public static class FragmentAboutHolder {
        @SuppressLint("StaticFieldLeak")
        static final FragmentAbout HOLDER_INSTANCE = new FragmentAbout();
    }
}