package com.clidev.gamecodex.populargames.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.clidev.gamecodex.R;
import com.clidev.gamecodex.populargames.model.PopularGamesRepository;
import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.model.room.Genre;
import com.clidev.gamecodex.utilities.ImageUrlEditor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameListRvAdapter extends RecyclerView.Adapter<GameListRvAdapter.GameListViewHolder> {

    private Context mContext;
    private List<Game> mGameList;
    private List<Genre> mGenreList;
    private List<Integer> mGenreIds;
    private float mScreenWidth;

    // Constructor
    public GameListRvAdapter(Context context, float screenWidth) {
        mContext = context;
        mScreenWidth = screenWidth;
    }

    // Custom methods /////////////////////////////////////////
    public void setGameList(List<Game> gameList) {
        if (gameList != null && gameList.isEmpty() != true) {
            mGameList = gameList;
            notifyDataSetChanged();
        }
    }

    public void setGenreList(List<Genre> genreList) {
        if (genreList != null && genreList.isEmpty() != true) {
            mGenreList = genreList;

            List<Integer> genreIds = new ArrayList<>();
            for (Genre genre : mGenreList) {
                genreIds.add(genre.getId());
            }

            mGenreIds = genreIds;

            notifyDataSetChanged();
        }
    }

    //.......................................

    @NonNull
    @Override
    public GameListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_popular_movies, parent, false);

        GameListViewHolder viewHolder = new GameListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GameListViewHolder holder, int position) {
        // Load image
        if (position != RecyclerView.NO_POSITION) {

            // Edit image url
            String imageUrl = "";
            if (mGameList.get(position).getCover() != null) {
                if (mGameList.get(position).getCover().getUrl() != null) {
                    imageUrl = mGameList.get(position).getCover().getUrl();
                    imageUrl = ImageUrlEditor.getBigCoverUrl(imageUrl);
                }
            }

            // Set the property of glide loading
            RequestOptions options = new RequestOptions();

            // load image with glide
            Glide.with(mContext)
                    .load(imageUrl)
                    .apply(options)
                    .into(holder.mGameCover);
        } else {
            Glide.with(mContext).clear(holder.mGameCover);
            holder.mGameCover.setImageDrawable(null);
        }

        // Load game title
        String title = mGameList.get(position).getName();
        holder.mGameTitle.setText(title);

        // Load game genre (do later)
        if (mGenreList != null && mGenreList.isEmpty() != true) {
            if (mGameList.get(position).getGenres() != null) {
                List<Integer> genreIds = mGameList.get(position).getGenres();

                String genreString = "";

                for (Integer genreId : genreIds) {
                    Integer index = mGenreIds.indexOf(genreId);

                    // only if a match is found, and the genre is not null
                    if (index >= 0 && mGenreList.get(index) != null) {
                        if (genreString.matches("")) {
                            genreString = mGenreList.get(index).getName();
                        } else {
                            genreString = genreString + ", " + mGenreList.get(index).getName();
                        }
                    }
                }

                holder.mGameGenre.setText(genreString);
            }
        }

    }

    @Override
    public int getItemCount() {
        return mGameList.size();
    }


    public class GameListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.rv_game_cover_image) ImageView mGameCover;
        @BindView(R.id.rv_description_box) LinearLayout mDescriptionBox;
        @BindView(R.id.rv_game_title_text) TextView mGameTitle;
        @BindView(R.id.rv_game_genre_text) TextView mGameGenre;


        public GameListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            float imageWidth = (mScreenWidth / 2) - 2 - 4;
            float imageHeight = imageWidth * 1.4f;

            /*
            mGameCover.getLayoutParams().height = (int) imageHeight;
            mGameCover.getLayoutParams().width = (int) imageWidth;
            mGameCover.requestLayout();
            */

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
