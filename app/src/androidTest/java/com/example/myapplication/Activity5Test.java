package com.example.myapplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class Activity5Test {

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRuleMain = new IntentsTestRule<>(MainActivity.class);
    public ActivityTestRule<MainActivity> activityActivityTestRuleMain= new ActivityTestRule<>(MainActivity.class,true,true);
    public IntentsTestRule<Activity1> intentsTestRule1 = new IntentsTestRule<>(Activity1.class);
    public ActivityTestRule<Activity1> activityActivityTestRule1 = new ActivityTestRule<>(Activity1.class,true,true);
    public IntentsTestRule<Activity2> intentsTestRule2 = new IntentsTestRule<>(Activity2.class);
    public ActivityTestRule<Activity2> activityActivityTestRule2 = new ActivityTestRule<>(Activity2.class,true,true);
    public IntentsTestRule<Activity3> intentsTestRule3 = new IntentsTestRule<>(Activity3.class);
    public ActivityTestRule<Activity3> activityActivityTestRule3 = new ActivityTestRule<>(Activity3.class,true,true);
    public IntentsTestRule<Activity4> intentsTestRule4 = new IntentsTestRule<>(Activity4.class);
    public ActivityTestRule<Activity4> activityActivityTestRule4 = new ActivityTestRule<>(Activity4.class,true,true);
    public IntentsTestRule<Activity5> intentsTestRule5 = new IntentsTestRule<>(Activity5.class);
    public ActivityTestRule<Activity5> activityActivityTestRule5 = new ActivityTestRule<>(Activity5.class);

    @Test
    public void intentTestNow(){

        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.next1)).perform(click());
        onView(withId(R.id.next2)).perform(click());
        onView(withId(R.id.next3)).perform(click());
        onView(withId(R.id.next4)).perform(click());
        onView(withId(R.id.next5)).perform(click());

        intended(hasComponent(Activity5.class.getName()));
    }

    @Test
    public void intentTest(){
        Uri uri = Uri.parse("uri.string");
        Intent resultData = new Intent();
        resultData.setData(uri);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK,resultData);
        intending(toPackage("com.example.myapplication.MainActivity")).respondWith(result);
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.next1)).perform(click());
        onView(withId(R.id.next2)).perform(click());
        onView(withId(R.id.next3)).perform(click());
        onView(withId(R.id.next4)).perform(click());
        onView(withId(R.id.next5)).perform(click());
        intended(hasComponent(Activity5.class.getName()));

    }

}