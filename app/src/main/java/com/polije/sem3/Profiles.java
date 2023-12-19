package com.polije.sem3;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.polije.sem3.model.UserModel;
import com.polije.sem3.network.BaseResponse;
import com.polije.sem3.network.Config;
import com.polije.sem3.network.UploadInterface;
import com.polije.sem3.network.UploadService;
import com.polije.sem3.response.FavoritKulinerResponse;
import com.polije.sem3.response.ResponseGetGambarProfil;
import com.polije.sem3.response.UserResponse;
import com.polije.sem3.retrofit.Client;
import com.polije.sem3.util.UsersUtil;
import com.polije.sem3.utility.FileUtils;
import com.polije.sem3.utility.ImageUtils;

import org.w3c.dom.Text;

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

    private String ambilGambarBaru = "";

    private EditText editNamaText, emailText, alamatText, notelpText;

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

    private String gambarPengguna;
    private Button btnLogout;
    private ProgressDialog progressDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // loading bar
        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setTitle("mengubah profile...");
        progressDialog.setMessage("Harap Tunggu");
        progressDialog.setCancelable(false);

        imgThumb = view.findViewById(R.id.img_thumb);
        btnLogout = view.findViewById(R.id.btn_logout);
//        getGambar();
        TextView btnChoose = (TextView) view.findViewById(R.id.choosePictures);
        Button btnUpload2 = (Button) view.findViewById(R.id.btn_upload_2);
        ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        editNamaText = (EditText) view.findViewById(R.id.edt_namalengkap);
        emailText = (EditText) view.findViewById(R.id.edt_emailaddr);
        alamatText = (EditText) view.findViewById(R.id.edt_alamat);
        notelpText = (EditText) view.findViewById(R.id.edt_notelp);

        // disable email edit
        emailText.setEnabled(false);

        // get data akun yang sedang login
        UsersUtil util = new UsersUtil(requireContext());
        String idPengguna = util.getId();
        String namaPengguna = util.getFullName();
        String emailPengguna = util.getEmail();
        String notelpPengguna = util.getNoTelp();
        String alamatPengguna = util.getAlamat();
        gambarPengguna = util.getUserPhoto();

        // tampil gambar/foto pengguna
        Glide.with(requireContext()).load(Config.API_IMAGE + gambarPengguna).into(imgThumb);

        // set text ke field
        editNamaText.setText(namaPengguna);
        emailText.setText(emailPengguna);
        notelpText.setText(notelpPengguna);
        alamatText.setText(alamatPengguna);

        // Scroll up for more visibility edittext
        editNamaText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Gulirkan tampilan ke EditText saat EditText mendapatkan fokus
                    scrollView.scrollTo(0, editNamaText.getTop());
                }
            }
        });

        emailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Gulirkan tampilan ke EditText saat EditText mendapatkan fokus
                    scrollView.scrollTo(0, emailText.getTop());
                }
            }
        });

        btnChoose.setOnClickListener(v -> {

            choosePhoto();
        });

        btnUpload2.setOnClickListener(v -> {
            // run loading bar
            progressDialog.show();

            if (uri != null) {
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String encoded = ImageUtils.bitmapToBase64String(bitmap, 100);
                uploadBase64(encoded);
//                String namaUser = String.valueOf(editNamaText.getText());
//                String emailUser = String.valueOf(emailText.getText());
//                String alamatUser = String.valueOf(alamatText.getText());
//                String notelpUser = String.valueOf(notelpText.getText());

//                getGambar();
            } else {
                updateProfiles();
//                Toast.makeText(requireActivity(), "You must choose the image", Toast.LENGTH_SHORT).show();
                Log.d("Profiles" , "Just Update String Information");
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.signOut();
                if (!util.isSignIn()) {
                    Toast.makeText(requireActivity(), "Logout Sukses", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(requireActivity(), WelcomeScreen.class));
                }
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

    public void onResume() {
        super.onResume();

        UsersUtil usersUtil = new UsersUtil(requireActivity());
        String emailUser = usersUtil.getEmail();
        String namaUser = usersUtil.getFullName();
        String alamatUser = usersUtil.getAlamat();
        String notelpUser = usersUtil.getNoTelp();
        String gambarUser = usersUtil.getUserPhoto();

        editNamaText.setText(namaUser);
        emailText.setText(emailUser);
        alamatText.setText(alamatUser);
        notelpText.setText(notelpUser);
        gambarPengguna = gambarUser;
//        getGambar();
//        Glide.with(requireContext()).load(Config.API_IMAGE + gambarUser).into(imgThumb);
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
    }

    private void uploadBase64(String imgBase64) {
        UploadService uploadService = new UploadService();
        UsersUtil util = new UsersUtil(requireActivity());
        String idPengguna = util.getId();
        uploadService.uploadPhotoBase64(TYPE_2, imgBase64, idPengguna).enqueue(new retrofit2.Callback<BaseResponse>() {
            @Override
            public void onResponse(retrofit2.Call<BaseResponse> call, retrofit2.Response<BaseResponse> response) {
                if (response != null) {
                    Toast.makeText(requireActivity(), response.message(), Toast.LENGTH_SHORT).show();
                    response.body().getMessage();
                    updateProfiles();
//                    util.setUserPhoto(response.body());
                } else {
                    Toast.makeText(requireActivity(), "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<BaseResponse> call, Throwable t) {
                Toast.makeText(requireActivity(), "GAGAL 2", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }
//    public void getGambar(){
//        UsersUtil util = new UsersUtil(requireContext());
//        String idPengguna = util.getId();
//        Client.getInstance().getGambar(idPengguna).enqueue(new Callback<ResponseGetGambarProfil>() {
//            @Override
//            public void onResponse(Call<ResponseGetGambarProfil> call, Response<ResponseGetGambarProfil> response) {
//               if (response.body().getStatus().equalsIgnoreCase("success")){
//                ambilGambarBaru = response.body().getData();
//                util.setUserPhoto(ambilGambarBaru);
//                   Toast.makeText(getActivity(), "new : "+ambilGambarBaru, Toast.LENGTH_SHORT).show();
//                   Glide.with(requireContext()).load(Config.API_IMAGE + ambilGambarBaru).into(imgThumb);
//                   Toast.makeText(requireContext(), "utilphoto -> " + util.getUserPhoto(), Toast.LENGTH_SHORT).show();
//               }else {
//                   Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//               }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseGetGambarProfil> call, Throwable t) {
//                Toast.makeText(getActivity(), "error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void updateProfiles() {
        UsersUtil util = new UsersUtil(requireActivity());
        String idPengguna = util.getId();
        String namaUser = String.valueOf(editNamaText.getText());
        String emailUser = String.valueOf(emailText.getText());
        String alamatUser = String.valueOf(alamatText.getText());
        String notelpUser = String.valueOf(notelpText.getText());
        // update data
        Client.getInstance().updateprofiles(idPengguna, namaUser, emailUser, alamatUser, notelpUser).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                    progressDialog.dismiss();

                    Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            UsersUtil util = new UsersUtil(requireContext());
                    UserModel model = response.body().getData();
                    util.setFullName(namaUser);
                    util.setAlamat(alamatUser);
                    util.setNoTelp(notelpUser);
                    if (response.body().getData() != null) {
//                        Toast.makeText(requireContext(), "getgambarmodel " + model.getGambar(), Toast.LENGTH_SHORT).show();
                        util.setUserPhoto(model.getGambar());
                    }
//                            util.setUserPhoto(gambarPengguna);
//                            Glide.with(requireContext()).load(Config.API_IMAGE + gambarPengguna).into(imgThumb);
//                            getGambar();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(requireContext(), "Timeout", Toast.LENGTH_SHORT).show();
            }
        });
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
            if (data != null) {
                uri = data.getData();

                imgThumb.setImageURI(uri);
            }
        }
    }
}