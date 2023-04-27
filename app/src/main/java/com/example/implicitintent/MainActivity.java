package com.example.implicitintent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
        /* 위에 코드 이해불가 시 통으로 외우기 */

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode== 1){
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Button btnCall = findViewById(R.id.btnCall);
                btnCall.setEnabled(false);
            }
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Button btnCamera = findViewById(R.id.btnCamera);
                btnCamera.setEnabled(false);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 1) && (resultCode == RESULT_OK)) {
            Bitmap bitmap = data.getParcelableExtra("data");
            ImageView ivCamera = findViewById(R.id.iVCamera);
            ivCamera.setImageBitmap(bitmap);
        }
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
