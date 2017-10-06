package com.intigral.test.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.intigral.test.R;
import com.intigral.test.SimpleHListActivity;
import com.intigral.test.interfaces.OnMovieItemClickListener;
import com.intigral.test.modal.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private List<Result> resultList;
    private OnMovieItemClickListener itemClickListener;
    Context context;

    public HorizontalAdapter(List<Result> resultList, Context activity) {
        this.resultList = resultList;
        this.context = activity;
        itemClickListener = (SimpleHListActivity) activity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500" + resultList.get(position).getPosterPath())
                .placeholder(R.drawable.ic_local_movies_black_24dp)
                .into(holder.ivItem);


        holder.ivItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onMovieItemClick(resultList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivItem;

        public MyViewHolder(View view) {
            super(view);
            ivItem = view.findViewById(R.id.iv_item);
        }
    }
}