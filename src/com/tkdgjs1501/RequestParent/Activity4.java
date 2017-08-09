package com.tkdgjs1501.RequestParent;



import com.tkdgjs1501.RequestParent.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity4 extends Activity {
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.layout4);
		final ImageButton button1 = (ImageButton) findViewById(R.id.send);
		final EditText edittext = (EditText) findViewById(R.id.phone);

	    // TODO Auto-generated method stub
	    button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String temp=edittext.getText().toString();
				if(temp.equals(""))
				{
					Toast.makeText(getApplicationContext(), "번호를 입력해 주십시오.",
							Toast.LENGTH_SHORT).show();
				}
				else
				{
				Activity1.DestinationPhoneNumber=edittext.getText().toString();
				Intent intent = new Intent(Activity4.this, Activity5.class);
				startActivity(intent);
				}
			}

		});
	}

}
