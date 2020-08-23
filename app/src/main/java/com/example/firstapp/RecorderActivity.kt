package com.example.firstapp

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_recorder.*
import java.io.IOException

class RecorderActivity : AppCompatActivity() {
    private var mediaRecorder: MediaRecorder? =null
    private var output: String? =null
    private var status: Boolean = false
    private var recordingStopped: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(this, "START GANESH", Toast.LENGTH_LONG).show()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recorder)
        Toast.makeText(this, "You are Now!", Toast.LENGTH_SHORT).show()
        mediaRecorder= MediaRecorder()
        output = Environment.DIRECTORY_DOWNLOADS+"/recording.mp3"
        Toast.makeText(applicationContext,"test $output",Toast.LENGTH_LONG).show()

        mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder!!.setOutputFile(output)

        button_start_recording.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this, permissions,0)
            } else {
                startRecording()
            }
        }
        button_stop_recording.setOnClickListener{
            stopRecording()
        }

        button_pause_recording.setOnClickListener {
            pauseRecording()
        }
    }

    @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun pauseRecording() {
        if(status) {
            if(!recordingStopped){
                Toast.makeText(this,"Stopped!", Toast.LENGTH_SHORT).show()
                mediaRecorder?.pause()
                recordingStopped = true
                //button_pause_recording.text = "Resume"
            }else{
                resumeRecording()
            }
        }
    }

    private fun stopRecording() {
        if(status){
            mediaRecorder?.stop()
            mediaRecorder?.release()
            status = false
        }else{
            Toast.makeText(this, "You are not recording right now!", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("RestrictedApi", "SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    private fun resumeRecording() {
        Toast.makeText(this,"Resume!", Toast.LENGTH_SHORT).show()
        mediaRecorder?.resume()
       // button_pause_recording.text = "Pause"
        recordingStopped = false
    }

    private fun startRecording() {
        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            status = true
        }catch (e:IllegalStateException){
            e.printStackTrace()
        }catch (e:IOException){
            e.printStackTrace()
        }
    }


}