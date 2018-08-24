package com.clidev.gamecodex.populargamescreen.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.clidev.gamecodex.R;
import com.clidev.gamecodex.constants.SearchTypeConstants;
import com.clidev.gamecodex.gamedetailscreen.view.GameDetailsActivity;
import com.clidev.gamecodex.populargamescreen.model.modeldata.Game;
import com.clidev.gamecodex.room.entities.Genre;
import com.clidev.gamecodex.room.database.GenreDatabase;
import com.clidev.gamecodex.populargamescreen.view.adapters.GameListRvAdapter;
import com.clidev.gamecodex.populargamescreen.view_model.GenreViewModel;
import com.clidev.gamecodex.populargamescreen.view_model.GenreViewModelFactory;
import com.clidev.gamecodex.populargamescreen.view_model.PopularGamesViewModel;
import com.clidev.gamecodex.populargamescreen.view_model.PopularGamesViewModelFactory;
import com.clidev.gamecodex.utilities.DpAndPxConversion;
import com.clidev.gamecodex.utilities.GridSpacingItemDecoration;
import com.clidev.gamecodex.utilities.NavHeadImageRandomizer;
import com.clidev.gamecodex.utilities.NetworkUtilities;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class PopularGamesFragment extends Fragment implements GameListRvAdapter.ItemClickHandler{

    public static final String SELECTED_GAME_ID = "SELECTED_GAME_ID";
    public static final String SEARCH_TYPE_ID = "SEARCH_TYPE_ID"   ;

    private GameListRvAdapter mGameListRvAdapter;
    private GridLayoutManager mGridLayoutManager;
    private List<Genre> mGenres = new ArrayList<>();
    private PopularGamesViewModel mPopViewModel;
    private Observer<List<Game>> mObserver;
    private ActionBar mActionBar;
    private Integer mSearchTypeId;
    private int spanCount = 2;
    private float spacingInDp = 2;
    private float spacingInPx;
    private boolean includeEdge = false;

    //private boolean notYetFirstLoad = true;

    @BindView(R.id.popular_games_rv) RecyclerView mGameListRv;
    @BindView(R.id.loading_bar) ProgressBar mLoadingBar;
    @BindView(R.id.drawer_navigation_view) NavigationView mNavigationView;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //mDrawerLayout.openDrawer(GravityCompat.START);
              //  Timber.d("Home Button Pressed");
                mDrawerLayout.openDrawer(GravityCompat.START, true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_popular_games, container, false);

        ButterKnife.bind(this, rootView);

        spacingInPx = DpAndPxConversion.convertDpToPixel(spacingInDp, getContext());

        mLoadingBar.setVisibility(View.VISIBLE);

        initiatePopularGameViewModel(savedInstanceState);

        setNavigationDrawer(rootView);

        setNavHeadImage();

        prepareRecyclerView();

        loadGameGenres();

        loadPopularGames();

        loadActionBarTitle();

        return rootView;
    }


    private void initiatePopularGameViewModel(@Nullable Bundle savedInstanceState) {
        // Create popular games view model
        PopularGamesViewModelFactory factory = new PopularGamesViewModelFactory();

        mPopViewModel = ViewModelProviders.of(this, factory).get(PopularGamesViewModel.class);
    }


    // SETTING UP NAVIGATION DRAWER AND ITS FUNCTIONALITY ////////////////////////////////////////////////////////////
    private void setNavigationDrawer(View rootView) {
        // setting the navigation drawer
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        setHasOptionsMenu(true);




        mNavigationView = rootView.findViewById(R.id.drawer_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                resetScreen();

                mPopViewModel.downloadGames(item.getItemId());

                mPopViewModel.getGameList().observe(PopularGamesFragment.this, mObserver);

                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }


    private void resetScreen() {
        // turn on progressbar
        mLoadingBar.setVisibility(View.VISIBLE);

        // clear scroll count in the view model
        mPopViewModel.resetViewModel();

        // remove the previous observer
        mPopViewModel.getGameList().removeObserver(mObserver);

        // clear recyclerview adapter
        mGameListRvAdapter.clearGameList();

        // set a different nav head image
        setNavHeadImage();
    }

    //..................................................................................................................

    private void setNavHeadImage() {
        View navView = mNavigationView.getHeaderView(0);
        ImageView headImage = navView.findViewById(R.id.nav_head_image);

        // Set the property of glide loading
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.override(300,200);

        // load image with glide
        Glide.with(getContext())
                .load(NavHeadImageRandomizer.getRandomImage())
                .apply(options)
                .into(headImage);
    }


    private void prepareRecyclerView() {
        mGameListRvAdapter = new GameListRvAdapter(getContext(), this);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mGridLayoutManager = new GridLayoutManager(getContext(), 2,
                    GridLayoutManager.VERTICAL, false);
        } else {
            mGridLayoutManager = new GridLayoutManager(getContext(), 4,
                    GridLayoutManager.VERTICAL, false);
        }

        mGameListRv.setAdapter(mGameListRvAdapter);
        mGameListRv.setLayoutManager(mGridLayoutManager);

        mGameListRv.addItemDecoration(new GridSpacingItemDecoration(spanCount, (int) spacingInPx, includeEdge));

        mGameListRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                // Total items in the list
                int totalItemCount = mGridLayoutManager.getItemCount();
                Timber.d("Total item count: " + totalItemCount);

                // the position of the last visible item on the list;
                int lastVisiblePosition = mGridLayoutManager.findLastVisibleItemPosition();
                //Timber.d("Current scroll position's last item: " + lastVisiblePosition);

                mPopViewModel.scrollDetected(totalItemCount, lastVisiblePosition);
            }
        });
    }


    private void loadGameGenres() {
        // Destroys database.
        //getActivity().getApplicationContext().deleteDatabase(GenreDatabase.DATABASE_NAME);
        GenreViewModelFactory factory = new GenreViewModelFactory();

        final GenreViewModel genreViewModel = ViewModelProviders.of(this, factory).get(GenreViewModel.class);

        GenreDatabase genreDatabase = GenreDatabase.getInstance(getContext());

        genreViewModel.updateGenreList(genreDatabase);

        genreViewModel.getGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(@Nullable List<Genre> genres) {
             //   Timber.d("Genre list successfully loaded");
                mGenres = genres;

                mGameListRvAdapter.setGenreList(mGenres);

                genreViewModel.getGenres().removeObserver(this);
            }
        });

    }


    private void loadPopularGames() {

        mPopViewModel.downloadGames(R.id.ps4_popular);

        mObserver = new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> gameList) {
                // populate the RecyclerView
                populateRecyclerView(gameList);

                mLoadingBar.setVisibility(View.INVISIBLE);

            }
        };

        mPopViewModel.getGameList().observe(this, mObserver);
    }

    private void populateRecyclerView(List<Game> gameList) {
        // Determining screen width
        //DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        //float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        mGameListRvAdapter.setGameList(gameList);
    }

    private void loadActionBarTitle() {
        mPopViewModel.getSearchTypeLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mSearchTypeId = integer;
                switch (integer) {
                    case R.id.ps4_popular:
                        mActionBar.setTitle("Most Popular - PS4");
                        break;

                    case R.id.ps4_rated:
                        mActionBar.setTitle("Highest Rated - PS4");
                        break;

                    case R.id.ps4_upcoming:
                        mActionBar.setTitle("Upcoming - PS4");
                        break;

                    case R.id.xb1_popular:
                        mActionBar.setTitle("Most Popular - XBONE");
                        break;

                    case R.id.xb1_rated:
                        mActionBar.setTitle("Highest Rated - XBONE");
                        break;

                    case R.id.xb1_upcoming:
                        mActionBar.setTitle("Upcoming - XBONE");
                        break;

                    case R.id.switch_popular:
                        mActionBar.setTitle("Most Popular - SWITCH");
                        break;

                    case R.id.switch_rated:
                        mActionBar.setTitle("Highest Rated - SWITCH");
                        break;

                    case R.id.switch_upcoming:
                        mActionBar.setTitle("Upcoming - SWITCH");
                        break;

                    case R.id.pc_popular:
                        mActionBar.setTitle("Most Popular - PC");
                        break;

                    case R.id.pc_rated:
                        mActionBar.setTitle("Highest Rated - PC");
                        break;

                    case R.id.pc_upcoming:
                        mActionBar.setTitle("Upcoming - PC");
                        break;

                    default:
                        break;
                }
            }
        });
    }


    // on recyclerview item clicked
    @Override
    public void onGameItemClicked(Game game) {
        Intent intent = new Intent(getContext(), GameDetailsActivity.class);
        Long gameId = game.getId();
        intent.putExtra(SEARCH_TYPE_ID, mSearchTypeId);
        intent.putExtra(SELECTED_GAME_ID, gameId);

        startActivity(intent);
    }


    // NETWORK CHECK ////////////////////////////////////
    @Override
    public void onResume() {
        super.onResume();
        checkIfNetWorkIsAvailable();
    }

    private void checkIfNetWorkIsAvailable() {
        if (NetworkUtilities.isNetworkAvailable(getContext()) == false) {
            NetworkUtilities.alertNetworkNotAvailable(getContext());
            mLoadingBar.setVisibility(View.INVISIBLE);
        }
    }


    //...........................................................



}
