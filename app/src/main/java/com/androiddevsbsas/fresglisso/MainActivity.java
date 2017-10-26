package com.androiddevsbsas.fresglisso;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private String troyUrl = "https://i.imgur.com/9tUQpEc.png";
    private String fortUrl = "https://i.imgur.com/rAvezp2.jpg";
    private String atendedorUrl = "https://media.giphy.com/media/3o7aCU7plfW5Z4Foxa/giphy.gif";
    private String webpUrl = "http://demos.mattwest.io/webp-demo-images/result.webp";
    private ImageView glideSample, picassoSample;
    private SimpleDraweeView imageFresco;
    private Boolean loadAtInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picassoSample = findViewById(R.id.picasso_sample);
        glideSample = findViewById(R.id.glide_sample);
        imageFresco = findViewById(R.id.fresco_sample);

        findViewById(R.id.glide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadGlideSample();
            }
        });

        findViewById(R.id.fresco).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFrescoSample();
            }
        });

        findViewById(R.id.picasso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPicassoSample();
            }
        });

        if (loadAtInit) {
            loadPicassoSample();
            loadFrescoSample();
            loadGlideSample();
        }
    }

    private void loadPicassoSample() {
        Picasso.with(MainActivity.this).load(troyUrl).into(picassoSample);
    }

    private void loadGlideSample() {
        Glide.with(MainActivity.this).load(atendedorUrl).into(glideSample);
    }

    private void loadFrescoSample() {
        imageFresco.setImageURI(Uri.parse(fortUrl));
    }
}
