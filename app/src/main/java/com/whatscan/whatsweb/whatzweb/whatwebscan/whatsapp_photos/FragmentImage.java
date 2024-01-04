package com.whatscan.whatsweb.whatzweb.whatwebscan.whatsapp_photos;

import android.content.UriPermission;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.whatscan.whatsweb.whatzweb.whatwebscan.Utils.Common;
import com.whatscan.whatsweb.whatzweb.whatwebscan.model.StatusModel;
import com.whatscan.whatsweb.whatzweb.whatwebscan.R;
import com.whatscan.whatsweb.whatzweb.whatwebscan.databinding.FragImagesBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;


public class FragmentImage extends Fragment {

    FragImagesBinding binding;
    private final List<StatusModel> imagesList = new ArrayList<>();
    private ImageAdapter imageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragImagesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        binding.swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(requireActivity(), android.R.color.holo_orange_dark)
                , ContextCompat.getColor(requireActivity(), android.R.color.holo_green_dark),
                ContextCompat.getColor(requireActivity(), R.color.colorPrimary),
                ContextCompat.getColor(requireActivity(), android.R.color.holo_blue_dark));

        binding.swipeRefreshLayout.setOnRefreshListener(this::getStatus);

        binding.recyclerViewImage.setHasFixedSize(true);
        binding.recyclerViewImage.setLayoutManager(new GridLayoutManager(getActivity(), Common.GRID_COUNT));

        getStatus();

    }

    private void getStatus() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            executeNew();

        } else if (Common.STATUS_DIRECTORY.exists()) {

            executeOld();

        } else {
            binding.ivNoFilesFound.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "Cannot find WhatsApp Dir", Toast.LENGTH_SHORT).show();
            binding.swipeRefreshLayout.setRefreshing(false);
        }

    }

    private void executeOld() {

        new Thread(() -> {

            Handler mainHandler = new Handler(Looper.getMainLooper());

            File[] statusFiles;
            statusFiles = Common.STATUS_DIRECTORY.listFiles();
            imagesList.clear();

            if (statusFiles != null && statusFiles.length > 0) {

                Arrays.sort(statusFiles);
                for (File file : statusFiles) {
                    StatusModel status = new StatusModel(file, file.getName(), file.getAbsolutePath());

                    if (!status.isVideo() && status.getTitle().endsWith(".jpg")) {
                        imagesList.add(status);
                    }

                }

                mainHandler.post(() -> {

                    if (imagesList.size() <= 0) {
                        binding.ivNoFilesFound.setVisibility(View.VISIBLE);
                    } else {
                        binding.ivNoFilesFound.setVisibility(View.GONE);
                    }

                    imageAdapter = new ImageAdapter(imagesList, binding.imageContainer);
                    binding.recyclerViewImage.setAdapter(imageAdapter);
                    imageAdapter.notifyDataSetChanged();
//                    imageAdapter.notifyItemRangeChanged(0, imagesList.size());
                    binding.prgressBarImage.setVisibility(View.GONE);
                });

            } else {

                mainHandler.post(() -> {
                    binding.prgressBarImage.setVisibility(View.GONE);
                    binding.ivNoFilesFound.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "No Files Found", Toast.LENGTH_SHORT).show();
                });

            }
            binding.swipeRefreshLayout.setRefreshing(false);

        }).start();
    }

    private void executeNew() {

        Executors.newSingleThreadExecutor().execute(() -> {
            Handler mainHandler = new Handler(Looper.getMainLooper());

            List<UriPermission> list = requireActivity().getContentResolver().getPersistedUriPermissions();

            DocumentFile file = DocumentFile.fromTreeUri(requireActivity(), list.get(0).getUri());

            imagesList.clear();

            if (file == null) {
                mainHandler.post(() -> {
                    binding.prgressBarImage.setVisibility(View.GONE);
                    binding.ivNoFilesFound.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "No Files Found", Toast.LENGTH_SHORT).show();
                    binding.swipeRefreshLayout.setRefreshing(false);
                });
                return;
            }

            DocumentFile[] statusFiles = file.listFiles();

            if (statusFiles.length <= 0) {
                mainHandler.post(() -> {
                    binding.prgressBarImage.setVisibility(View.GONE);
                    binding.ivNoFilesFound.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "No Files Found", Toast.LENGTH_SHORT).show();
                    binding.swipeRefreshLayout.setRefreshing(false);
                });
                return;
            }

            for (DocumentFile documentFile : statusFiles) {
                StatusModel status = new StatusModel(documentFile);

                if (!status.isVideo()) {
                    imagesList.add(status);
                }
            }

            Log.e("HEY: ", String.valueOf(imagesList.size()));

            mainHandler.post(() -> {
                imagesList.remove(0);
                if (imagesList.size() <= 0) {
                    binding.ivNoFilesFound.setVisibility(View.VISIBLE);
                } else {
                    binding.ivNoFilesFound.setVisibility(View.GONE);
                }

                imageAdapter = new ImageAdapter(imagesList, binding.imageContainer);
                binding.recyclerViewImage.setAdapter(imageAdapter);
//                imageAdapter.notifyItemRangeChanged(0, imagesList.size());
                binding.prgressBarImage.setVisibility(View.GONE);
            });

        });
    }

}
