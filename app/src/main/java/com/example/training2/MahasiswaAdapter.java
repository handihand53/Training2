package com.example.training2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.training2.model.Mahasiswa;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    private List<Mahasiswa> mahasiswaList;

    public MahasiswaAdapter(List<Mahasiswa> inputData) {
        mahasiswaList = inputData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_nama;
        public TextView txt_nim;
        public TextView txt_notlp;

        public ViewHolder(View v) {
            super(v);
            txt_nama = (TextView) v.findViewById(R.id.txt_nama);
            txt_nim = (TextView) v.findViewById(R.id.txt_nim);
            txt_notlp = (TextView) v.findViewById(R.id.txt_notlp);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_mahasiswa_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_nama.setText(mahasiswaList.get(position).getNama());
        holder.txt_nim.setText(mahasiswaList.get(position).getNim());
        holder.txt_notlp.setText(mahasiswaList.get(position).getNotlp());

//        System.out.println(mahasiswaList.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

}
