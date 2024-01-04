package com.SachinApps.Whatscan.Pro.WhatsClone.shayri;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.FragShayriViewBinding;


public class FragmentShayriViews extends Fragment {

    FragShayriViewBinding binding;
    int _position;
    String strShayri;

    public FragmentShayriViews() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragShayriViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        _position = getArguments().getInt("CurrentPosition");

        strShayri = getArguments().getString("CurrentShayri");

        binding.tvShayri.setText(strShayri);
        return view;
    }
}