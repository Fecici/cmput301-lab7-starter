package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testAddCity() {

        // clicking on add city button
        onView(withId(R.id.button_add)).perform(click());

        // typing "edmonton"
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));

        // clicking on confirm
        onView(withId(R.id.button_confirm)).perform(click());

        // check if edmonton is actually displayed on the screen
        onView(withText("Edmonton")).check(matches(isDisplayed()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClearCity() {
        // add city to the list
        onView(withId(R.id.button_add)).perform(click());

        // type edmonton
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("edmonton"));

        onView(withId(R.id.button_confirm)).perform(click());

        // add another city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("calgary"));
        onView(withId(R.id.button_confirm)).perform(click());

        // clear list
        onView(withId(R.id.button_clear)).perform(click());

        // check that they don't exist anymore
        onView(withText("edmonton")).check(doesNotExist());
        onView(withText("calgary")).check(doesNotExist());
    }

    @Test
    public void testListView() {
        // add city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // check if there is data in the Adapter view
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).check(matches(withText("edmonton")));
    }

    @Test
    public void testActivitySwitch() {
        // add city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // check that the activity matches the layout
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());
        onView(withId(R.id.back_button)).check(matches(isDisplayed()));  // we're in the second activity
    }

    @Test
    public void testCityName() {
        // add city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // check that the name matches
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("edmonton")));
    }

    @Test
    public void testBackButton() {
        // add city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // check that the name matches
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());
        onView(withId(R.id.back_button)).perform(click());
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));  // an element in the layout we want
    }
}
