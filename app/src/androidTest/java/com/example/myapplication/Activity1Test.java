package com.example.myapplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.ActivityResultFunction;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.anyIntent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

public class Activity1Test {


     @Rule
     public IntentsTestRule<MainActivity>intentsTestRuleMain = new IntentsTestRule<>(MainActivity.class);
    public ActivityTestRule<MainActivity>activityActivityTestRuleMain= new ActivityTestRule<>(MainActivity.class,true,true);
    public IntentsTestRule<Activity1> intentsTestRule = new IntentsTestRule<>(Activity1.class);
    public ActivityTestRule<Activity1> activityActivityTestRule = new ActivityTestRule<>(Activity1.class);



    @Test
    public void intentTestNow(){

        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.next1)).perform(click());

        intended(hasComponent(Activity1.class.getName()));
    }

    @Test
    public void intentTest(){
        Uri uri = Uri.parse("uri.string");
        Intent resultData = new Intent();
        resultData.setData(uri);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK,resultData);
        intending(toPackage("com.example.myapplication.Activity2")).respondWith(result);
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.next1)).perform(click());
        intended(hasComponent(Activity1.class.getName()));

    }

    @Test
    public void intentSendData(){

        Instrumentation.ActivityMonitor activityMonitor = new Instrumentation.ActivityMonitor("Activity2.class",null,true);
        InstrumentationRegistry.getInstrumentation().addMonitor(activityMonitor);
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.next1)).perform(click());
        assertTrue(InstrumentationRegistry.getInstrumentation().checkMonitorHit(activityMonitor,2));
    }

    @Test
    public void verifyMessageSent(){
        Intent intent = new Intent();
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.next1)).perform(click());
        intended(allOf(hasComponent(hasShortClassName("Activity1.class")),toPackage("com.example.myapplication.Activity2"),hasExtra("Value",intent)));
    }

    public static Intent createIntent(String name){
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.next1)).perform(click());
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent i = new Intent(context,Activity1.class);
        i.putExtra("Value",name);
        return i;
    }
    @Test
    public void showIntentTest() throws InterruptedException {
        activityActivityTestRule.launchActivity(createIntent("i"));
        onView(withId(R.id.next1)).perform(click());
        Thread.sleep(2000);
    }

}