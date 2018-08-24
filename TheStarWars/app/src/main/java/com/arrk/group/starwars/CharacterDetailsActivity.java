package com.arrk.group.starwars;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arrk.group.starwars.communication.constants.UIConstant;
import com.arrk.group.starwars.communication.models.PeopleModel;
import com.arrk.group.starwars.custom.STextView;
import com.arrk.group.starwars.util.Util;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharacterDetailsActivity extends AbstractUIActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_name)
    STextView mTextName;
    @BindView(R.id.text_height)
    STextView mTextHeight;
    @BindView(R.id.text_mass)
    STextView mTextMass;
    @BindView(R.id.text_created)
    STextView mTextCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialise(getIntent());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_character_details;
    }

    @Override
    public void handleMessage(Message message) {

    }

    private void initialise(Intent intent) {
        Bundle bundle = intent.getExtras();
        PeopleModel model = bundle.getParcelable(UIConstant.PEOPLE_INTENT);
        init(model);
    }

    private void init(PeopleModel model) {
        mTextName.setText(model.getName());
        mTextHeight.setText(String.format(Locale.US, "%.02f %s", Util.convertToMeter(model.getHeight()), getString(R.string.unit_meter)));
        mTextMass.setText(String.format("%s %s", model.getMass(), getString(R.string.unit_kg)));
        mTextCreated.setText(Util.formatDate(model.getCreated()));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initialise(intent);
    }
}
