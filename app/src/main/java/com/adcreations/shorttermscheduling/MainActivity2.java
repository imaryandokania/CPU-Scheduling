package com.adcreations.shorttermscheduling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        TextView textView=(TextView)findViewById(R.id.textView8);

                imageView.animate().alpha(1).setDuration(3000);

       textView.setEnabled(true);
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000);
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}