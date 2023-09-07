package com.example.sms;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends Activity
{
@Override
protected void onCreate(Bundle savedInstanceState
{
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
Button bt=(Button)findViewById(R.id.button1);
bt.setOnClickListener(new OnClickListener()
{
@Override
public void onClick(View v)
{
// TODO Auto-generated method stub
SmsManager sms=SmsManager.getDefault();
sms.sendTextMessage("5554", null, "hai", null, null);
}
});
}
public boolean onCreateOptionsMenu(Menu menu)
{
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.main, menu);
return true;
}
}
