package com.musicplayer.mp;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

public class MusicPlayerFragment extends Fragment {
    public static Button Start;
    public static Button Stop;
    MediaPlayer mPlayer = null;

    public View onCreateView( LayoutInflater inflater ,  ViewGroup viewGroup ,  Bundle bundle) {
        View view = inflater.inflate(R.layout.musicplayer_fragment,viewGroup,false);
        Start = view.findViewById(R.id.Start);
        Stop = view.findViewById(R.id.Stop);


        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( mPlayer != null && mPlayer.isPlaying ( ) ) {

                    mPlayer.stop ( );
                    Start.setClickable ( true );
                    Toast.makeText ( getActivity ( ) , "You stoped the music!" , Toast.LENGTH_SHORT ).show ( );

                }
            }
        });

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( MusicListFragment.musicUri != null ) {
                    if ( mPlayer != null ) {
                        mPlayer.stop ( );
                    }
                    mPlayer = new MediaPlayer( );
                    mPlayer.setAudioStreamType ( AudioManager.STREAM_MUSIC );

                    try {

                        mPlayer.setDataSource ( getActivity ( ) , MusicListFragment.musicUri );
                        mPlayer.prepare ( );
                        mPlayer.start ( );


                        if ( mPlayer != null && mPlayer.isPlaying ( ) ) {

                            Start.setClickable ( false );
                        }
                        Toast.makeText ( getActivity ( ) , "You start the music!" , Toast.LENGTH_SHORT ).show ( );


                    } catch ( IllegalArgumentException e ) {

                        Toast.makeText ( getActivity ( ) , "You might not set the URI correctly!" , Toast.LENGTH_LONG ).show ( );

                    } catch ( SecurityException e ) {

                        Toast.makeText ( getActivity ( ) , "You might not set the URI correctly!" , Toast.LENGTH_LONG ).show ( );

                    } catch ( IllegalStateException e ) {

                        Toast.makeText ( getActivity ( ) , "You might not set the URI correctly!" , Toast.LENGTH_LONG ).show ( );

                    } catch ( IOException e ) {

                        e.printStackTrace ( );

                    }


                } else {
                    Toast.makeText ( getActivity ( ) , "Please click one musio!" , Toast.LENGTH_LONG ).show ( );

                }
            }
        });
        return view;
    }


}
