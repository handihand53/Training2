package com.example.training2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.training2.model.Film;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private List<Film> filmList;

    public RecycleAdapter(List<Film> inputData) {
        filmList = inputData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView judulFilm;
        public TextView tahunFilm;

        public ViewHolder(View v) {
            super(v);
            judulFilm = (TextView) v.findViewById(R.id.judul_film);
            tahunFilm = (TextView) v.findViewById(R.id.tahun_film);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.judulFilm.setText(filmList.get(position).getJudul());
        holder.tahunFilm.setText("Tahun " + filmList.get(position).getTahun());
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }
}
