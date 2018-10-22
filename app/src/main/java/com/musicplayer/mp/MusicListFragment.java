package com.musicplayer.mp;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.musicplayer.mp.entities.Music;

import java.io.IOException;
import java.util.ArrayList;

public class MusicListFragment extends Fragment {
    private static final int MY_PERMISSION_REQUEST = 1;
    ListView listView;
    ArrayList<Music> arrayList;
    MediaPlayer mPlayer = null;
    Button Start;
    public static Uri musicUri = null;
    Context context;
    Fragment fragment;


    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup ,Bundle bundle) {
        View view = inflater.inflate(R.layout.music_list_fragment,viewGroup,false);
        context = viewGroup.getContext();
        listView = view.findViewById(R.id.list);
            Start = view.findViewById(R.id.Start);



        arrayList = new ArrayList <>();


        if ( ContextCompat.checkSelfPermission ( getContext(), Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED ) {
            if ( ActivityCompat.shouldShowRequestPermissionRationale ( getActivity() , Manifest.permission.READ_EXTERNAL_STORAGE ) ) {
                ActivityCompat.requestPermissions ( getActivity() , new String[] { Manifest.permission.READ_EXTERNAL_STORAGE } , MY_PERMISSION_REQUEST );
            } else {
                ActivityCompat.requestPermissions ( getActivity() , new String[] { Manifest.permission.READ_EXTERNAL_STORAGE } , MY_PERMISSION_REQUEST );
            }

        } else {

            doStuff ();
        }


        listView.setOnItemClickListener ( new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick (AdapterView <?> parent , View view , int position , long id ) {

                musicUri = Uri.parse ( arrayList.get ( position ).getUri ( ) );
                Toast.makeText(getContext(),"Music Selected", Toast.LENGTH_SHORT).show();

                MusicPlayerFragment.Start.setClickable ( true );
            }
        } );


        return view;
    }




    public void doStuff ()
    {
        getMusics ( );
        arrayList = new ArrayList <Music> ( );
        getMusics ( );
        ArrayAdapter<Music> adapter = new ArrayAdapter <Music> ( getActivity(), android.R.layout.simple_list_item_1 , arrayList );
        listView.setAdapter ( adapter );
    }




    public void getMusics ( )
    {
        ContentResolver contentResolver = context.getContentResolver ( );
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        final Cursor songCursor = contentResolver.query ( songUri , null , null , null , null );

        if ( songCursor != null && songCursor.moveToFirst ( ) ) {
            int songTitle = songCursor.getColumnIndex ( MediaStore.Audio.Media.TITLE );

            int songLocation = songCursor.getColumnIndex ( MediaStore.Audio.Media.DATA );


            do {
                String currentTitle = songCursor.getString ( songTitle );

                String currentLocation = songCursor.getString ( songLocation );

                arrayList.add ( new Music ( currentTitle , currentLocation ) );

            }
            while ( songCursor.moveToNext ( ) );
        }
    }



    public void onRequestPErmissionResult ( int requestCode , String[] permissions , @NonNull int[] grantResults )
    {
        switch ( requestCode ) {
            case MY_PERMISSION_REQUEST: {
                if ( grantResults.length > 0 && grantResults[ 0 ] == PackageManager.PERMISSION_GRANTED ) {
                    if ( ContextCompat.checkSelfPermission ( context, Manifest.permission.READ_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED ) {
                        Toast.makeText ( context , "Permission granted!" , Toast.LENGTH_LONG ).show ( );

                        doStuff ( );

                    }
                } else {
                    Toast.makeText ( context , "no permission granted" , Toast.LENGTH_LONG ).show ( );
                   // finish ( );
                }
                return;
            }
        }
    }
}
