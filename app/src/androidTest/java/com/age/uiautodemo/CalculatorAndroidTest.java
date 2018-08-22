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

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CalculatorAndroidTest {

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
        //UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
        //allAppsButton.clickAndWaitForNewWindow();
        //UiObject cal = new UiObject(new UiSelector().text("Calculator"));
        //cal.click();
    }

    @Test
    public void testSetting() throws UiObjectNotFoundException, InterruptedException, IOException {
        UiAutomatorTestCase u = new UiAutomatorTestCase ();

        mDevice.findObject(new UiSelector().description("应用")).click();
        u.sleep(3000);

        // 找到应用并点击
        mDevice.findObject(new UiSelector().text("计算器")).click();
        //UiObject u0 = new UiObject(new UiSelector().className("android.widget.ImageButton"));
        //u0.click();
        u.sleep(3000);

        new UiObject(new UiSelector().text("5")).click();
        u.sleep(1000);

        //mDevice.findObject(new UiSelector().text("0")).click();
        new UiObject(new UiSelector().text("=")).click();
        u.sleep(1000);
        //mDevice.pressHome();

        int cnt = 0;
        while(cnt++ < 10) {
            new UiObject(new UiSelector().text("9")).click();
            new UiObject(new UiSelector().text("6")).click();
            new UiObject(new UiSelector().text("+")).click();
            new UiObject(new UiSelector().text("1")).click();
            new UiObject(new UiSelector().text("7")).click();
            new UiObject(new UiSelector().text("=")).click();
            UiObject res = new UiObject(new UiSelector().className("android.widget.TextView").enabled(true).instance(1));
            Log.d("___ZZZTTT...", "res=" + res.getText());
            assertEquals("", "113", res.getText());
        }
    }


}
