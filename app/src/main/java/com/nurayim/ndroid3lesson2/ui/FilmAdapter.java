package com.nurayim.ndroid3lesson2.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nurayim.ndroid3lesson2.data.Films;
import com.nurayim.ndroid3lesson2.databinding.FilmListBinding;
import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    List<Films> films = new ArrayList<>();
    private FilmCallBack callBack; // интрефейс вызвали
    private FilmListBinding binding;

    public FilmAdapter(FilmCallBack callBack) {
        this.callBack = callBack;
    }

    public FilmAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = FilmListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding,callBack);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void addFilms(List<Films> film) {
        films.addAll(film);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private FilmCallBack callback;
        private FilmListBinding binding;

        public ViewHolder(@NonNull FilmListBinding itemView, FilmCallBack callback) {
            super(itemView.getRoot());
            this.callback = callback;
            this.binding = itemView;
           // this.binding = binding;
        }

        public void bind(Films films) {
            binding.textTitleFilm.setText(films.getTitle());
            binding.textDirectorFilm.setText(films.getProducer());
            binding.textTitleFilm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onClick(films);
                }
            });
        }
    }

    public interface FilmCallBack{
        void onClick(Films films);
    }
}
