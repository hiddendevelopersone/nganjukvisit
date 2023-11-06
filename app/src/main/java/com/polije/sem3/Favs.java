package com.polije.sem3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.polije.sem3.model.FavoritWisataModelAdapter;
import com.polije.sem3.model.FavoritWisataModel;
import com.polije.sem3.model.WisataModelAdapter;
import com.polije.sem3.response.FavoritWisataResponse;
import com.polije.sem3.retrofit.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Favs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Favs extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Favs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Favs.
     */
    // TODO: Rename and change types and number of parameters
    public static Favs newInstance(String param1, String param2) {
        Favs fragment = new Favs();
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


    private RecyclerView recyclerView;
    private FavoritWisataModelAdapter adapter;
    private ArrayList<FavoritWisataModel> WisataArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favs, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerviewListWisataFavorit);

        Client.getInstance().favwisata("U1000013").enqueue(new Callback<FavoritWisataResponse>() {
            @Override
            public void onResponse(Call<FavoritWisataResponse> call, Response<FavoritWisataResponse> response) {
                adapter = new FavoritWisataModelAdapter(response.body().getData());

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<FavoritWisataResponse> call, Throwable t) {

            }
        });

        return rootView;
    }
}