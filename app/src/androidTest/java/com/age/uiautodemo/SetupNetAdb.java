package com.age.uiautodemo;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiObject;
import android.util.Log;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class SetupNetAdb {
    private Context mContext;
    private UiDevice mDevice;

    @Before
    public void setUp() throws Exception {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mContext = InstrumentationRegistry.getContext();

        // Start from the home screen
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
    }

    @Test
    public void SetupAdbByTCP() throws InterruptedException, IOException {
        try {
            mDevice.findObject(new UiSelector().description("应用")).click();
            Thread.sleep(1000);
        } catch (UiObjectNotFoundException ee) {}

        int h = mDevice.getDisplayHeight();
        int w = mDevice.getDisplayWidth();
        UiObject MyFcs = null;
        while(MyFcs == null) {
            MyFcs = mDevice.findObject(new UiSelector().description("设置"));
            Log.d("YUAN", "continue...." + MyFcs);
            mDevice.swipe(w / 2, h - 200, w / 2, h - 300, 100);//滑动屏幕
        }

        if(null == MyFcs) {
            Log.d("YUAN", "Setup not found");
        } else {
            //MyFcs.click();
        }
    }
}
