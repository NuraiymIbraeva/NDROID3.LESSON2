package com.nurayim.ndroid3lesson2.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nurayim.ndroid3lesson2.R;
import com.nurayim.ndroid3lesson2.data.Films;
import com.nurayim.ndroid3lesson2.databinding.FragmentFilmListBinding;
import com.nurayim.ndroid3lesson2.frameworks.retrofit.FilmApi;
import com.nurayim.ndroid3lesson2.frameworks.retrofit.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FilmListFragment extends Fragment implements FilmAdapter.FilmCallBack {

    private FragmentFilmListBinding binding;
    private final FilmAdapter adapter = new FilmAdapter(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        getFilmFromRetrofit();
    }

    private void getFilmFromRetrofit() {
        RetrofitBuilder.getInstance().getFilms().enqueue(new Callback<List<Films>>() {
            @Override
            public void onResponse(Call<List<Films>> call, Response<List<Films>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.addFilms(response.body());
                    Log.d("tag", "success" + response.body());
                } else {
                    Log.d("tag", "error" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Films>> call, Throwable t) {
                Log.d("tag", "failure" + t.getLocalizedMessage());
            }
        });
    }

    private void initViews(View view) {
        binding.recyclerview.setAdapter(adapter);
    }

    @Override
    public void onClick(Films films) {
        NavController navController = Navigation.findNavController(requireActivity(),R.id.fragment);
        Bundle bundle = new Bundle();
        bundle.putString("key",films.getId());
        Log.d("tag","id"+films.getId());
        navController.navigate(R.id.detailFragment,bundle);
    }
}