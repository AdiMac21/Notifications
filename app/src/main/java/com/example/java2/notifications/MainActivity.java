package com.example.java2.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private int NOTIFICATION_ID = 0;
    private int NOTIFICATION_ID2 = 1;
    private Button button2;
    private NotificationCompat.Builder mBuilder;
    private int numMessages = 1;
    private NotificationManager notificationManager;
    private int progres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkUI();
        listener();
    }

    private void listener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    notificare();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBuilder.setContentText("balblalba").setNumber(++numMessages);
                notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
            }
        });
    }

    private void linkUI() {

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
    }

    private void notif() throws InterruptedException {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.ro"));
        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My title").setContentText("Hello").setAutoCancel(true).setOngoing(false).setContentIntent(PendingIntent.getActivity(MainActivity.this, 0, intent, 0));
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 110; i = i + 10) {
                    mBuilder.setProgress(100, i, false);
                    notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == 100) {
                        NotificationCompat.Builder mBuilder2 = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Complete").setContentText("Download is complete").setAutoCancel(true).setOngoing(false).setContentIntent(PendingIntent.getActivity(MainActivity.this, 0, new Intent(), 0));
                        notificationManager.notify(NOTIFICATION_ID2, mBuilder2.build());
                    }
                }
            }
        }).start();


    }

    private void notificare(){
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true).setOngoing(false).setContentIntent(PendingIntent.getActivity(MainActivity.this, 0, new Intent(), 0));
        NotificationCompat.InboxStyle inboxStyle=new NotificationCompat.InboxStyle();
        String[] events=new String[6];
        events[0]="event1";
        events[1]="event2";
        events[2]="event3";
        events[3]="event4";
        inboxStyle.setBigContentTitle("Event tracker:");
        for (int i = 0; i < events.length ; i++) {
            inboxStyle.addLine(events[i]);

        }
        mBuilder.setStyle(inboxStyle);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
