package com.basiclca.app.internalstorageapplca;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSaveData, btnReadData;
    private TextView tvData;
    private final String FILE_NAME = "internal_storage_lca.com";
    private final String FILE_NAME_Cach2 = "lca.com";
    private final String CONTENT = "Khong con duong de di nua roi!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSaveData = (Button) findViewById(R.id.btn_save_data);
        btnReadData = (Button) findViewById(R.id.btn_read_data);
        tvData = (TextView) findViewById(R.id.tv_data);
        btnSaveData.setOnClickListener(this);
        btnReadData.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_data:
//                saveData();
                saveDataByCache();
                break;
            case R.id.btn_read_data:
//                readData();
                readData2();
                break;
            default:
                break;
        }
    }
    public void saveData(){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(CONTENT.getBytes());//Chuyen String qua byte
            fileOutputStream.close();
            Toast.makeText(this, "saved successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readData(){
        try {
            FileInputStream in = openFileInput(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            StringBuffer strBuffer = new StringBuffer();
            String line = null;//Doc tung dong trong strBuffer
            try {
                while((line = bufferedReader.readLine()) != null){
                    strBuffer.append(line).append("\n");
                }
                Log.d("MainActivity", "readData: "+strBuffer.toString());
                tvData.setText(strBuffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void saveDataByCache(){
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            file = new File(getCacheDir(),FILE_NAME_Cach2);//luu trong thu muc cache
            fileOutputStream = new FileOutputStream(file);//dua vao OutputStream
//            fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(CONTENT.getBytes());//Chuyen String qua byte
            fileOutputStream.close();
            Toast.makeText(this, "saved successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readData2(){
        try {
            File file = new File(getCacheDir(), FILE_NAME_Cach2);//doc file cache
//            FileInputStream in = openFileInput(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringBuffer strBuffer = new StringBuffer();
            String line = null;//Doc tung dong trong strBuffer
            try {
                while((line = bufferedReader.readLine()) != null){
                    strBuffer.append(line).append("\n");
                }
                Log.d("MainActivity", "readData: "+strBuffer.toString());
                tvData.setText(strBuffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
