package com.clidev.gamecodex.gamedetails.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.clidev.gamecodex.R;
import com.clidev.gamecodex.gamedetails.model.Company;
import com.clidev.gamecodex.gamedetails.view.adapters.TrailerRvAdapter;
import com.clidev.gamecodex.gamedetails.view.adapters.TrailerSnapHelper;
import com.clidev.gamecodex.gamedetails.view_model.CompanyViewModel;
import com.clidev.gamecodex.gamedetails.view_model.CompanyViewModelFactory;
import com.clidev.gamecodex.gamedetails.view_model.GameDetailsViewModel;
import com.clidev.gamecodex.gamedetails.view_model.GameDetailsViewModelFactory;
import com.clidev.gamecodex.populargames.model.modeldata.Game;
import com.clidev.gamecodex.populargames.model.modeldata.Video;
import com.clidev.gamecodex.populargames.view.PopularGamesActivity;
import com.clidev.gamecodex.utilities.ImageUrlEditor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class GameDetailsFragment extends Fragment {

    private Long mId;
    private GameDetailsViewModel mGameDetailsViewModel;
    private CompanyViewModel mCompanyViewModel;

    // View Fields
    @BindView(R.id.game_detail_trailer_rv) RecyclerView mTrailerRv;
    @BindView(R.id.game_detail_cover) ImageView mCoverImage;
    @BindView(R.id.game_detail_title) TextView mTitleText;
    @BindView(R.id.game_detail_developer) TextView mDeveloperText;
    @BindView(R.id.game_detail_platform) TextView mPlatformText;
    @BindView(R.id.game_detail_rating) TextView mRatingText;
    @BindView(R.id.game_detail_release_date) TextView mReleaseDateText;
    @BindView(R.id.game_detail_summary_title) TextView mSummaryTitle;
    @BindView(R.id.game_detail_summary_detail) TextView mSummaryText;
    @BindView(R.id.game_detail_loading_bar) ProgressBar mLoadingBar;
    @BindView(R.id.game_detail_trailer_cover) ImageView mTrailerCover;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_details, container, false);

        ButterKnife.bind(this, rootView);

        clearViews();

        Intent intent = getActivity().getIntent();
        mId = intent.getLongExtra(PopularGamesActivity.SELECTED_GAME_ID, 0);

        Timber.d("Selected game id is: " + mId);

        if (mId != 0) {
            loadGameData();
        }

        return rootView;
    }

    private void loadGameData() {
        mLoadingBar.setVisibility(View.VISIBLE);

        GameDetailsViewModelFactory factory = new GameDetailsViewModelFactory(mId);

        mGameDetailsViewModel = ViewModelProviders.of(this, factory).get(GameDetailsViewModel.class);

        mGameDetailsViewModel.getGame().observe(this, new Observer<Game>() {
            @Override
            public void onChanged(@Nullable Game game) {
                Timber.d("LiveData change observed");

                Timber.d("Game name: " + game.getName());

                mLoadingBar.setVisibility(View.INVISIBLE);

                //setTrailerCover(game);

                setTrailerRecyclerView(game);

                setCoverPoster(game);

                setGameTitle(game);

                setDeveloperName(game);

                setPlatforms(game);

                setFirstReleaseDate(game);

                setAggregatedRating(game);

                setSummary(game);

            }
        });
    }

    private void setTrailerCover(Game game) {
        if (game.getVideos() != null) {
            List<Video> videos = game.getVideos();
            if (videos != null && videos.isEmpty() != true) {
                final String videoId1 = videos.get(0).getVideoId();
                String coverUrl = "https://img.youtube.com/vi/" + videoId1 +"/0.jpg";

                RequestOptions options = new RequestOptions();
                options.centerCrop();
                options.override(500,300);
                options.placeholder(R.drawable.image_loading);

                Glide.with(getContext())
                        .load(coverUrl)
                        .apply(options)
                        .into(mTrailerCover);

                mTrailerCover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId1));
                        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://www.youtube.com/watch?v=" + videoId1));
                        try {
                            getContext().startActivity(appIntent);
                        } catch (ActivityNotFoundException ex) {
                            getContext().startActivity(webIntent);
                        }
                    }
                });

            }
        }
    }

    private void setTrailerRecyclerView(Game game) {
        if (game.getVideos() != null) {
            List<Video> videos = game.getVideos();
            if (videos != null && videos.isEmpty() != true) {
                mTrailerCover.setVisibility(View.INVISIBLE);

                // get a list of video cover image.
                List<String> videoImageUrl = new ArrayList<>();
                for (Video video : videos) {
                    String id = video.getVideoId();
                    String url = "https://img.youtube.com/vi/" + id +"/0.jpg";
                    videoImageUrl.add(url);
                }

                // pass this image url list and video list into the recycler view adapter
                TrailerRvAdapter trailerRvAdapter = new TrailerRvAdapter(getContext());
                LinearLayoutManager linearLayoutManager =
                        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                mTrailerRv.setAdapter(trailerRvAdapter);
                mTrailerRv.setLayoutManager(linearLayoutManager);
                TrailerSnapHelper trailerSnapHelper = new TrailerSnapHelper();
                trailerSnapHelper.attachToRecyclerView(mTrailerRv);

                trailerRvAdapter.setData(videos, videoImageUrl);

                return;
            }


            setBannerInsteadOfTrailer(game);

            return;
        }


        setBannerInsteadOfTrailer(game);


    }

    private void setBannerInsteadOfTrailer(Game game) {
        if (game.getArtworks() != null) {
            if (!game.getArtworks().isEmpty()) {
                // TODO: display screenshot
                String url = game.getArtworks().get(0).getUrl();
                setTrailerCoverImage(url);
            }
        } else if (game.getScreenShots() != null){
            if (!game.getScreenShots().isEmpty()) {
                // TODO: display artwork
                String url = game.getScreenShots().get(0).getUrl();
                setTrailerCoverImage(url);
            }
        }
    }

    private void setTrailerCoverImage(String url) {
        url = ImageUrlEditor.getBigCoverUrl(url);

        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.override(500,300);
        options.placeholder(R.drawable.image_loading);

        Glide.with(getContext())
                .load(url)
                .apply(options)
                .into(mTrailerCover);

        mTrailerCover.setVisibility(View.VISIBLE);
    }

    private void setCoverPoster(Game game) {
        String imageUrl = "";
        if (game.getCover() != null) {
            if (game.getCover().getUrl() != null) {
                imageUrl = game.getCover().getUrl();
                imageUrl = ImageUrlEditor.getBigCoverUrl(imageUrl);
            }
        }

        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.override(150,190);
        options.placeholder(R.drawable.image_loading);

        Glide.with(getContext())
                .load(imageUrl)
                .apply(options)
                .into(mCoverImage);
    }

    private void setGameTitle(Game game) {
        String title = "Title of this game isn't available!";
        if (game.getName() != null) {
            title = game.getName();
        }
        mTitleText.setText(title);
    }

    private void setDeveloperName(Game game) {
        String developer = "";
        if (game.getDevelopers() != null) {
            List<Long> developerIds = game.getDevelopers();

            // TODO: make retrofit call to get the developer names
            CompanyViewModelFactory factory = new CompanyViewModelFactory(developerIds);

            mCompanyViewModel = ViewModelProviders.of(this, factory).get(CompanyViewModel.class);

            mCompanyViewModel.getCompanyLiveData().observe(this, new Observer<List<Company>>() {
                @Override
                public void onChanged(@Nullable List<Company> companies) {
                    String companyText = "";
                    for (Company company : companies) {
                        if (companyText.matches("")) {
                            companyText = company.getName();
                        } else {
                            companyText = companyText + "," + company.getName();
                        }
                    }

                    mDeveloperText.setText(companyText);
                }
            });


        }

    }

    private void setPlatforms(Game game) {
        String platform = "";
        if (game.getPlatforms() != null) {
            List<Integer> platformIds = game.getPlatforms();

            // TODO: make retrofit call to get platform names
        }
    }

    private void setFirstReleaseDate(Game game) {
        String releaseDate = "";
        if (game.getFirstReleaseDate() != null ) {
            Long releaseDateInSec = game.getFirstReleaseDate();

            // TODO: convert to human format
        }
    }

    private void setAggregatedRating(Game game) {
        String rating = "";
        if (game.getAggregatedRating() != null) {
            Double ratingNumber = game.getAggregatedRating();
            rating = String.format("%.2f", ratingNumber) + "/100";
        }
        mRatingText.setText(rating);
    }

    private void setSummary(Game game) {
        String summary = "";
        if (game.getSummary() != null) {
            summary = game.getSummary();
        }
        mSummaryTitle.setText("Summary");
        mSummaryText.setText(summary);
    }

    private void clearViews() {
        mSummaryTitle.setText("");
        mSummaryText.setText("");
        mTitleText.setText("");
        mDeveloperText.setText("");
        mRatingText.setText("");
        mPlatformText.setText("");
        mReleaseDateText.setText("");

    }

}
