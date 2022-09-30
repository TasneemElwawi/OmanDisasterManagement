package com.tasneem.omandisastermanagement.Activities;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.models.Video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

public class OneVideoActivity extends AppCompatActivity {
    private Toolbar toolbar;

// THE LINK FROM DB STORAGE ..

    private String myVideoURL = "https://firebasestorage.googleapis.com/v0/b/graduationproject-2ea8c.appspot.com/o/videos%2F%D9%86%D8%B8%D8%A7%D9%85%20%D8%A7%D9%84%D8%AA%D9%88%D8%AC%D9%8A%D9%87%20-%20%D8%A7%D9%84%D8%AF%D8%B1%D9%83%D8%B3%D9%88%D9%86%20-%20%D8%A7%D9%84%D9%83%D9%87%D8%B1%D8%A8%D8%A7%D8%A6%D9%8A.mp4?alt=media&token=b519e68b-fc19-4c71-b235-b7e9dc29d6dc";
    PlayerView playerView;
    TextView titleTV;
    String video_title, video_url;
    private ExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_video);

        ActivityCompat.requestPermissions(OneVideoActivity.this,
                new String[]{Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                100);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tips For Disaster Management ");
        setSupportActionBar(toolbar);


        // get video object from intent ..
        Video video = (Video) getIntent().getSerializableExtra("video_obj");
         video_title = video.getVideo_title();
         video_url = video.getVideo_URL();
//        Toast.makeText(getApplicationContext(), "video_url"+ video_url, Toast.LENGTH_SHORT).show();

        playerView = findViewById(R.id.playerView_lecture);
        titleTV = findViewById(R.id.txtV_title_of_video);
        titleTV.setText(video_title);



    }

    private void initializePlayer() {
//        player = ExoPlayerFactory.newSimpleInstance(this);

        player = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player);


        Uri uri = Uri.parse(video_url);
        MediaSource mediaSource = buildMediaSource(uri);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare(mediaSource, false, false);
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }
    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, "exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(uri));
    }

    @Override
    public void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (( player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }
}