package com.androiddevsbsas.fresglisso;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.androiddevsbsas.fresglisso.glide.GlideApp;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private String troyUrl = "https://i.imgur.com/9tUQpEc.png";
    private String fortUrl = "https://i.imgur.com/rAvezp2.jpg";
    private String atendedorUrl = "https://media.giphy.com/media/3o7aCU7plfW5Z4Foxa/giphy.gif";
    private String webpUrl = "http://demos.mattwest.io/webp-demo-images/result.webp";
    private String hdImageUrl = "https://images8.alphacoders.com/411/411509.jpg";
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

        this.bindListeners();

        if (loadAtInit) {
            loadPicassoSample();
            loadFrescoSample();
            loadGlideSample();
        }
    }

    private void bindListeners() {
        findViewById(R.id.button_clear_cache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCache();
            }
        });

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
    }

    private void clearCache() {
        this.clearCachePicasso();
        this.clearCacheFresco();
        this.clearCacheGlide();
    }

    private void clearCacheGlide() {
        //Cambios de tamaño temporales.
//        GlideApp.get(this).setMemoryCategory(MemoryCategory.HIGH);
//        GlideApp.get(this).setMemoryCategory(MemoryCategory.NORMAL);

        //Limpio imagen
        GlideApp.with(this).clear(this.glideSample);

        //Limpio Memory Cache
        GlideApp.get(this).clearMemory();

        //Limpio Disk Cache:
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                // This method must be called on a background thread.
                GlideApp.get(MainActivity.this).clearDiskCache();
                return null;
            }
        }.execute();
    }

    private void clearCacheFresco() {
    }

    private void clearCachePicasso() {
    }

    private void loadPicassoSample() {
        Picasso.with(MainActivity.this).load(troyUrl).into(picassoSample);
    }

    private void loadGlideSample() {
        GlideApp.with(MainActivity.this)
                .load(this.atendedorUrl)
                //PlaceHolders
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.error)
                .fallback(R.mipmap.info)
                //Simple Transformations
//                .apply(RequestOptions.circleCropTransform())
//                .circleCrop()
                //Multiple Transformations
//                .transform(new MultiTransformation(new CircleCrop(), new FitCenter()))
                .transforms(new FitCenter(), new CircleCrop())
                //Transitions
                .transition(new DrawableTransitionOptions().crossFade())
                .into(this.glideSample);
    }

    private void loadFrescoSample() {
        // Show a progress bar for huge images
        imageFresco.setHierarchy(initHierarchy());
        imageFresco.setImageURI(Uri.parse(hdImageUrl));
    }

    private GenericDraweeHierarchy initHierarchy() {
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        GenericDraweeHierarchy hierarchy = builder.setFadeDuration(500).build();
        hierarchy.setProgressBarImage(new ProgressBarDrawable());
        hierarchy.setPlaceholderImage(R.mipmap.ic_launcher_round);
        hierarchy.setFailureImage(R.mipmap.error);
        return hierarchy;
    }
}
