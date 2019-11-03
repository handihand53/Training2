package com.example.training2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

public class MataKuliahAdapter extends RecyclerView.Adapter<MataKuliahAdapter.ViewHolder> {
    private List<MataKuliah> mataKuliahList;
    FirebaseFirestore firebaseFirestoreDb;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    TextView textView;
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
        final String id = mataKuliahList.get(position).getId();
        final String matkul = mataKuliahList.get(position).getMatkul();
        final String namaDosen = mataKuliahList.get(position).getNamaDosen();
        final int sks = mataKuliahList.get(position).getSks();
        System.out.println(id);
        holder.txt_matkul.setText(mataKuliahList.get(position).getMatkul());
        holder.txt_matkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                deleteDataMahasiswa(v,id);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
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

    private void deleteDataMahasiswa(final View v, String id) {
//        firebaseFirestoreDb.database().ref('complaintsPothole').orderByChild('complaintId').equalTo('-LO1M0u_xW4MrolCTwrg').remove();
        firebaseFirestoreDb.collection("DaftarMatkul").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(v.getContext(), "Mahasiswa berhasil dihapus",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(v.getContext(), "Error deleting document: " + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public int getItemCount() {
        return mataKuliahList.size();
    }
}
