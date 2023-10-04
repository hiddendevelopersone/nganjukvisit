package com.polije.sem3;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.polije.sem3.network.BaseResponse;
import com.polije.sem3.network.UploadService;
import com.polije.sem3.utility.FileUtils;
import com.polije.sem3.utility.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profiles#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profiles extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int PICK_IMAGE = 1;
    private static final int PERMISSION_REQUEST_STORAGE = 2;

    private static final String TYPE_1 = "multipart";
    private static final String TYPE_2 = "base64";

    private Uri uri;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView imgThumb;
    public Profiles() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profiles.
     */
    // TODO: Rename and change types and number of parameters
    public static Profiles newInstance(String param1, String param2) {
        Profiles fragment = new Profiles();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profiles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgThumb = view.findViewById(R.id.img_thumb);

        Button btnChoose = (Button) view.findViewById(R.id.btn_choose);
        Button btnUpload1 = (Button) view.findViewById(R.id.btn_upload_1);
        Button btnUpload2 = (Button) view.findViewById(R.id.btn_upload_2);

        btnChoose.setOnClickListener(v -> {
//            LogApp.info(requireContext(), LogTag.ON_CLICK, "Button Choose Diclik");
            choosePhoto();
        });

        btnUpload1.setOnClickListener(v -> {
            if(uri != null) {
                File file = FileUtils.getFile(requireActivity(), uri);
                uploadMultipart(file);
            }else{
                Toast.makeText(requireActivity(), "You must choose the image", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpload2.setOnClickListener(v -> {
            if(uri != null) {
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String encoded = ImageUtils.bitmapToBase64String(bitmap, 100);
                uploadBase64(encoded);
            }else{
                Toast.makeText(requireActivity(), "You must choose the image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void choosePhoto() {
//        if (
//                ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED
//        ) {
//
//            ActivityCompat.requestPermissions(requireActivity(),
//                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    PERMISSION_REQUEST_STORAGE);
//            Toast.makeText(requireActivity(), "permission needed", Toast.LENGTH_SHORT).show();
//        }else{
//        }

        openGallery();
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
    }

    private void uploadMultipart(File file) {
        RequestBody photoBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("photo",
                file.getName(), photoBody);

        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), TYPE_1);
        UploadService uploadService = new UploadService();
        uploadService.uploadPhotoMultipart(action, photoPart).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response != null) {
                    Toast.makeText(requireActivity(), response.message(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(requireActivity(), "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(requireActivity(), "GAGAL 2", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void uploadBase64(String imgBase64) {
        UploadService uploadService = new UploadService();
        uploadService.uploadPhotoBase64(TYPE_2, imgBase64).enqueue(new retrofit2.Callback<BaseResponse>() {
            @Override
            public void onResponse(retrofit2.Call<BaseResponse> call, retrofit2.Response<BaseResponse> response) {
                if(response != null) {
                    Toast.makeText(requireActivity(), response.message(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(requireActivity(), "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<BaseResponse> call, Throwable t) {
                Toast.makeText(requireActivity(), "GAGAL 2", Toast.LENGTH_SHORT).show();
            t.printStackTrace();
            }
        });
//        uploadService.uploadPhotoBase64(TYPE_2, imgBase64, new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//
//                if(response != null) {
//                    Toast.makeText(requireActivity(), response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    openGallery();
                }

                return;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                uri = data.getData();

                imgThumb.setImageURI(uri);
            }
        }
    }
}