package com.tkdgjs1501.RequestParent;

import java.text.*;
import java.util.*;

import com.tkdgjs1501.RequestParent.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.os.*;
import android.provider.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity1 extends Activity {
	public static int RegMCcount = 99999999;	//이만큼 충족시 메세지 발송
	public static String DestinationPhoneNumber =null;
	public static int CReg;	//횟수 등록
	public static String CurLoc;
	public static int InitMissedCall=0;		//처음 시작할때 부재중통화 수
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout1);
		
		
		InitMissedCall=getCallDetails(this);//현재 부재중 통화수 갖고오기
		
		ImageButton b1 = (ImageButton) findViewById(R.id.ImgButton1); // 첫번째 버튼

		b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Activity1.this, Activity2.class);
				startActivity(intent);
			}
		});

		ImageButton b2 = (ImageButton) findViewById(R.id.ImgButton2); // 두번째 버튼

		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Activity1.this, Activity3.class);
				startActivity(intent);
			}
		});
		ImageButton b3 = (ImageButton) findViewById(R.id.ImgButton3); // 세번째 버튼

		b3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Activity1.this, Activity4.class);
				startActivity(intent);
			}
		});

		
		ImageButton b5 = (ImageButton) findViewById(R.id.ImgButton5); // 세번째 버튼

		b5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (RegMCcount == 99999999 || DestinationPhoneNumber == null) {
					Toast.makeText(getApplicationContext(), "등록을 먼저 해주십시오",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent(Activity1.this, Activity7.class);
					startActivity(intent);

				}

			}
		});

		TextView t1 = (TextView) findViewById(R.id.state);
		if (!(RegMCcount == 99999999 && DestinationPhoneNumber == null))
			t1.setText("모든 설정이 완료되었습니다.");
		else
			t1.setText("App 사용법을 읽은 후 등록을 해주세요.");

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


}
