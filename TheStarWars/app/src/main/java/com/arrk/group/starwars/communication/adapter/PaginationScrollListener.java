package com.arrk.group.starwars.communication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author SANDY
 */
public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private final LinearLayoutManager layoutManager;

    protected PaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount < getTotalPageCount()) {
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();

    protected abstract int getTotalPageCount();

    protected abstract boolean isLastPage();

    protected abstract boolean isLoading();
}
