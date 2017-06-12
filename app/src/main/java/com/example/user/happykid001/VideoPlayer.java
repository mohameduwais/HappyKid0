package com.example.user.happykid001;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

    public class VideoPlayer extends AppCompatActivity implements View.OnClickListener {

        ProgressDialog pd ;
        VideoView videoView;
        ImageButton btnPlay;

        String URL = "http://31.210.87.4/film/fullmp4best/Assassin_s_Creed_Official_Trailer_2_2016.mp4";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            videoView = (VideoView)findViewById(R.id.videoView);
            btnPlay = (ImageButton)findViewById(R.id.btnPlay);


        }

        @Override
        public void onClick(View v) {
            pd = new ProgressDialog(VideoPlayer.this);
            pd.setMessage("Buffering...");
            pd.setCanceledOnTouchOutside(false);
            pd.show();

            try{
                if(!videoView.isPlaying()){
                    Uri uri = Uri.parse(URL);
                    videoView.setVideoURI(uri);
                    videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            btnPlay.setImageResource(R.drawable.ic_play);

                        }
                    });

                }else{
                    videoView.pause();
                    btnPlay.setImageResource(R.drawable.ic_play);
                }

            }
            catch (Exception e){

                Log.e("Error" , e.getMessage());
                e.printStackTrace();

            }

            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    pd.dismiss();
                    mp.setLooping(true);
                    videoView.start();
                    btnPlay.setImageResource(R.drawable.ic_pause);
                }
            });
        }
    }