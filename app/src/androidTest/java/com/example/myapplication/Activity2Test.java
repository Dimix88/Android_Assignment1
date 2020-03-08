package com.example.myapplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class Activity2Test {


    @Rule
    public IntentsTestRule<MainActivity> intentsTestRuleMain = new IntentsTestRule<>(MainActivity.class);
    public ActivityTestRule<MainActivity> activityActivityTestRuleMain= new ActivityTestRule<>(MainActivity.class,true,true);
    public IntentsTestRule<Activity1> intentsTestRule1 = new IntentsTestRule<>(Activity1.class);
    public ActivityTestRule<Activity1> activityActivityTestRule1 = new ActivityTestRule<>(Activity1.class,true,true);
    public IntentsTestRule<Activity2> intentsTestRule2 = new IntentsTestRule<>(Activity2.class);
    public ActivityTestRule<Activity2> activityActivityTestRule2 = new ActivityTestRule<>(Activity2.class);
    @Test
    public void intentTestNow(){

        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.next1)).perform(click());
        onView(withId(R.id.next2)).perform(click());

        intended(hasComponent(Activity2.class.getName()));
    }

    @Test
    public void intentTest(){
        Uri uri = Uri.parse("uri.string");
        Intent resultData = new Intent();
        resultData.setData(uri);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK,resultData);
        intending(toPackage("com.example.myapplication.Activity3")).respondWith(result);
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.next1)).perform(click());
        onView(withId(R.id.next2)).perform(click());
        intended(hasComponent(Activity1.class.getName()));

    }

    public static Intent createIntent(String name){
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.next1)).perform(click());
        onView(withId(R.id.next2)).perform(click());
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent i = new Intent(context,Activity2.class);
        i.putExtra("Value",name);
        return i;
    }
    @Test
    public void showIntentTest() throws InterruptedException {
        activityActivityTestRule2.launchActivity(createIntent("i"));
        onView(withId(R.id.next2)).perform(click());
        Thread.sleep(2000);
    }
    @Test
    public void intentSendData(){

        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Activity3.class.getName(),null,false);
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.next1)).perform(click());
        onView(withId(R.id.next2)).perform(click());
        Activity activity3 = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 2000);
        Assert.assertNotNull(activity3);
        Assert.assertEquals("Activity3",activity3.getLocalClassName());
    }

}