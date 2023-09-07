package com.example.cameraapp;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
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
                //Toast.makeText(getApplicationContext(),"Sucessfully Sent",Toast.LENGTH_LONG).show();
                SmsManager sms=SmsManager.getDefault();

                sms.sendTextMessage("5554", null, "hai", null, null);
                Toast.makeText(getApplicationContext(),"Sucessfully Sent",Toast.LENGTH_LONG).show();

            }
        });
    }
    /*
    public boolean onCreateOptionsMenu(Menu menu)
    {
// Inflate the menu; this adds items
// to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        Toast.makeText(getApplicationContext(),"Sucessfully Sent",Toast.LENGTH_SHORT).show();
        return true;
    }*/
}
