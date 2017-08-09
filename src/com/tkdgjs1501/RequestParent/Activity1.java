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
	public static int RegMCcount = 99999999;	//�̸�ŭ ������ �޼��� �߼�
	public static String DestinationPhoneNumber =null;
	public static int CReg;	//Ƚ�� ���
	public static String CurLoc;
	public static int InitMissedCall=0;		//ó�� �����Ҷ� ��������ȭ ��
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout1);
		
		
		InitMissedCall=getCallDetails(this);//���� ������ ��ȭ�� �������
		
		ImageButton b1 = (ImageButton) findViewById(R.id.ImgButton1); // ù��° ��ư

		b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Activity1.this, Activity2.class);
				startActivity(intent);
			}
		});

		ImageButton b2 = (ImageButton) findViewById(R.id.ImgButton2); // �ι�° ��ư

		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Activity1.this, Activity3.class);
				startActivity(intent);
			}
		});
		ImageButton b3 = (ImageButton) findViewById(R.id.ImgButton3); // ����° ��ư

		b3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Activity1.this, Activity4.class);
				startActivity(intent);
			}
		});

		
		ImageButton b5 = (ImageButton) findViewById(R.id.ImgButton5); // ����° ��ư

		b5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (RegMCcount == 99999999 || DestinationPhoneNumber == null) {
					Toast.makeText(getApplicationContext(), "����� ���� ���ֽʽÿ�",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent(Activity1.this, Activity7.class);
					startActivity(intent);

				}

			}
		});

		TextView t1 = (TextView) findViewById(R.id.state);
		if (!(RegMCcount == 99999999 && DestinationPhoneNumber == null))
			t1.setText("��� ������ �Ϸ�Ǿ����ϴ�.");
		else
			t1.setText("App ������ ���� �� ����� ���ּ���.");

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
