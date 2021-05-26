package com.example.androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class RecordActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 1000;

    Button btnRecord, btnPlay, btnStop;

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    String pathPlay = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        requestPermissionFromDevice();

        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);



        // ja tem permissao pra utilizar
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermissionFromDevice()){
                    pathPlay = Environment.getExternalStorageDirectory().getAbsolutePath() +
                            "/" + UUID.randomUUID().toString() + "_audio_record.3gp";

                    setupMediaRecorder();

                    try{
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    btnStop.setEnabled(true);
                    btnPlay.setEnabled(false);

                    Toast.makeText(RecordActivity.this, "Gravando áudio...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RecordActivity.this, "LALALA AQUI", Toast.LENGTH_SHORT).show();
                    requestPermissionFromDevice();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mediaRecorder.stop();
                } catch(RuntimeException ex){
                    ex.printStackTrace();
                }

                btnStop.setEnabled(true);
                btnPlay.setEnabled(true);
                btnRecord.setEnabled(true);

                Toast.makeText(RecordActivity.this, pathPlay, Toast.LENGTH_LONG).show();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();

                try {
                    mediaPlayer.setDataSource(pathPlay);
                    mediaPlayer.prepare();
                } catch(IOException ex){
                    ex.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(RecordActivity.this, "Reproduzindo áudio...", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private boolean checkPermissionFromDevice(){
        int WRITE_EXTERNAL_STORAGE_RESULT = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int RECORD_AUDIO_RESULT = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        return WRITE_EXTERNAL_STORAGE_RESULT == PackageManager.PERMISSION_GRANTED &&
                RECORD_AUDIO_RESULT == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissionFromDevice() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(RecordActivity.this, "Permissão concedida", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RecordActivity.this, "Permissão negada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setupMediaRecorder(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathPlay);
    }

}