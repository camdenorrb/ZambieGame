package me.camdenorrb.zambiegame.utils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Utilities for making timers cleaner
 */
public final class TimerUtils {

	private TimerUtils() {}


	/*
	public static void scheduleRepeat(Timer timer, long delay, Runnable block) {
		timer.scheduleAtFixedRate(task(block), delay, 0);
	}
	*/

	/**
	 * Schedules a repeating task
	 *
	 * @param timer The timer to run it on
	 * @param delay The delay to run the task with
	 * @param period The period to run the task
	 * @param block The task
	 */
	public static void scheduleRepeat(Timer timer, long delay, long period, Runnable block) {
		timer.scheduleAtFixedRate(task(block), delay, period);
	}

	/*
	public static void scheduleRepeat(Timer timer, Date time, Runnable block) {
		timer.scheduleAtFixedRate(task(block), time, 0);
	}
	*/

	/**
	 * Schedules a repeating task
	 *
	 * @param timer The timer to run it on
	 * @param period The period to run the task
	 * @param block The task
	 */
	public static void scheduleRepeat(Timer timer, Date time, long period, Runnable block) {
		timer.scheduleAtFixedRate(task(block), time, period);
	}


	/**
	 * Creates a task through lambda
	 *
	 * @param runnable The runnable to call as the "task"
	 *
	 * @return The new TimerTask
	 */
	public static TimerTask task(Runnable runnable) {
		return new TimerTask() {
			@Override
			public void run() {
				runnable.run();
			}
		};
	}

}
