package com.nurayim.ndroid3lesson2.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nurayim.ndroid3lesson2.R;
import com.nurayim.ndroid3lesson2.data.Films;
import com.nurayim.ndroid3lesson2.databinding.FragmentDetailBinding;
import com.nurayim.ndroid3lesson2.frameworks.retrofit.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {

    private String filmId;
    private FragmentDetailBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            filmId = getArguments().getString("key");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RetrofitBuilder.getInstance().getFilmById(filmId).enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                if (response.isSuccessful()&& response.body() != null){
                    binding.textTitleDetail.setText(response.body().getTitle());
                    binding.textDirectorDetail.setText(response.body().getProducer());
                    binding.textDesc.setText(response.body().getDescription());
                    Log.d("tag", "detail" + response.body().getTitle());
                }else {
                    Log.d("tag", "detail" + response.code());
                }


            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {

            }
        });
    }
}