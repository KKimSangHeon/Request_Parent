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
			sloc = "��ȭ�� ���� �ʾҽ��ϴ� ��ġ������ �������� �ʽ��ϴ�";
		}
		if (Activity1.RegMCcount == Activity1.InitMissedCall) { // RegMCount(�ʱ�
																// ������+ ����Ѽ�)��
																// ���� ��������ȭ��
																// ��������

			SmsManager sms = SmsManager.getDefault(); // ����
			sms.sendTextMessage(Activity1.DestinationPhoneNumber, null, sloc,
					null, null); // ����
			Activity1.RegMCcount = Activity1.RegMCcount + Activity1.CReg;
		}

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout7);

		// ������ Ŭ����)
		TextView st = (TextView) findViewById(R.id.mcstate);
		st.setText("���ø����̼��� ���������� �������Դϴ�.");

		Button GoTo11 = (Button) findViewById(R.id.GoToInit1); // ����° ��ư
		
		mLocMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		mProvider = mLocMan.getBestProvider(new Criteria(), true);
		Location location = mLocMan.getLastKnownLocation(mProvider);

		final Timer t = new Timer();
		
		TimesGo tg = new TimesGo(); // Timer�� ������ �۾� ��ü ����( TimerTask Ŭ������ ��ӹ޾Ƽ�
		t.schedule(tg, 5000, 5000);// ��(2��°�Ű�����)���� ��(3��°�Ű�����)�ʸ��� ���� �ݺ�
		// ��ġ������ ������� ���� 20�ʸ� �ְ� 5�ʸ��� ��������ȭ�� ����
		GoTo11.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Timer t = new Timer(); �ȵɶ� �غ���
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
			sloc = String.format("��ȭ�� ���� �ʾҽ��ϴ�\n����:%f\n�浵:%f\n��:%f",
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
