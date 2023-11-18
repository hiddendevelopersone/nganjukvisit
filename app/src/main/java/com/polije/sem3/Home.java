package com.polije.sem3;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.polije.sem3.model.EventModel;
import com.polije.sem3.model.EventModelAdapter;
import com.polije.sem3.model.KulinerModel;
import com.polije.sem3.model.PenginapanModel;
import com.polije.sem3.model.RekomendasiKulinerAdapter;
import com.polije.sem3.model.RekomendasiPenginapanAdapter;
import com.polije.sem3.model.RekomendasiWisataAdapter;
import com.polije.sem3.model.WisataModel;
import com.polije.sem3.response.EventResponse;
import com.polije.sem3.response.KulinerResponse;
import com.polije.sem3.response.PenginapanResponse;
import com.polije.sem3.response.WisataResponse;
import com.polije.sem3.retrofit.Client;
import com.polije.sem3.util.UsersUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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

    private RekomendasiPenginapanAdapter adapter;
    private RekomendasiWisataAdapter adapter2;
    private RekomendasiKulinerAdapter adapter3;
    private EventModelAdapter adapter4;
    private ArrayList<WisataModel> wisataArraylist;
    private ArrayList<KulinerModel> KulinerArrayList;
    private ArrayList<EventModel> eventList;
    private ArrayList<PenginapanModel> penginapanList;
    private MaterialCardView catWisata, catKuliner, catPenginapan, catEvent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView namaPengguna;
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

//        getnamapengguna
        UsersUtil userUtil = new UsersUtil(requireContext());
        namaPengguna = (TextView) rootView.findViewById(R.id.namaLengkapPengguna);
        namaPengguna.setText("Halo! " + userUtil.getFullName());

        String idPengguna = userUtil.getId();

        // tombol kategori
        catWisata = rootView.findViewById(R.id.catWisata);
        catKuliner = rootView.findViewById(R.id.catKuliner);
        catPenginapan = rootView.findViewById(R.id.catHomestay);
        catEvent = rootView.findViewById(R.id.catEvent);

        // Temukan tombol berdasarkan ID
        CardView button = rootView.findViewById(R.id.showWisata);
        CardView buttonShowEvent = rootView.findViewById(R.id.showEvent);
        CardView buttonShowKuliner = rootView.findViewById(R.id.showKuliner);
        CardView buttonShowPenginapan = rootView.findViewById(R.id.showPenginapan);

        // atur tombol kategori
        catWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListWisata.class);
                startActivity(i);
            }
        });

        catKuliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListKuliner.class);
                startActivity(i);
            }
        });

        catPenginapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListPenginapan.class);
                startActivity(i);
            }
        });

        catEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListEvent.class);
                startActivity(i);
            }
        });

        // Atur OnClickListener pada tombol
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ketika tombol diklik, buat intent untuk menuju aktivitas baru
                Intent intent = new Intent(getActivity(), ListWisata.class); // Gantilah 'AktivitasTujuan' dengan aktivitas yang ingin Anda tuju
                startActivity(intent);
            }
        });

        buttonShowEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListEvent.class);
                startActivity(intent);
            }
        });

        buttonShowKuliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListKuliner.class);
                startActivity(intent);
            }
        });

        buttonShowPenginapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListPenginapan.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerviewListPenginapanRekomendasi);

        Client.getInstance().penginapanpopuler().enqueue(new Callback<PenginapanResponse>() {
            @Override
            public void onResponse(Call<PenginapanResponse> call, Response<PenginapanResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                    adapter = new RekomendasiPenginapanAdapter(response.body().getData());
                    recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(requireContext(), "data kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PenginapanResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "timeout", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView1 = rootView.findViewById(R.id.recyclerviewListWisataPopuler);

        Client.getInstance().wisatapopuler().enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                if(response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                    wisataArraylist = response.body().getData();
                    adapter2 = new RekomendasiWisataAdapter(wisataArraylist, new RekomendasiWisataAdapter.OnClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            startActivity(
                                    new Intent(requireContext(), DetailInformasi.class)
                                            .putExtra(DetailInformasi.ID_WISATA, wisataArraylist.get(position).getIdwisata())
                            );
                        }
                    });
                    recyclerView1.setAdapter(adapter2);
                } else {
                    Toast.makeText(requireContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Request Timeout", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView2 = rootView.findViewById(R.id.recyclerviewListKulinerPopuler);

        Client.getInstance().kulinerpopuler().enqueue(new Callback<KulinerResponse>() {
            @Override
            public void onResponse(Call<KulinerResponse> call, Response<KulinerResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                    KulinerArrayList = response.body().getData();
                    adapter3 = new RekomendasiKulinerAdapter(KulinerArrayList, new RekomendasiKulinerAdapter.OnClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            startActivity(
                                    new Intent(requireContext(), DetailKuliner.class)
                                            .putExtra(DetailKuliner.ID_KULINER, KulinerArrayList.get(position).getIdKuliner())
                            );
                        }
                    });
                    recyclerView2.setAdapter(adapter3);
                } else {
                    Toast.makeText(requireContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KulinerResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Request Timeout", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }
}