package com.example.training2;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.training2.db.DbHelper;
import com.example.training2.model.Mahasiswa;
import com.example.training2.model.MataKuliah;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.okhttp.internal.Internal;

import java.util.ArrayList;
import java.util.List;

public class ViewPage extends AppCompatActivity {

    EditText nim ;
    EditText nama;
    EditText telepon;
    TextView nim_txt ;
    TextView nama_txt;
    TextView telepon_txt;
    Button add;
    private Button buttonSimpan;

    private EditText mataKuliah;
    private EditText namaDosen;
    private EditText sks;

    DbHelper mydb;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore firebaseFirestoreDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        firebaseFirestoreDb = FirebaseFirestore.getInstance();

    }

    public void addUser(View view)
    {
        mydb = new DbHelper(this);
        nim = findViewById(R.id.nim);
        nama= findViewById(R.id.nama);
        telepon = findViewById(R.id.telepon);

        ArrayList<Mahasiswa> data = new ArrayList<Mahasiswa>();
        System.out.println(nim.getText().toString());
        System.out.println(nama.getText().toString());
        System.out.println(telepon.getText().toString());
        mydb.insertMahasiswa(nim.getText().toString(), nama.getText().toString(),
                telepon.getText().toString());

        data = mydb.getAllMahasiswa();

        recyclerView = findViewById(R.id.recycle_view_mahasiswa);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MahasiswaAdapter(data);
        recyclerView.setAdapter(adapter);

        System.out.println("clicked");
    }


}
