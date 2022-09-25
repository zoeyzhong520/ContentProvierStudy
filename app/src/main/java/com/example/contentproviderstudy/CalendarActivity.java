package com.example.contentproviderstudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {
    // 成员变量
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initPermission();
    }

    /*动态获取权限*/
    private void initPermission() {
        // Build.VERSION.SDK_INT 操作系统的版本号
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] reqPermissions = new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR};
            for (String reqPermission : reqPermissions) {
                if (checkSelfPermission(reqPermission) != PackageManager.PERMISSION_GRANTED) {
                    // 如果有没有授权的，就去提醒授权
                    requestPermissions(reqPermissions, PERMISSION_REQUEST_CODE);
                }
            }
        }
    }

    /*重写回调方法onRequestPermissionsResult，也就是权限获取结果的回调。如果拒绝了我们的权限申请，就finish()当前页面*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    System.out.println("somePermissionsWereNotGranted");
                    Toast.makeText(this, "somePermissionsWereNotGranted", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                } else {
                    System.out.println("allPermissionsHaveBeenGiven...");
                    Toast.makeText(this, "allPermissionsHaveBeenGiven...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}