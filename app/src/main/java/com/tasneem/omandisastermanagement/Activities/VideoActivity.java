package com.tasneem.omandisastermanagement.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.adapters.RequestAdapter;
import com.tasneem.omandisastermanagement.adapters.VideosAdapter;
import com.tasneem.omandisastermanagement.models.Video;
import com.tasneem.omandisastermanagement.models.VolunteerRequest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class VideoActivity extends AppCompatActivity {

    private List<Video> videos;
    private Toolbar toolbar;
    private static final String TAG = "VideosRepository";
    Video item;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
// set title ..
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tips For Disaster Management");
        setSupportActionBar(toolbar);

        reference = FirebaseDatabase.getInstance().getReference("Videos");
        // display list view ..
        videos = new ArrayList<>();
        ListView lv = findViewById(R.id.videos_list);
        VideosAdapter adapter = new VideosAdapter(getApplicationContext(), videos ,R.layout.videos_item_layout);
        lv.setAdapter(adapter);
        item = new Video();

        // get videos from db ..

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Video v = child.getValue(Video.class);

                    assert v != null;
                    videos.add(new Video(v.getVideo_title(), v.getVideo_URL(), v.getVideo_IMG()));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        loadVideosData();
// go to video link ..
        lv.setOnItemClickListener((parent, view, position, id) -> {
            String url = videos.get(position).getVideo_URL();
            //String title = videoLectures.get(position).getVideo_title();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

    }
}