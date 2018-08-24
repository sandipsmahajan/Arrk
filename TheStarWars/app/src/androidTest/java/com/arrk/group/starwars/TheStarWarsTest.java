package com.arrk.group.starwars;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arrk.group.starwars.communication.adapter.PeopleAdapter;
import com.arrk.group.starwars.communication.constants.UIConstant;
import com.arrk.group.starwars.communication.models.PeopleModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TheStarWarsTest {

    @Rule
    public ActivityTestRule<CharacterActivity> characterActivityTestRule = new ActivityTestRule<>(CharacterActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.arrk.group.starwars", appContext.getPackageName());
    }

    @Test
    public void ensureRecyclerView() {
        CharacterActivity activity = characterActivityTestRule.getActivity();
        View viewById = activity.findViewById(R.id.recyclerView);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(RecyclerView.class));
        RecyclerView listView = (RecyclerView) viewById;
        PeopleAdapter adapter = (PeopleAdapter) listView.getAdapter();
        assertThat(adapter, instanceOf(RecyclerView.Adapter.class));
        assertNotNull(adapter);
        assertThat(adapter.getItemCount(), lessThan(5));
    }

    @Rule
    public ActivityTestRule<CharacterDetailsActivity> characterDetailsActivityTestRule = new ActivityTestRule<CharacterDetailsActivity>(CharacterDetailsActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra(UIConstant.PEOPLE_INTENT, new PeopleModel());
            return intent;
        }
    };

    @Test
    public void ensureIntentDataIsDisplayed() {
        CharacterDetailsActivity activity = characterDetailsActivityTestRule.getActivity();
        View viewById = activity.findViewById(R.id.text_name);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(TextView.class));
        TextView textView = (TextView) viewById;
        assertThat(textView.getText().toString(), is("The Star Wars"));
    }
}
