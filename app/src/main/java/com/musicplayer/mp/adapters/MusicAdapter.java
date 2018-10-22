package com.musicplayer.mp.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.musicplayer.mp.entities.Music;
import com.musicplayer.mp.MainActivity;
import com.musicplayer.mp.R;
import com.musicplayer.mp.entities.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends ArrayAdapter<Music> {

    private Context mContext;
    private  List<Music> musicList = new ArrayList<Music>();

    public MusicAdapter(@NonNull Context context, ArrayList<Music> list)
    {
        super(context, 0, list);
        mContext = context;
        musicList = list;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.custom_music_list,parent,false);

        Music currentMusic = musicList.get(position);

        TextView music = (TextView) listItem.findViewById(R.id.Music);
        music.setText(currentMusic.getMusic());

        return listItem;
    }


}
