package sample.application.countdowntimer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class CountdownTimerActivity extends Activity {

	static TextView tv;
	static SeekBar sb;
	static Context mContext;
	static int timeLeft = 0;
	static Button btnStart, btnStop;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mContext = this;
		tv = (TextView) this.findViewById(R.id.textView1);
		btnStart = (Button) this.findViewById(R.id.buttonStart);
		btnStop = (Button) this.findViewById(R.id.buttonStop);
		sb = (SeekBar) this.findViewById(R.id.seekBar1);
		sb.setBackgroundDrawable(drawScale());
		setListeners();
	}

	private void setListeners() {

		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				timeLeft = progress * 60;
				if (fromUser) {
					showTime(progress * 60);
				}
				if (fromUser && (progress > 0)) {
					btnStart.setEnabled(true);
				} else {
					btnStart.setEnabled(false);
				}
				if (progress == 0) {
					btnStop.setEnabled(false);
				}
			}
		});
	}

	private Drawable drawScale() {
		Paint paint;
		Path path;
		Canvas canvas;
		Bitmap bitmap;

		paint = new Paint();
		paint.setStrokeWidth(0);
		paint.setStyle(Paint.Style.STROKE);
		bitmap = Bitmap.createBitmap(241, 30, Bitmap.Config.ARGB_8888);
		path = new Path();
		canvas = new Canvas(bitmap);

		for (int i = 0; i < 17; i++) {
			path.reset();
			if (i == 5 || i == 10 || i == 15) {
				paint.setColor(Color.WHITE);
			} else {
				paint.setColor(Color.GRAY);
			}
			path.moveTo(i * 16, 5);
			path.quadTo(i * 16, 5, i * 16, 15);
			canvas.drawPath(path, paint);
		}

		BitmapDrawable bd = new BitmapDrawable(bitmap);
		return bd;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}