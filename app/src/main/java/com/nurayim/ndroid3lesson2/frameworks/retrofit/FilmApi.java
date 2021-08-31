package com.nurayim.ndroid3lesson2.frameworks.retrofit;

import com.nurayim.ndroid3lesson2.data.Films;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmApi {

    @GET("/films")
    Call<List<Films>> getFilms();
    // метод котрый стягиват все фильмы с бекэнда

    @GET ("/films/{id}")
    Call<Films> getFilmById(
            @Path("id") String filmId
    );

    //Этот  метод чтобы получить всех по айдишке
}
