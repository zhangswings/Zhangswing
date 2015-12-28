package com.pdsu.zhangsings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class NextActivity extends Activity implements OnClickListener {

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0) {
				mTextView.setText("This is change Log .");
				Toast.makeText(getApplicationContext(), "This is change Log .",
						Toast.LENGTH_SHORT).show();
			}
			if (msg.what == 1) {
				mTextView.setText("This is change Log .1");
				Toast.makeText(getApplicationContext(),
						"This is change Log .1", Toast.LENGTH_SHORT).show();
			}
		}
	};
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next);
		mTextView = (TextView) findViewById(R.id.tv_show);
		findViewById(R.id.btn_change_text).setOnClickListener(this);
		findViewById(R.id.btn_change_text2).setOnClickListener(this);
		findViewById(R.id.btn_change_text3).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.next, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_change_text:
			new Thread() {
				@Override
				public void run() {
					Message msg = mHandler.obtainMessage();
					msg.what = 0;
					msg.sendToTarget();
					// mHandler.sendEmptyMessage(what)
				};
			}.start();
			break;
		case R.id.btn_change_text2:
			new Thread() {
				@Override
				public void run() {
					Message msg = new Message();
					msg.what = 1;
					// msg.sendToTarget();
					mHandler.sendMessage(msg);
				};
			}.start();
		case R.id.btn_change_text3:
			checkDefaultActivity(this);
			break;

		default:
			break;
		}

	}

	// 检测程序默认程序
	public void checkDefaultActivity(Context mContext) {
		PackageManager pm = mContext.getPackageManager();
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://www.google.com"));
		ResolveInfo info = pm.resolveActivity(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		Log.i("zhang", "getDefaultActivity info = " + info + ";pkgName = "
				+ info.activityInfo.packageName);
		Toast.makeText(
				mContext,
				"getDefaultActivity info = " + info + ";pkgName = "
						+ info.activityInfo.packageName, Toast.LENGTH_SHORT)
				.show();
	}
}
