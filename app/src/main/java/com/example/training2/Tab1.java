package com.example.training2;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.training2.db.DbHelper;
import com.example.training2.model.Mahasiswa;

import java.util.ArrayList;

public class Tab1 extends Fragment {

    public Tab1() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        return view;
    }

    private void prepareFragment(){
        this.getChildFragmentManager().
                beginTransaction()
                .add(R.id.list, new FragmentAddUser())
                .commit();
    }

    }
