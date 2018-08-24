package com.arrk.group.starwars;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.arrk.group.starwars.communication.ICommunicator;
import com.arrk.group.starwars.communication.NetBuilder;
import com.arrk.group.starwars.communication.SyncUpdateMessage;
import com.arrk.group.starwars.util.Util;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SANDY
 */
public abstract class AbstractUIActivity extends AppCompatActivity {

    protected ICommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        communicator = NetBuilder.getInstance().getService();
    }

    /**
     * Get layout resource file
     *
     * @return int
     */
    protected abstract int getLayoutResource();

    /**
     * Handle messages received from the handler
     */
    protected abstract void handleMessage(Message message);

    private void showProgress() {
        Util.showProgress(this);
    }

    private void hideProgress() {
        Util.hideProgress();
    }

    @SuppressWarnings("unchecked")
    protected void callService(Call call, boolean showProgress) {
        if (showProgress)
            showProgress();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                SyncUpdateMessage syncUpdateMessage = new SyncUpdateMessage(SyncUpdateMessage.SYNC_SUCCESSFUL, response.body());
                handleResponse(syncUpdateMessage);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (call.isCanceled()) {
                    return;
                }
                SyncUpdateMessage syncUpdateMessage = new SyncUpdateMessage(SyncUpdateMessage.SYNC_ERROR, new Exception(getResources().getString(R.string.network_error)));
                handleResponse(syncUpdateMessage);
            }
        });
    }

    private void handleResponse(SyncUpdateMessage data) {
        hideProgress();
        Message message = new Message();
        message.obj = data;
        handleMessage(message);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Home as up button is to navigate to previous activity
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
