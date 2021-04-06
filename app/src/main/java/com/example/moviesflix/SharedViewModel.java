package com.example.moviesflix;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<MovieResponse> selected = new MutableLiveData<>();

    public void setSelected(MovieResponse item) {
        selected.setValue(item);
    }

    public MutableLiveData<MovieResponse> getSelected() {
        return selected;
    }
}
