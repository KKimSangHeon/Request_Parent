package com.tkdgjs1501.RequestParent;

import java.util.*;

import com.tkdgjs1501.RequestParent.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.location.*;
import android.os.*;
import android.provider.*;
import android.telephony.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class Activity7 extends Activity {
	LocationManager mLocMan;
	String mProvider;
	int mCount;
	String sloc = null;
	

	public void onResume() {
		super.onResume();
		mCount = 0;
		mLocMan.requestLocationUpdates(mProvider, 1000, 10, mListener);
	}

	public void routine() {
		Activity1.InitMissedCall = getCallDetails(this);
		mLocMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		mProvider = mLocMan.getBestProvider(new Criteria(), true);
		Location location = mLocMan.getLastKnownLocation(mProvider);
		if (sloc == null) {
			sloc = "전화를 받지 않았습니다 위치정보는 제공되지 않습니다";
		}
		if (Activity1.RegMCcount == Activity1.InitMissedCall) { // RegMCount(초기
																// 부재중+ 등록한수)가
																// 현재 부재중통화와
																// 같아지면

			SmsManager sms = SmsManager.getDefault(); // 문자
			sms.sendTextMessage(Activity1.DestinationPhoneNumber, null, sloc,
					null, null); // 문자
			Activity1.RegMCcount = Activity1.RegMCcount + Activity1.CReg;
		}

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout7);

		// 구현한 클래스)
		TextView st = (TextView) findViewById(R.id.mcstate);
		st.setText("어플리케이션이 정상적으로 동작중입니다.");

		Button GoTo11 = (Button) findViewById(R.id.GoToInit1); // 세번째 버튼
		
		mLocMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		mProvider = mLocMan.getBestProvider(new Criteria(), true);
		Location location = mLocMan.getLastKnownLocation(mProvider);

		final Timer t = new Timer();
		
		TimesGo tg = new TimesGo(); // Timer로 실행할 작업 객체 생성( TimerTask 클래스를 상속받아서
		t.schedule(tg, 5000, 5000);// 앞(2번째매개변수)초후 뒤(3번째매개변수)초마다 무한 반복
		// 위치정보를 갖어오기 위해 20초를 주고 5초마다 부재중통화수 갱신
		GoTo11.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Timer t = new Timer(); 안될때 해보기
				t.cancel();
				Intent intent = new Intent(Activity7.this, Activity1.class);
				startActivity(intent);
				
			}
		});
	}

	public int getCallDetails(Context context) {

		Cursor cursor = context.getContentResolver().query(
				CallLog.Calls.CONTENT_URI, null, null, null,
				CallLog.Calls.DATE + " DESC");

		int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
		int missedcall = 0;

		while (cursor.moveToNext()) {

			String callType = cursor.getString(type);

			int dircode = Integer.parseInt(callType);
			switch (dircode) {
			case CallLog.Calls.OUTGOING_TYPE:
				break;
			case CallLog.Calls.INCOMING_TYPE:
				break;

			case CallLog.Calls.MISSED_TYPE:

				missedcall++;
				break;
			}

		}
		cursor.close();
		return missedcall;
	}

	class TimesGo extends TimerTask {

		@Override
		public void run() {

			
				routine();
		
		}

	}

	LocationListener mListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			mCount++;
			sloc = String.format("전화를 받지 않았습니다\n위도:%f\n경도:%f\n고도:%f",
					location.getLatitude(), location.getLongitude(),
					location.getAltitude());

		}

		public void onProviderDisabled(String provider) {

		}

		public void onProviderEnabled(String provider) {

		}

		public void onStatusChanged(String provider, int status, Bundle extras) {

		}
	};
}
