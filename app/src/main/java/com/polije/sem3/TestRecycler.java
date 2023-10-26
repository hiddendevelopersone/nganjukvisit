package com.polije.sem3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.polije.sem3.model.WisataModel;
import com.polije.sem3.model.WisataModelAdapter;

import java.util.ArrayList;

public class TestRecycler extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WisataModelAdapter adapter;
    private ArrayList<WisataModel> WisataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testrecycler);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        adapter = new WisataModelAdapter(WisataArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TestRecycler.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    void addData() {
        WisataArrayList = new ArrayList<>();
        WisataArrayList.add(new WisataModel("Sedudo", "Sedudo adalah bla bla bla bla"));
        WisataArrayList.add(new WisataModel("Taman Nyawiji", "Taman Nyawiji adalah bla bla bla bla"));
    }
}