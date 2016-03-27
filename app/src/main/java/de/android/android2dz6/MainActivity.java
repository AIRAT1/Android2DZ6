package de.android.android2dz6;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import me.everything.providers.android.calllog.CallsProvider;
import me.everything.providers.android.telephony.TelephonyProvider;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
    }

    public void onClickCalls(View view) {
        CallsProvider callsProvider = new CallsProvider(this);
        List<me.everything.providers.android.calllog.Call> calls = callsProvider.getCalls().getList();
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, calls));
    }

    public void onClickSMS(View view) {
        TelephonyProvider telephonyProvider = new TelephonyProvider(this);
        List<me.everything.providers.android.telephony.Sms> sms = telephonyProvider.getSms(TelephonyProvider.Filter.INBOX).getList();
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sms));
    }

    public void onClickSensors(View view) {
        SensorManager sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sensors));
    }
}
