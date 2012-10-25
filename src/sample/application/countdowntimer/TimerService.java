package sample.application.countdowntimer;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;

public class TimerService extends Service {

	Context mContext;
	int counter;
	Timer timer;
	public PowerManager.WakeLock wl;

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		mContext = this;
		counter = intent.getIntExtra("counter", 0);
		if (counter != 0) {
			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK
					+ PowerManager.ON_AFTER_RELEASE, "My Tag");
			wl.acquire();
			startTimer();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		timer.cancel();
		if (wl.isHeld()) {
			wl.release();
		}
	}

	public void startTimer() {
		if (timer != null) {
			timer.cancel();
		}
		timer = new Timer();
		final android.os.Handler handler = new Handler();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				handler.post(new Runnable() {

					@Override
					public void run() {
						if (counter == -1) {
							timer.cancel();
							if (wl.isHeld()) {
								wl.release();
							}
							showAlam();
						} else {
							CountdownTimerActivity.countdown(counter);
							counter = counter - 1;
						}
					}

					private void showAlam() {
					}
				});
			}
		}, 0, 1000);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
