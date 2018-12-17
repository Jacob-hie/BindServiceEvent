package com.hie2j.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnStrat;
    private Button btnStop;
    private Button btnBind;
    private Button btnUnbind;
    private Button btnShowTime;
    private Button btnTransform;

    private TextView txtShowTime;
    private TextView txtResult;
    private EditText editText;
    private MyService.MyBinder myBinder;

    private ServiceConnection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFindViews();

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("ServiceConnection","onServiceConnected");
                myBinder = (MyService.MyBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("ServiceConnection","onServiceDisconnected");
            }
        };

        btnStrat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                startService(intent);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });

        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
            }
        });

        btnUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });

        btnShowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtShowTime.setText(myBinder.showTime());
            }
        });

        btnTransform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 txtResult.setText(myBinder.transfomr(editText.getText().toString()));
            }
        });
    }

    private void initFindViews() {
        btnStrat = findViewById(R.id.start_service);
        btnStop = findViewById(R.id.stop_service);
        btnBind = findViewById(R.id.bind_service);
        btnUnbind = findViewById(R.id.unbind_service);
        btnShowTime = findViewById(R.id.show_time);
        btnTransform = findViewById(R.id.transform);

        txtShowTime = findViewById(R.id.txt_time);
        txtResult = findViewById(R.id.txt_result);
        editText = findViewById(R.id.edt_text);
    }
}
