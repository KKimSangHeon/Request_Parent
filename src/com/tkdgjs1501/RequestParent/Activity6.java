package com.tkdgjs1501.RequestParent;



import com.tkdgjs1501.RequestParent.*;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.*;
import android.telephony.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity6 extends Activity {
		
	String PhoneNumber = "0";
	
	String sloc=null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout6);
		
		final Button button1 = (Button) findViewById(R.id.send);
		final TextView tv = (TextView) findViewById(R.id.result);
		
		tv.setText(Activity1.CReg+"�� ��ȭ�� ���� ���� �� "+Activity1.DestinationPhoneNumber+" ��ȣ�� ���ڸ޼����� ���޵˴ϴ�.");
		

	
		
		
		
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				PhoneNumber=Activity1.DestinationPhoneNumber;
				
				SmsManager sms = SmsManager.getDefault(); // ����
				sms.sendTextMessage(PhoneNumber, null, "�޼��� ���� �׽�Ʈ", null, null); // ����
				
				Toast.makeText(getApplicationContext(), "�׽�Ʈ �޼��� ���ۿϷ�!",
						Toast.LENGTH_SHORT).show();
			}

		});
		Button GoTo7 = (Button) findViewById(R.id.GoToActivity7); // �ι�° ��ư

		GoTo7.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Activity6.this, Activity7.class);
				startActivity(intent);
			}
		});
	}


	
	
}