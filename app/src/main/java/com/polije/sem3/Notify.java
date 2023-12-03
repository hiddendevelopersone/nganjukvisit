package com.polije.sem3;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.polije.sem3.model.NotifyAdapter;
import com.polije.sem3.model.NotifyModelNew;
import com.polije.sem3.response.NotifyResponse;
import com.polije.sem3.retrofit.Client;
import com.polije.sem3.util.UsersUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Notify#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Notify extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Notify() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Notify.
     */
    // TODO: Rename and change types and number of parameters
    public static Notify newInstance(String param1, String param2) {
        Notify fragment = new Notify();
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

    private NotifyAdapter adapter;
    private ArrayList<NotifyModelNew> arrayList;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notify, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recviewNotify);

        UsersUtil usersUtil = new UsersUtil(getContext());
        Client.getInstance().mynotif(usersUtil.getId()).enqueue(new Callback<NotifyResponse>() {
            @Override
            public void onResponse(Call<NotifyResponse> call, Response<NotifyResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                    arrayList = response.body().getData();
                    adapter = new NotifyAdapter(arrayList);

                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("error null", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<NotifyResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Request Timeout", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Log.e("failure", t.getMessage());
            }
        });

        return rootView;
    }
}