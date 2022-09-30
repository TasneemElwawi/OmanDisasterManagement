package com.tasneem.omandisastermanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tasneem.omandisastermanagement.Activities.OneVideoActivity;
import com.tasneem.omandisastermanagement.R;
import com.tasneem.omandisastermanagement.models.Video;

import java.util.List;

public class VideosAdapter extends BaseAdapter {

    Context context;
    List<Video> videos;
    int layoutItem;

    public VideosAdapter(Context context, List<Video> videos, int layoutItem) {
        this.context = context;
        this.videos = videos;
        this.layoutItem = layoutItem;

    }

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Object getItem(int i) {
        return videos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(layoutItem, null);
            ViewHolder vh = new ViewHolder();
            vh.vid_Img = view.findViewById(R.id.about_img);
            vh.vTitle_TV = view.findViewById(R.id.aboutSchoolTV);
            vh.show_btn = view.findViewById(R.id.show_btn);
            view.setTag(vh);
        }
        ViewHolder vh = (ViewHolder) view.getTag();

            vh.vTitle_TV.setText(videos.get(i).getVideo_title());
            String img_URL = videos.get(i).getVideo_IMG();
            String videoURL = videos.get(i).getVideo_URL();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.emargense);

        Glide.with(context).load(img_URL).apply(options).into(vh.vid_Img);


        vh.show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OneVideoActivity.class);

                Bundle b = new Bundle();
                b.putSerializable("video_obj", videos.get(i));
                intent.putExtras(b);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return view;
    }
}

class ViewHolder {
    ImageView vid_Img;
    TextView vTitle_TV;
    Button show_btn;
}
