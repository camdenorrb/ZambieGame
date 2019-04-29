package me.camdenorrb.zambiegame.utils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public final class TimerUtils {

	private TimerUtils() {}


	/*
	public static void scheduleRepeat(Timer timer, long delay, Runnable block) {
		timer.scheduleAtFixedRate(task(block), delay, 0);
	}
	*/

	public static void scheduleRepeat(Timer timer, long delay, long period, Runnable block) {
		timer.scheduleAtFixedRate(task(block), delay, period);
	}

	/*
	public static void scheduleRepeat(Timer timer, Date time, Runnable block) {
		timer.scheduleAtFixedRate(task(block), time, 0);
	}
	*/

	public static void scheduleRepeat(Timer timer, Date time, long period, Runnable block) {
		timer.scheduleAtFixedRate(task(block), time, period);
	}


	public static TimerTask task(Runnable runnable) {
		return new TimerTask() {
			@Override
			public void run() {
				runnable.run();
			}
		};
	}

}
