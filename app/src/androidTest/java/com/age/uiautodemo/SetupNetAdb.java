package com.age.uiautodemo;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.Configurator;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiObject;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class SetupNetAdb {
    private static final String TAG ="YUAN";
    private Context mContext;
    private UiDevice mDevice;

    @Before
    public void setUp() throws Exception {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mContext = InstrumentationRegistry.getContext();

        // Start from the home screen
        mDevice.wakeUp();
        mDevice.pressBack();
        mDevice.pressBack();
        mDevice.pressHome();
    }

    @Test
    public void SetupAdbByTCP() throws InterruptedException, IOException {
        try {
            mDevice.findObject(new UiSelector().description("应用")).click();
            Thread.sleep(1000);
        } catch (UiObjectNotFoundException ee) {
            Log.d(TAG, "no [应用]");
        }

        int h = mDevice.getDisplayHeight();
        int w = mDevice.getDisplayWidth();

        long orig_timeout = Configurator.getInstance().getWaitForSelectorTimeout();
        Log.d(TAG, "orig_timeout=" + orig_timeout);
        Configurator.getInstance().setWaitForSelectorTimeout(100);

        while(true) {
            try {
                mDevice.findObject(new UiSelector().description("设置")).click();
                Log.d(TAG, "[设置] clicked");
                break;
            } catch (UiObjectNotFoundException e) {
                //e.printStackTrace();
                Log.d(TAG, "no, continue....");
                mDevice.swipe(w / 2, h - 200, w / 2, h - 500, 100);//滑动屏幕
            }
        }

        while(true) {
            try {
                mDevice.findObject(new UiSelector().text("开发者选项")).click();
                Log.d(TAG, "[开发者选项] clicked");
                break;
            } catch (UiObjectNotFoundException e) {
                //e.printStackTrace();
                Log.d(TAG, "no, continue....");
                mDevice.swipe(w / 2, h - 200, w / 2, h - 600, 100);//滑动屏幕
            }
        }

        UiObject My_onoff = null;
        while(true) {
            try {
                UiObject MyFcs = mDevice.findObject(new UiSelector().text("高级重启")); //网络 ADB 调试
                MyFcs.isEnabled();
                Log.d(TAG, "now can Clicked");
                My_onoff = MyFcs.getFromParent(new UiSelector().className(android.widget.Switch.class.getName()));
                break;
            } catch (UiObjectNotFoundException e) {
                //e.printStackTrace();
                Log.d(TAG, "no, continue....");
                mDevice.swipe(w / 2, h - 200, w / 2, h - 600, 100);//滑动屏幕
            }
        }
        Log.d(TAG, "now FFFFFFFFFFFf it");
        try {
            Log.d(TAG, "ON_OFF=" + My_onoff);
            My_onoff.getChild(new UiSelector().text("高级重启")).click();
            Log.d(TAG, "SWITCH = " + My_onoff.isChecked());
            My_onoff.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }


        //恢复超时参数
        Configurator.getInstance().setWaitForSelectorTimeout(orig_timeout);
        Log.d(TAG, "----terminate normally.");
    }

}
