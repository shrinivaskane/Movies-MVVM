package com.example.moviesflix;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MovieDetailsFragment extends Fragment {

    //TextView titlemain, origintitle,romantitle,direct,produce,runtime,rate,rels,desc,url;
    MovieResponse movieResponse;
    TextView titlemain, origintitle,romantitle,direct,produce,runtime,rate,rels,desc,url;
    String maintitle,origin,roman,run,dir,prod,rt,des,urls,rel;
    Button btnback;
//    private  binding;


    public MovieDetailsFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titlemain= getView().findViewById(R.id.title);
        origintitle = getView().findViewById(R.id.original_title);
        romantitle  = getView().findViewById(R.id.original_title_romanised);
        desc=getView().findViewById(R.id.description);
        runtime=getView().findViewById(R.id.runningtime);
        rels=getView().findViewById(R.id.releasedate);
        rate=getView().findViewById(R.id.rtscore);
        direct=getView().findViewById(R.id.director);
        produce=getView().findViewById(R.id.producer);
        url=getView().findViewById(R.id.url);
        btnback=getView().findViewById(R.id.button);
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getSelected().observe(getViewLifecycleOwner(), item -> {

                    maintitle = item.getTitle();
                    origin = item.getOriginal_title();
                    roman = item.getOriginal_title_romanised();
                    run = item.getRunning_time();
                    dir = item.getDirector();
                    prod = item.getProducer();
                    rt = item.getRt_score();
                    des = item.getDescription();
                    urls = item.getUrl();
                    rel = item.getRelease_date();

            titlemain.setText(maintitle);
            origintitle.setText(origin);
            romantitle.setText(roman);
            runtime.setText(run);
            desc.setText(des);
            rels.setText(rel);
            rate.setText(rt);
            url.setText(urls);
            produce.setText(prod);
            direct.setText(dir);
                });


        btnback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack ("frag1", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

    }
}