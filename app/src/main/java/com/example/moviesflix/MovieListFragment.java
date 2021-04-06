package com.example.moviesflix;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieListFragment extends Fragment {

    Toolbar toolbar;
    MovieAdapter adapter;
    private MovieDetailsFragment detailsFragment = new MovieDetailsFragment();
    List<MovieResponse> movieResponses;
    RecyclerView recyclerView;


    public MovieListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        adapter = new MovieAdapter();
        getAllMovies();





    }

    public void getAllMovies() {

        Call<List<MovieResponse>> movielist = ApiClient.getUserService().getAllMovies();

        movielist.enqueue(new Callback<List<MovieResponse>>() {
            @Override
            public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {

                if (response.isSuccessful()) {
                    Log.i("Response Successful",response.body().toString());
                    movieResponses = response.body();
                    adapter.setData(movieResponses);
                    recyclerView.setAdapter(adapter);
                    SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

                    adapter.setListener((v, position) -> {
                        viewModel.setSelected(adapter.getItemAt(position));
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.container, detailsFragment)
                                .addToBackStack("frag1")
                                .commit();
                    });

                };

            }

            @Override
            public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });
    }
}
