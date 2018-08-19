package com.clidev.gamecodex.gamedetails.view.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.clidev.gamecodex.R;
import com.clidev.gamecodex.populargames.model.modeldata.Video;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerRvAdapter extends RecyclerView.Adapter<TrailerRvAdapter.TrailerViewHolder> {

    private List<Video> mVideos;
    private List<String> mTrailerUrls;
    private Context mContext;


    public TrailerRvAdapter(Context context) {
        mContext = context;
        mVideos = new ArrayList<>();
        mTrailerUrls = new ArrayList<>();
    }

    public void setData(List<Video> videos, List<String> trailerUrls) {
        if (videos != null && trailerUrls != null) {
            mVideos = videos;
            mTrailerUrls = trailerUrls;
        }
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_trailer_cover, parent, false);

        TrailerViewHolder viewHolder = new TrailerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        // Set the image
        if (position != RecyclerView.NO_POSITION) {
            String imageUrl = "";
            imageUrl = mTrailerUrls.get(position);

            RequestOptions options = new RequestOptions();
            options.centerCrop();
            options.override(500,300);
            options.placeholder(R.drawable.image_loading);

            Glide.with(mContext)
                    .load(imageUrl)
                    .apply(options)
                    .into(holder.mTrailerCover);
        } else {
            Glide.with(mContext).clear(holder.mTrailerCover);
            holder.mTrailerCover.setImageDrawable(null);
        }

        // set the arrows
        if (position == 0) {
            if (mVideos.size() > 1) {
                holder.mTrailerRight.setVisibility(View.VISIBLE);
                holder.mTrailerLeft.setVisibility(View.INVISIBLE);
            } else {
                holder.mTrailerRight.setVisibility(View.INVISIBLE);
                holder.mTrailerLeft.setVisibility(View.INVISIBLE);
            }
        } else if (position == mVideos.size()-1) {
            holder.mTrailerRight.setVisibility(View.INVISIBLE);
            holder.mTrailerLeft.setVisibility(View.VISIBLE);
        } else {
            holder.mTrailerRight.setVisibility(View.VISIBLE);
            holder.mTrailerLeft.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }





    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.rv_trailer_cover) ImageView mTrailerCover;
        @BindView(R.id.rv_trailer_right) ImageView mTrailerRight;
        @BindView(R.id.rv_trailer_left) ImageView mTrailerLeft;

        public TrailerViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + mVideos.get(position).getVideoId()));
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + mVideos.get(position).getVideoId()));
            try {
                mContext.startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                mContext.startActivity(webIntent);
            }

        }
    }
}
