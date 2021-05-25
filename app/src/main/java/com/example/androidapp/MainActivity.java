package com.example.androidapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

//    private ImageView imgView;
//    private ImageButton btnOpen;
//    private String currPhotoPath;
//    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private Button btnCamera;
    private Button btnAudio;
    private Button btnConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = (Button) findViewById(R.id.camera);
        btnAudio = (Button) findViewById(R.id.audio);
        btnConfig = (Button) findViewById(R.id.config);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCaptureActivity();
            }
        });

        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecordActivity();
            }
        });

        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingActivity();
            }
        });



        //imgView = findViewById(R.id.imageView);
        //btnOpen = (ImageButton) findViewById(R.id.btnOpen);

        //btnOpen.setOnClickListener(this::takePicture);
//
//        //pedir permissão pra camera
//        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//            String[] permissions = {Manifest.permission.CAMERA};
//            ActivityCompat.requestPermissions(MainActivity.this, permissions, 100);
//        }
//
//        btnOpen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Abrir camera
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 100);
//            }
//        });
    }

    public void openCaptureActivity() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivity(intent);
    }

    public void openRecordActivity() {
        Intent intent = new Intent(this, RecordActivity.class);
        startActivity(intent);
    }

    public void openSettingActivity() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

//    // função para dar a ordem (Intent) de utilizar o app de camera
//    public void takePicture(View view){
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
//    }
//
//    // Intent de retorno do final da nossa Intent inical (Mandar tirar foto)
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == 100 && resultCode == RESULT_OK){ // Capiturar e setar imagem na tela
//            Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
//            imgView.setImageBitmap(imgBitmap);
//
//            Uri tempUri = getImageUri(getApplicationContext(), imgBitmap);
//
//            String path = getPathFromUri(tempUri);
//
//            Toast t = Toast.makeText(getApplicationContext(), path, Toast.LENGTH_SHORT);
//            t.show();
//        }
//    }
//
//    public Uri getImageUri(Context inContext, Bitmap inImage){
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }
//
//    public String getPathFromUri(Uri uri){
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        cursor.moveToFirst();
//        int i = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//        return cursor.getString(i);
//    }
//
//    private String createImageFile() throws IOException {
//        // Criando um arquivo para salvar a foto que será tirada
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imgFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(imgFileName, ".jpeg", storageDir);
//
//        // Salvando o path do arquivo criado
//        currPhotoPath = image.getAbsolutePath();
//        return currPhotoPath;
//    }
}