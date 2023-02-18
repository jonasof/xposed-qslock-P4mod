package com.i5lee8bit.char101.qslock;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import android.app.AndroidAppHelper;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;

public class Main implements IXposedHookLoadPackage {
    private static final String TAG = "qslock";

    private static final int DISABLE2_NONE = 0;
    private static final int DISABLE2_QUICK_SETTINGS = 1;

    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.android.systemui")) {
            return;
        }

        XposedBridge.log(TAG + ": started");

        Method updateDisplaysMethod = this.findMethodByName(XposedHelpers.findClass("com.android.keyguard.KeyguardDisplayManager", lpparam.classLoader), "updateDisplays");

        XposedBridge.hookMethod(updateDisplaysMethod, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.i(TAG, "show");
                Context context = (Context) AndroidAppHelper.currentApplication();
                if (context == null) {
                    Log.e(TAG, "context is null");
                    return;
                }
                Object statusBarManager = context.getSystemService("statusbar");
                if (statusBarManager == null) {
                    Log.e(TAG, "statusBarManager is null");
                    return;
                }

                boolean isLockScreenActive = param.args[0].toString() == "true";

                if (isLockScreenActive) {
                    XposedHelpers.callMethod(statusBarManager, "disable2", new Class<?>[]{Integer.class}, DISABLE2_QUICK_SETTINGS);
                } else {
                    XposedHelpers.callMethod(statusBarManager, "disable2", new Class<?>[]{Integer.class}, DISABLE2_NONE);
                }
            }
        });
    }

    private Method findMethodByName(Class cl, String name) {
        Method[] methods = cl.getDeclaredMethods();

        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName() == name) {
                return methods[i];
            }
        }

        return null;
    }
}
