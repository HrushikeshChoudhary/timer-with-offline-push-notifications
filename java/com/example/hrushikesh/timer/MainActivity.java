package com.example.hrushikesh.timer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button start;
    TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        timer = (TextView) findViewById(R.id.timer);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(5000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        timer.setText(millisUntilFinished / 1000+" Seconds Left");
                    }

                    public void onFinish() {
                        start.setText("Restart");
                        timer.setText("Done");
                        sendNoti();
                    }
                }.start();
            }
        });
    }

    public void sendNoti() {
        int tag = 12345;
        NotificationCompat.Builder notif = new NotificationCompat.Builder(this);
        notif.setAutoCancel(true);
        notif.setSmallIcon(R.mipmap.ic_launcher);
        notif.setWhen(System.currentTimeMillis());
        notif.setContentTitle("REMINDER");
        notif.setContentText("Feed Maxi");
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pend = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notif.setContentIntent(pend);
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(tag, notif.build());
        //notif.setAutoCancel(true);

    }

}

