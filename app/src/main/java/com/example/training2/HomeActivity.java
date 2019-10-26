package com.example.training2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.training2.service.MyJobService;

public class HomeActivity extends AppCompatActivity {

    private Button recycleView;
    private Button aboutButton;
    private Button exitButton;
    private Button fragmentBtn;
    private Button viewPager;
    private Button sqlLite;
    private TextView textView;
    private boolean isReceiverReigtered = false;
    private boolean isConnect = false;
    private static final String TAG = "Home Activity";
    public static final String TITLE_FLAG = "title";
    public static final String MSG_FLAG = "msg";
    public static final String TITLE_TEXT = "Wifi notification";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {
        } else {
            textView = findViewById(R.id.greeting);
            String value = textView.getText() + " " + extras.getString(MainActivity.FLAG);
            textView.setText(value);
        }

        aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
            }
        });

        fragmentBtn = findViewById(R.id.fragment);
        fragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(HomeActivity.this, profileActivity.class));
            }
        });


        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        viewPager = findViewById(R.id.viewPager);
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(HomeActivity.this, ViewPage.class));
            }
        });

        recycleView = findViewById(R.id.recycleView);
        recycleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(HomeActivity.this, RecyclerViewActivity.class));
            }
        });
    }

    public void scheduleJob(View v) {
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
//                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }

    public void cancelJob(View v) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelled");
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!isNetworkAvailable(context)) {
                Notification(context, "Wifi Connection Off");
            } else {
                Notification(context, "Wifi Connection On");
            }
        }

        public void Notification(Context context, String msg){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_perm_scan_wifi_black_24dp)
                    .setTicker(msg)
                    .setContentTitle(TITLE_TEXT)
                    .setContentText(msg)
                    .setAutoCancel(true);

            NotificationManager notificationmanager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            notificationmanager.notify(0, builder.build());
        }

        private boolean isNetworkAvailable(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();
            return activeNetworkInfo != null;
        }
    };

    protected void onResume(){
        super.onResume();
        if(!isReceiverReigtered) {
            isReceiverReigtered = true;
            registerReceiver(receiver, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!isReceiverReigtered){

        }
    }
}
