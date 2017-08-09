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
		
		tv.setText(Activity1.CReg+"번 전화를 받지 않을 시 "+Activity1.DestinationPhoneNumber+" 번호로 문자메세지가 전달됩니다.");
		

	
		
		
		
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				PhoneNumber=Activity1.DestinationPhoneNumber;
				
				SmsManager sms = SmsManager.getDefault(); // 문자
				sms.sendTextMessage(PhoneNumber, null, "메세지 전송 테스트", null, null); // 문자
				
				Toast.makeText(getApplicationContext(), "테스트 메세지 전송완료!",
						Toast.LENGTH_SHORT).show();
			}

		});
		Button GoTo7 = (Button) findViewById(R.id.GoToActivity7); // 두번째 버튼

		GoTo7.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Activity6.this, Activity7.class);
				startActivity(intent);
			}
		});
	}


	
	
}