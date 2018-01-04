package com.example.ridoy.fileexplore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class File_List extends AppCompatActivity {

    ListView listView;
    Button newfile, newfolder;
    String path = null;
    String discritPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file__list);
        listView = findViewById(R.id.fileItem);
        newfile = findViewById(R.id.newFile);
        newfolder = findViewById(R.id.newFolder);

        Intent data = getIntent();
        path = discritPath = data.getStringExtra("path");
        Log.v("path","2nd path " + path);
        file_and_folder_list(path);
    }

    // Back on home menu
    public void home(View view)
    {
        Intent intent = new Intent(File_List.this,MainActivity.class);
        startActivity(intent);
    }

    // Create New Folder
    public void newfolder(View view)
    {
        File file = new File(discritPath,"Ridoy_kumar_joy");
        if (!file.exists())
        {
            file.mkdir();
            Toast.makeText(this,"new Folder created",Toast.LENGTH_SHORT).show();
        }
        file_and_folder_list(discritPath);
    }

    // Create New Folder
    public void newfile(View view) throws IOException {
        File file = new File(discritPath,"Ridoy_kumar_joy.txt");
        if (!file.exists())
        {
            FileOutputStream fileData = new FileOutputStream(file);
            fileData.write("Hello I am Ridoy".getBytes());
            fileData.flush();
            Toast.makeText(this,"new file created",Toast.LENGTH_SHORT).show();
        }
        file_and_folder_list(discritPath);
    }



    // View all file and folder list using list view on current dir path
    private void file_and_folder_list(String getpath)
    {
        List<String> item = new ArrayList<>();
        final File file = new File(getpath);
        File[] list = file.listFiles();

        for(File name : list)
        {
            item.add(name.getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,item);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                discritPath = discritPath + String.valueOf(adapterView.getItemAtPosition(i)) + "/";
                Toast.makeText(File_List.this, String.valueOf(adapterView.getItemAtPosition(i)), Toast.LENGTH_SHORT).show();
                file_and_folder_list(discritPath);
            }
        });
    }

    // work when user pressed back button
    @Override
    public void onBackPressed() {
        if("/storage/emulated/0/".equals(discritPath) || "/storage/".equals(discritPath))
        {
            Toast.makeText(this,"You are all ready in main dir",Toast.LENGTH_SHORT).show();
        }
        else
        {
            String test = discritPath;
            test = test.replaceAll("/", "***/");
            String[] ter = test.split("/");
            int i = 0;
            test = "";
            while (i < ter.length - 1) {
                test = test + ter[i];
                i++;
            }
            test = test.replace("***", "/");
            if (test.equals("/")) {
                test = "/storage/";
                discritPath = "Path";
            } else {
                discritPath = test;
            }
            discritPath = test;
            file_and_folder_list(discritPath);
        }
    }


}
