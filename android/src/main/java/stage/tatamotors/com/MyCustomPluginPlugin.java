package com.tatamotors.stage;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.app.ActivityManager;
import android.content.Context;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import android.provider.Settings;

@CapacitorPlugin(name = "MyCustomPlugin")
public class MyCustomPluginPlugin extends Plugin {

    private MyCustomPlugin implementation = new MyCustomPlugin();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }
    @PluginMethod
    public void testPlugin(PluginCall call) {
        String value = call.getString("msg");
        JSObject ret = new JSObject();
        ret.put("value", value);
        call.resolve(ret);
    }
    @PluginMethod
    public void getDeviceUUID(PluginCall call) {
        Context context = getContext();
        String uuid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        JSObject ret = new JSObject();
        ret.put("uuid", uuid);
        call.resolve(ret);
    }
    @PluginMethod
public void getHardwareDetails(PluginCall call) {
    JSObject ret = new JSObject();
    ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
    ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
    activityManager.getMemoryInfo(mi);
    long availableMegs = mi.availMem/(1024*1024*1024); // in megabytes
    long totalMemory = mi.totalMem/(1024*1024*1024);

    StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
    long bytesAvailable = (long)stat.getBlockSizeLong() * (long)stat.getAvailableBlocksLong();
    long freeMegs = bytesAvailable/(1024*1024*1024);

    ret.put("availableRam", availableMegs);
    ret.put("freeSpace", freeMegs);
    ret.put("osVersion", Build.VERSION.RELEASE);
    ret.put("totalRam", totalMemory);
    call.resolve(ret);
}
}
