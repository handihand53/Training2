package com.example.training2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.training2.model.MataKuliah;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MataKuliahAdapter extends RecyclerView.Adapter<MataKuliahAdapter.ViewHolder> {
    private List<MataKuliah> mataKuliahList;
    FirebaseFirestore firebaseFirestoreDb;

    public MataKuliahAdapter(List<MataKuliah> inputData) {
        mataKuliahList = inputData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_matakuliah_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        System.out.println("============================================masuk============================================");
//        getDataMataKuliah();
        System.out.println("============================================keluar====================================================");
        return vh;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        System.out.println("ada");
        holder.txt_matkul.setText(mataKuliahList.get(position).getMatkul());
        holder.txt_nama_dosen.setText(mataKuliahList.get(position).getNamaDosen());
        holder.txt_sks.setText(Integer.toString(mataKuliahList.get(position).getSks()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_matkul;
        public TextView txt_nama_dosen;
        public TextView txt_sks;

        public ViewHolder(View v) {
            super(v);
            txt_matkul = (TextView) v.findViewById(R.id.txt_matkul);
            txt_nama_dosen = (TextView) v.findViewById(R.id.txt_nama_dosen);
            txt_sks = (TextView) v.findViewById(R.id.txt_sks);
        }
    }

    @Override
    public int getItemCount() {
        return mataKuliahList.size();
    }
}
