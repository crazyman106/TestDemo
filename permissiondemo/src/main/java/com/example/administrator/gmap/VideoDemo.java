package com.example.administrator.gmap;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * @author lijunjie on 2017/12/18 0018.
 * @description
 */

public class VideoDemo extends AppCompatActivity {

    VideoView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_demo);
        imageView = findViewById(R.id.video);

        String uri = "android.resource://" + getPackageName() + "/" + R.raw.splash;
        imageView.setVideoURI(Uri.parse((uri)));
        imageView.setMediaController(new MediaController(VideoDemo.this));
        imageView.requestFocus();
        imageView.setMediaController(null);
        imageView.start();
        imageView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(VideoDemo.this, mp.getCurrentPosition() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
