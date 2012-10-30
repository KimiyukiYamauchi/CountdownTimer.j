package sample.application.countdowntimer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class AlarmDialog extends Activity {

	Ringtone rt;
	Vibrator vib;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.alarmdialog);
		vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (rt != null) {
					rt.stop();
				}
				vib.cancel();
				finish();
			}
		});
	}

	@Override
	protected void onResume() {

		Log.d(getLocalClassName(), "onResume");

		super.onResume();

		SharedPreferences prefs;
		prefs = this.getSharedPreferences("CountdownTimerPrefs", 0);

		Log.d(getLocalClassName(), "onResume prefs=" + prefs);

		String fn = prefs.getString("alarm", "");

		Log.d(getLocalClassName(), "onResume fn=" + fn);

		if (fn != "") {

			Log.d(getLocalClassName(), "onResume fn != ");

			rt = RingtoneManager.getRingtone(this, Uri.parse(fn));

			Log.d(getLocalClassName(), "onResume rt=" + rt);

			if (rt != null && !rt.isPlaying()) {

				Log.d(getLocalClassName(), "onResume rt.play()");

				rt.play();
			}
		}
		if (prefs.getBoolean("vibrator", true)) {
			vib.vibrate(new long[] { 0, 1000, 500, 1000, 500, 1000 }, -1);
		}
	}

}
