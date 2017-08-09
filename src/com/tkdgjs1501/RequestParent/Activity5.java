package com.tkdgjs1501.RequestParent;

import com.tkdgjs1501.RequestParent.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity5 extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout5);
		final ImageButton registerbutton = (ImageButton) findViewById(R.id.register);
		final EditText countedittext = (EditText) findViewById(R.id.count);

		// TODO Auto-generated method stub
		registerbutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String temp=countedittext.getText().toString();
				if(temp.equals(""))
				{
					Toast.makeText(getApplicationContext(), "횟수를 입력해 주십시오.",
							Toast.LENGTH_SHORT).show();

				}
				else
				{
					Activity1.CReg = Integer.parseInt(countedittext.getText().toString());
					Activity1.RegMCcount = Activity1.CReg
							+ Activity1.InitMissedCall;
					Intent intent = new Intent(Activity5.this, Activity6.class);
					startActivity(intent);
				}

				

			}

		});
	}

}
