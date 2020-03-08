package com.example.myapplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
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

public class MainActivityTest {
    String text = "This is the Message to Read";
    @Rule
    public IntentsTestRule<MainActivity>intentsTestRule = new IntentsTestRule<>(MainActivity.class);
    public ActivityTestRule<MainActivity>activityActivityTestRule = new ActivityTestRule<>(MainActivity.class,true,false);


    public static Intent createIntent(String name){
        onView(withId(R.id.button)).perform(click());
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent i = new Intent(context,MainActivity.class);
        i.putExtra("Value",name);
        return i;
    }
    @Test
    public void showIntentTest() throws InterruptedException {
        activityActivityTestRule.launchActivity(createIntent("This is the Message to Read"));
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(2000);
    }
    @Test
    public void intentTest(){
        onView(withId(R.id.button)).perform(click());

        intended(hasComponent(Activity1.class.getName()));
    }

    @Test
    public void intentSendData(){
        onView(withId(R.id.button)).perform(click());
        Intent i = new Intent();
        activityActivityTestRule.launchActivity(i);
    }
    /*@Test
    public void getDataBack() throws InterruptedException {
        Intent i = new Intent();
        i.putExtra("Value",text);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK,i);
        intending(toPackage(Activity5.class.getName())).respondWith(result);

        Espresso.pressBack();
        Espresso.closeSoftKeyboard();
        Thread.sleep(2000);
        onView(withId(R.id.button)).perform(click());
        Thread.sleep(2000);
    }
*/
}
