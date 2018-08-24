package com.clidev.gamecodex.gamedetailscreen.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.clidev.gamecodex.gamedetailscreen.model.modeldata.Company;
import com.clidev.gamecodex.gamedetailscreen.model.modeldata.Platform;
import com.clidev.gamecodex.gamedetailscreen.view.adapters.TrailerRvAdapter;
import com.clidev.gamecodex.gamedetailscreen.view.adapters.TrailerSnapHelper;
import com.clidev.gamecodex.gamedetailscreen.view_model.CompanyViewModel;
import com.clidev.gamecodex.gamedetailscreen.view_model.CompanyViewModelFactory;
import com.clidev.gamecodex.gamedetailscreen.view_model.GameDetailsViewModel;
import com.clidev.gamecodex.gamedetailscreen.view_model.GameDetailsViewModelFactory;
import com.clidev.gamecodex.gamedetailscreen.view_model.PlatformViewModel;
import com.clidev.gamecodex.gamedetailscreen.view_model.PlatformViewModelFactory;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;
import com.clidev.gamecodex.populargamescreen.model.modeldata.ReleaseDate;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Video;
import com.clidev.gamecodex.populargamescreen.view.PopularGamesActivity;
import com.clidev.gamecodex.populargamescreen.view.PopularGamesFragment;
import com.clidev.gamecodex.utilities.ImageUrlEditor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class GameDetailsFragment extends Fragment {

    private Long mId;
    private GameDetailsViewModel mGameDetailsViewModel;
    private CompanyViewModel mCompanyViewModel;
    private PlatformViewModel mPlatformViewModel;
    private Integer mSearchTypeId;

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
    @BindView(R.id.game_detail_developer_loading) ProgressBar mDeveloperLoadingBar;
    @BindView(R.id.game_detail_platform_loading) ProgressBar mPlatformLoadingBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_details, container, false);

        ButterKnife.bind(this, rootView);

        clearViews();

        Intent intent = getActivity().getIntent();
        mSearchTypeId = intent.getIntExtra(PopularGamesFragment.SEARCH_TYPE_ID,0);
        mId = intent.getLongExtra(PopularGamesFragment.SELECTED_GAME_ID, 0);


        Timber.d("Selected game id is: " + mId);

        if (mId != 0) {
            loadGameData();
        }

        setSmallLoadingBarVisibility();


        return rootView;
    }

    private void setSmallLoadingBarVisibility() {
        // set small loading circl color and visibility
        mDeveloperLoadingBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
        mPlatformLoadingBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
        mDeveloperLoadingBar.setVisibility(View.INVISIBLE);
        mPlatformLoadingBar.setVisibility(View.INVISIBLE);
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
                mTrailerCover.setVisibility(View.VISIBLE);

                //setTrailerCover(game);

                setTrailerRecyclerView(game);

                setCoverPoster(game);

                setGameTitle(game);

                setDeveloperName(game);

                setPlatforms(game);

                //setFirstReleaseDate(game);
                setReleaseDate(game);

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
                mTrailerCover.setVisibility(View.INVISIBLE);
                //  display artwork
                String url = game.getArtworks().get(0).getUrl();
                setTrailerCoverImage(url);
            }
        } else if (game.getScreenShots() != null){
            if (!game.getScreenShots().isEmpty()) {
                mTrailerCover.setVisibility(View.INVISIBLE);
                // display screenshot
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
        if (game.getDevelopers() != null) {
            mDeveloperLoadingBar.setVisibility(View.VISIBLE);
            List<Long> developerIds = game.getDevelopers();

            // make retrofit call to get the developer names
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

                    mDeveloperLoadingBar.setVisibility(View.INVISIBLE);
                    mDeveloperText.setText(companyText);
                }
            });
        } else {
            mDeveloperText.setText("Developer: N/A");
        }

    }

    private void setPlatforms(Game game) {
        final String platform = "";
        if (game.getPlatforms() != null) {
            mPlatformLoadingBar.setVisibility(View.VISIBLE);
            List<Integer> platformIds = game.getPlatforms();

            // make retrofit call to get platform names
            PlatformViewModelFactory factory = new PlatformViewModelFactory(game);

            mPlatformViewModel = ViewModelProviders.of(this, factory).get(PlatformViewModel.class);

            mPlatformViewModel.getPlatformLiveData().observe(this, new Observer<List<Platform>>() {
                @Override
                public void onChanged(@Nullable List<Platform> platforms) {
                    String platformText = "";
                    for (Platform platform : platforms) {
                        // ONLY OUTPUT THE PLATFORM IF IT MATCHES ONE OF THE 4 APPROVED PLATFORM
                        if (platform.getId() == 6
                                || platform.getId() == 48
                                || platform.getId() == 49
                                || platform.getId() == 130) {

                            if (platformText.matches("")) {
                                platformText = platform.getName();
                            } else {
                                platformText = platformText + "\n" + platform.getName();
                            }
                        }
                    }

                    mPlatformLoadingBar.setVisibility(View.INVISIBLE);
                    mPlatformText.setText(platformText);
                }
            });
        } else {
            mPlatformText.setText("Platform: N/A");
        }
    }

    private void setFirstReleaseDate(Game game) {
        if (game.getFirstReleaseDate() != null ) {
            Long releaseDateInSec = game.getFirstReleaseDate();

            // convert to human format
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

            String date = simpleDateFormat.format(releaseDateInSec);

            mReleaseDateText.setText(date);
        } else {
            mReleaseDateText.setText("Release Date: N/A");
        }
    }

    private void setReleaseDate(Game game) {
        mReleaseDateText.setText("Release Date: N/A");
        if (game.getReleaseDates() != null) {
            if (game.getReleaseDates().isEmpty() == false) {

                // get required platform id based on the search method that was used
                Integer requiredPlatformId = 0;
                if (mSearchTypeId.equals(R.id.ps4_popular)
                        || mSearchTypeId.equals(R.id.ps4_rated)
                        || mSearchTypeId.equals(R.id.ps4_upcoming)) {
                    requiredPlatformId = 48; // PLAYSTATION 4
                } else if (mSearchTypeId.equals(R.id.xb1_popular)
                        || mSearchTypeId.equals(R.id.xb1_rated)
                        || mSearchTypeId.equals(R.id.xb1_upcoming)) {
                    requiredPlatformId = 49; // Xbox one
                } else if (mSearchTypeId.equals(R.id.switch_popular)
                        || mSearchTypeId.equals(R.id.switch_rated)
                        || mSearchTypeId.equals(R.id.switch_upcoming)) {
                    requiredPlatformId = 130; // Switch
                } else if (mSearchTypeId.equals(R.id.pc_popular)
                        || mSearchTypeId.equals(R.id.pc_rated)
                        || mSearchTypeId.equals(R.id.pc_upcoming)) {
                    requiredPlatformId = 6; // Switch
                }

                // create an equal sized list of just platform ids
                List<ReleaseDate> releaseDates = game.getReleaseDates();
                List<Integer> platformIds = new ArrayList<>();

                for (ReleaseDate releaseDate : releaseDates) {
                    Integer id = releaseDate.getPlatform();
                    platformIds.add(id);
                }

                // if the list of platformIds contains the required platform id, get the corresponding index.
                int index = -1;
                if (platformIds.contains(requiredPlatformId)) {
                    index = platformIds.indexOf(requiredPlatformId);
                }

                // if index is not invalid, get the corresponding releaseDate object
                ReleaseDate requiredReleaseDate;
                if (index != -1) {
                    requiredReleaseDate = releaseDates.get(index);

                    // get the release date in sec and then convert it to human time.
                    Long releaseDateInSec = requiredReleaseDate.getDate();

                    // convert to human format
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

                    if (releaseDateInSec != null) {
                        String date = simpleDateFormat.format(releaseDateInSec);
                        mReleaseDateText.setText(date);
                    }


                }

            }
        }



    }

    private void setAggregatedRating(Game game) {
        if (game.getAggregatedRating() != null) {
            String rating = "";
            Double ratingNumber = game.getAggregatedRating();
            rating = String.format("%.2f", ratingNumber) + "/100";

            mRatingText.setText(rating);
        } else {
            mRatingText.setText("Rating: N/A");
        }

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
