package com.clidev.gamecodex.populargames.view.adapters;

import android.content.Context;
import android.graphics.Color;
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
import com.clidev.gamecodex.utilities.HexColorArray;
import com.clidev.gamecodex.utilities.ImageUrlEditor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameListRvAdapter extends RecyclerView.Adapter<GameListRvAdapter.GameListViewHolder> {

    private Context mContext;
    private List<Game> mGameList = new ArrayList<>();
    private List<Genre> mGenreList;
    private List<Integer> mGenreIds;
    private List<String> mColorList;
    //private float mScreenWidth;

    // Constructor
    public GameListRvAdapter(Context context) {
        mContext = context;
    }

    // Custom PUBLIC methods /////////////////////////////////////////
    public void setGameList(List<Game> gameList) {
        if (gameList != null && gameList.isEmpty() != true) {
            mGameList = gameList;
            notifyDataSetChanged();

            // setup color list.
            setupColorList(gameList);
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
        loadImage(holder, position);

        // Load game title
        loadGameTitle(holder, position);

        // Load game genre (do later)
        loadGameGenre(holder, position);

        // Load description box color
        loadDescriptionBoxColor(holder, position);


    }


    @Override
    public int getItemCount() {
        return mGameList.size();
    }


    // HELPER PRIVATE METHODS //////////////////////////////////////////////////////////////////
    private void loadGameGenre(@NonNull GameListViewHolder holder, int position) {
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

    private void loadGameTitle(@NonNull GameListViewHolder holder, int position) {
        String title = mGameList.get(position).getName();
        holder.mGameTitle.setText(title);
    }

    private void loadImage(@NonNull GameListViewHolder holder, int position) {
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
    }

    private void loadDescriptionBoxColor(@NonNull GameListViewHolder holder, int position) {
        holder.mDescriptionBox.setBackgroundColor(Color.parseColor(mColorList.get(position)));
    }

    private void setupColorList(List<Game> gameList) {
        mColorList = new ArrayList<>();

        for (int i= 0 ; i < gameList.size(); i++) {

            if (i == 0) { //  if position = 0, pick random color
                mColorList.add(HexColorArray.getRandomColor());
            } else if (i == 1) { //  else, pick a random color, and check if it is the same as previous color,
                String randomColor = HexColorArray.getRandomColor();

                // while the chosen color is the same as previous color, keep looping until u choose a different color
                while (isColorSame(randomColor, mColorList.get(i - 1)) == true) {
                    randomColor = HexColorArray.getRandomColor();
                }

                mColorList.add(randomColor);
            } else {
                String randomColor = HexColorArray.getRandomColor();

                // while the chosen color is the same as 2 previous colors, keep looping until u choose a different color
                while (isColorSame(randomColor, mColorList.get(i - 1)) == true ||
                        isColorSame(randomColor, mColorList.get(i - 2))) {
                    randomColor = HexColorArray.getRandomColor();
                }

                mColorList.add(randomColor);
            }

        }
    }

    private boolean isColorSame(String chosenColor, String previousColor) {
        if (chosenColor.matches(previousColor)) {
            return true;
        }
        return false;
    }
    // .......................................................................



    public class GameListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.rv_game_cover_image) ImageView mGameCover;
        @BindView(R.id.rv_description_box) LinearLayout mDescriptionBox;
        @BindView(R.id.rv_game_title_text) TextView mGameTitle;
        @BindView(R.id.rv_game_genre_text) TextView mGameGenre;


        public GameListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            /*
            float imageWidth = (mScreenWidth / 2) - 2 - 4;
            float imageHeight = imageWidth * 1.4f;
            */

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
