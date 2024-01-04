package com.SachinApps.Whatscan.Pro.WhatsClone.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.FragPosterViewBinding;


public class FragmentPostersView extends Fragment {

    FragPosterViewBinding binding;
    int currPos;
    int[] myImageList = new int[]{R.drawable.e_poster_1, R.drawable.e_poster_2, R.drawable.e_poster_3,
            R.drawable.e_poster_4, R.drawable.e_poster_5};

    public FragmentPostersView() {
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragPosterViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        assert getArguments() != null;
        currPos = getArguments().getInt("CurrentPosition");
        binding.ivPoster.setImageResource(myImageList[currPos]);
        return view;
    }
}