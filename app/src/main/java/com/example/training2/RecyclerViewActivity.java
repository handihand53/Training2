package com.example.training2;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.training2.model.Film;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Film> filmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        initDataset();

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view_main);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecycleAdapter(filmList);
        recyclerView.setAdapter(adapter);
    }

    private void initDataset(){
        filmList = new ArrayList<Film>(Arrays.asList(
                new Film("Marmut merah jambu", "2019"),
                new Film("Danur", "2013"),
                new Film("Tiga", "2020"),
                new Film("Aku dan Kamu", "2015")
        ));
    }
}
