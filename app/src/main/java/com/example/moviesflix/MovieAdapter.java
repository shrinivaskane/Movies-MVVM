package com.example.moviesflix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieResponse> movieResponseList;
    private OnItemClickListener listener;
    private Context context;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public MovieAdapter() {
    }

    public void setData(List<MovieResponse> movieResponseList) {
         this.movieResponseList= movieResponseList;
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MovieResponse getItemAt(int position) {
        return movieResponseList.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        //return new MovieAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false));
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        MovieResponse movieResponse = movieResponseList.get(position);

        String title = movieResponse.getTitle();
        String prefix = "";

        holder.prefix.setText(prefix);
        holder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        if(movieResponseList==null){
            return 0;
        }
        else {

            return movieResponseList.size();
        }

    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView prefix;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            prefix = itemView.findViewById(R.id.prefix);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
