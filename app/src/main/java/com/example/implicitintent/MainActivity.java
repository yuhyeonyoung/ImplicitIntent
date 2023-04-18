package com.example.implicitintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] PERMISSIONS = {Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA};
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), PERMISSIONS[0])
                != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(getApplicationContext(), PERMISSIONS[1])
                        != PackageManager.PERMISSION_GRANTED))
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnCall:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01026713382"));
                break;
            case R.id.btnDial:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01026713382"));
                break;
            case R.id.btnSms:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:01026713382"));
                break;
            case R.id.btnMap:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.18, 128.19?Z=10"));
                break;
            case R.id.btnWep:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.semyung.ac.kr"));
                break;
            case R.id.btnContact:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contancts/people/"));
                break;
            case R.id.btnCamera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                break;
        }
        if (view.getId() == R.id.btnCamera)
            startActivityForResult(intent,1);
        else
            startActivity(intent);
    }
} // end of onClick()
