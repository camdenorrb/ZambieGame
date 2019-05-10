package me.camdenorrb.zambiegame.utils;

import java.awt.*;

public final class DisplayUtils {

    private DisplayUtils() {}


    public static int getRefreshRate() {

        final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice[] devices = env.getScreenDevices();

        if (devices.length == 0) {
            return 60;
        }

        final int refreshRate = devices[0].getDisplayMode().getRefreshRate();

        if (refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
            return 60;
        }

        return devices[0].getDisplayMode().getRefreshRate();
    }

}
