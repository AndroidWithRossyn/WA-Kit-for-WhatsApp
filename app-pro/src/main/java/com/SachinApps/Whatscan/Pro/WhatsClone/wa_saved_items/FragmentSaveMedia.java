package com.SachinApps.Whatscan.Pro.WhatsClone.wa_saved_items;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.SachinApps.Whatscan.Pro.WhatsClone.Utils.Common;
import com.SachinApps.Whatscan.Pro.WhatsClone.model.StatusModel;
import com.SachinApps.Whatscan.Pro.WhatsClone.R;
import com.SachinApps.Whatscan.Pro.WhatsClone.databinding.FragSaveMediaBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FragmentSaveMedia extends Fragment {

    FragSaveMediaBinding binding;
    private final Handler handler = new Handler();
    private FilesAdapter filesAdapter;
    public ArrayList<String> images;
    public ArrayList<StatusModel> statusModelsVideo;
    public ArrayList<StatusModel> statusModelsImage;
    public ArrayList<StatusModel> statusModelsAll;
    public ArrayList<StatusModel> statusModels = new ArrayList<>();
    public ArrayList<StatusModel> statusModels1 = new ArrayList<>();
    StatusModel model;

    public FragmentSaveMedia() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragSaveMediaBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        binding.swipeRefreshLayoutFiles.setColorSchemeColors(ContextCompat.getColor(requireActivity(), android.R.color.holo_orange_dark)
                , ContextCompat.getColor(requireActivity(), android.R.color.holo_green_dark),
                ContextCompat.getColor(requireActivity(), R.color.colorPrimary),
                ContextCompat.getColor(requireActivity(), android.R.color.holo_blue_dark));

        binding.swipeRefreshLayoutFiles.setOnRefreshListener(this::getFiles);

        binding.recyclerViewFiles.setHasFixedSize(true);
        binding.recyclerViewFiles.setLayoutManager(new GridLayoutManager(getActivity(), Common.GRID_COUNT));

        getFiles();


    }

    private void getFiles() {
        statusModelsAll = new ArrayList<>();
        statusModelsVideo = new ArrayList<>();
        statusModelsImage = new ArrayList<>();
        statusModelsVideo = getAllShownVideoPath();
        statusModelsImage = getAllShownImagesPath();
        statusModelsAll.addAll(statusModelsVideo);
        statusModelsAll.addAll(statusModelsImage);

        if (statusModelsAll.size() <= 0){
            binding.ivNoFilesFound.setVisibility(View.VISIBLE);
            binding.recyclerViewFiles.setVisibility(View.GONE);
            binding.progressBar.setVisibility(View.GONE);
        }else {
            binding.progressBar.setVisibility(View.GONE);
            binding.ivNoFilesFound.setVisibility(View.GONE);
            binding.recyclerViewFiles.setVisibility(View.VISIBLE);
            filesAdapter = new FilesAdapter(statusModelsAll);
            binding.recyclerViewFiles.setAdapter(filesAdapter);
            filesAdapter.notifyDataSetChanged();

        }
    }

    public static File[] listFile;
    private ArrayList<StatusModel> getAllShownVideoPath() {
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
        sb.append("/Status Video");

        File file = new File(sb.toString());
        if (file.isDirectory()) {
            listFile = file.listFiles();

            for (File fileNext : new File(sb.toString()).listFiles()) {
                try {
                    if (fileNext.isFile()) {
                        model = new StatusModel(fileNext,fileNext.getName(),fileNext.getAbsolutePath());
                        statusModels.add(model);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Collections.reverse(statusModels);
        }
        return statusModels;
    }
    private ArrayList<StatusModel> getAllShownImagesPath() {
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        sb.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        sb.append("/Status Photos");

        File file = new File(sb.toString());
        if (file.isDirectory()) {
            listFile = file.listFiles();

            for (File fileNext : new File(sb.toString()).listFiles()) {
                try {
                    if (fileNext.isFile()) {
                        model = new StatusModel(fileNext,fileNext.getName(),fileNext.getAbsolutePath());
                        statusModels1.add(model);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Collections.reverse(statusModels1);
        }
        return statusModels1;
    }

}