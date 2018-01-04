package com.example.ridoy.fileexplore;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button internal_SD_Button, external_SD_Button;
    int WRITE_EXTERNAL_STORAGE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internal_SD_Button = findViewById(R.id.internal);
        external_SD_Button = findViewById(R.id.external);

        // Want Runtime permission from user
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
            ActivityCompat.requestPermissions(MainActivity.this, permissions, WRITE_EXTERNAL_STORAGE);
        }

    }

    // Button for select internal path
    public void internal_B(View view)
    {
        String internal_storage_path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
        Intent data = new Intent(MainActivity.this,File_List.class);
        data.putExtra("path",internal_storage_path);
        startActivity(data);
    }


    // Button for select external path
    public void external_B(View view)
    {
        String external_sotrage_path = "/storage/";
        Intent data = new Intent(MainActivity.this,File_List.class);
        data.putExtra("path",external_sotrage_path);
        startActivity(data);
    }
}
