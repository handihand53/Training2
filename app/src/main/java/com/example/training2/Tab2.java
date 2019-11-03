package com.example.training2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.training2.model.MataKuliah;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Tab2 extends Fragment {

    private EditText mataKuliah;
    private EditText namaDosen;
    private EditText sks;
    private Button buttonSimpan;
    private Button buttonHapus;
    FirebaseFirestore firebaseFirestoreDb;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    public Tab2() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        mataKuliah = view.findViewById(R.id.mataKuliah);
        namaDosen = view.findViewById(R.id.namaDosen);
        sks = view.findViewById(R.id.Sks);
        recyclerView = view.findViewById(R.id.recycle_view_matkul);
        buttonSimpan = view.findViewById(R.id.simpanButton);
        buttonHapus = view.findViewById(R.id.hapusButton);
        buttonHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDataMahasiswa();
            }
        });


        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sanity check
                if (!mataKuliah.getText().toString().isEmpty() && !namaDosen.getText().toString().isEmpty()&& !sks.getText().toString().isEmpty()) {
                    tambahMatakuliah();
                } else {
                    Toast.makeText(requireActivity(), "Matkul, Nama dosen, dan sks tidak boleh kosong",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    private void tambahMatakuliah() {

        MataKuliah matkul = new MataKuliah(mataKuliah.getText().toString(),
                namaDosen.getText().toString(),
                Integer.parseInt(sks.getText().toString()));

        firebaseFirestoreDb.collection("DaftarMatkul").document().set(matkul)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mataKuliah.setText("");
                        namaDosen.setText("");
                        sks.setText("");
                        Toast.makeText(requireActivity(), "Matkul berhasil didaftarkan",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireActivity(), "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
        getDataMataKuliah();
    }



    private void getDataMataKuliah() {
        final ArrayList<MataKuliah> dataMatkul = new ArrayList<MataKuliah>();
        System.out.println("========================================================================================");
        System.out.println("terpanggil");
        Task<QuerySnapshot> docRef = firebaseFirestoreDb.collection("DaftarMatkul")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        MataKuliah mataKuliah = new MataKuliah();
                        mataKuliah.setMatkul((String) document.get("matkul").toString());
                        mataKuliah.setNamaDosen((String) document.get("namaDosen").toString());
                        mataKuliah.setSks(((Long) document.get("sks")).intValue());
                        dataMatkul.add(mataKuliah);
                        System.out.println(document.get("matkul"));
                        System.out.println(document.get("namaDosen"));
                        System.out.println(document.get("sks"));
                    }
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setNestedScrollingEnabled(false);

                    layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);

                    adapter = new MataKuliahAdapter(dataMatkul);
                    recyclerView.setAdapter(adapter);

//                    Log.d(TAG, list.toString());
                } else {
//                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }

    private void deleteDataMahasiswa() {
        firebaseFirestoreDb.collection("DaftarMatkul").document()
            .delete()
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    mataKuliah.setText("");
                    namaDosen.setText("");
                    sks.setText("");
                    Toast.makeText(requireActivity(), "Mahasiswa berhasil dihapus",
                            Toast.LENGTH_SHORT).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(requireActivity(), "Error deleting document: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });
    }
}
