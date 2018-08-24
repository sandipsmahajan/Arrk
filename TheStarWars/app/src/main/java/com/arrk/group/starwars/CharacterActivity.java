package com.arrk.group.starwars;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arrk.group.starwars.communication.SyncUpdateMessage;
import com.arrk.group.starwars.communication.adapter.OnItemClickListener;
import com.arrk.group.starwars.communication.adapter.PaginationScrollListener;
import com.arrk.group.starwars.communication.adapter.PeopleAdapter;
import com.arrk.group.starwars.communication.constants.UIConstant;
import com.arrk.group.starwars.communication.models.PeopleModel;
import com.arrk.group.starwars.communication.models.PeopleResponseModel;
import com.arrk.group.starwars.custom.STextView;
import com.arrk.group.starwars.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * @author SANDY
 * <p>
 * The activity will show the Star Wars character names
 */
public class CharacterActivity extends AbstractUIActivity implements OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_error)
    STextView mTextError;
    @BindView(R.id.errorLayout)
    RelativeLayout mErrorLayout;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private PeopleAdapter adapter;
    private Intent intent;

    // Index from which pagination should start (1 is 1st page in our case)
    private static final int PAGE_START = UIConstant.PAGE_START;
    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;
    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;
    // total no. of pages to load. Initial load is page 1, after which more pages will load.
    private int TOTAL_PAGES;
    // Total number of items
    private int TOTAL_PAGES_COUNT;
    // indicates the current page which Pagination is fetching.
    private int currentPage = PAGE_START;

    private int back_click_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        adapter = new PeopleAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
        intent = new Intent(this, CharacterDetailsActivity.class);

        // Handle pagination
        mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {

            @Override
            protected void loadMoreItems() {
                currentPage += 1;
                getPeople(false);
                isLoading = true;
            }

            @Override
            protected int getTotalPageCount() {
                return TOTAL_PAGES_COUNT;
            }

            @Override
            protected boolean isLastPage() {
                return isLastPage;
            }

            @Override
            protected boolean isLoading() {
                return isLoading;
            }
        });

        getPeople(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        back_click_count = 0;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_character;
    }

    @Override
    public void handleMessage(Message message) {
        SyncUpdateMessage syncUpdateMessage = (SyncUpdateMessage) message.obj;
        // If the data is appropriate
        if (syncUpdateMessage.getWhat() instanceof PeopleResponseModel) {
            PeopleResponseModel response = (PeopleResponseModel) syncUpdateMessage.getWhat();
            showErrorInfo(false);
            TOTAL_PAGES_COUNT = response.getCount();
            calculateTotalPages(TOTAL_PAGES_COUNT);
            adapter.setValues(response.getResults());
            adapter.notifyDataSetChanged();
        } else if (syncUpdateMessage.getWhat() instanceof Exception) {
            Exception exception = (Exception) syncUpdateMessage.getWhat();
            mTextError.setText(exception.getMessage());
            showErrorInfo(true);
        }
        isLoading = false;
        mProgressBar.setVisibility(View.GONE);
    }

    private void calculateTotalPages(int count) {
        int PAGE_ITEM_COUNT = UIConstant.PAGE_ITEMS_COUNT;
        int pages = count / PAGE_ITEM_COUNT;
        int mod = count % PAGE_ITEM_COUNT;
        if (mod > 0) {
            pages += pages;
        }
        TOTAL_PAGES = pages;
    }

    private void showErrorInfo(boolean isShow) {
        if (isShow) {
            mErrorLayout.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            return;
        }
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_retry)
    public void onViewClicked() {
        if (Util.isNetworkAvailable(this)) {
            showErrorInfo(false);
            getPeople(true);
        }
    }

    // Call API to get
    private void getPeople(boolean showProgress) {
        if (!isLoading) {
            if (showProgress || (currentPage > PAGE_START && currentPage <= TOTAL_PAGES)) {
                Call people_call = communicator.people(currentPage);
                callService(people_call, showProgress);

                if (!showProgress) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            } else {
                isLastPage = true;
            }
        }
    }

    @Override
    public void onItemClick(PeopleModel item) {
        intent.putExtra(UIConstant.PEOPLE_INTENT, item);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (back_click_count == 0) {
            back_click_count += 1;
            Toast.makeText(this, getString(R.string.exit_message), Toast.LENGTH_SHORT).show();
            return;
        }
        super.onBackPressed();
    }
}
