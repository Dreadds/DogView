package com.dreads.dogapp.viewcontrollers.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.widget.ANImageView;
import com.dreads.dogapp.R;
import com.dreads.dogapp.models.Dog;

public class DogActivity extends AppCompatActivity {

    ANImageView logoImage;
    TextView    textView;
    ImageButton favButton;
    Dog dog;
    boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent == null) return;

        dog = Dog.Builder.from(intent.getExtras()).build();
        isFavorite = dog.isFavorite();
        logoImage = (ANImageView) findViewById(R.id.image_logo);
        textView = (TextView) findViewById(R.id.id_TextView);
        favButton = (ImageButton) findViewById(R.id.favoriteButtonBorder);
        updateView(dog);


    }

    private void updateView( final Dog dog) {
        logoImage.setDefaultImageResId(R.mipmap.ic_launcher_dog);
        logoImage.setErrorImageResId(R.mipmap.ic_launcher_dog);
        logoImage.setImageUrl(dog.getUrl());
        textView.setText(dog.getTime());
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite(dog);
            }
        });

    }

    private void toggleFavorite(Dog dog){
        isFavorite = !isFavorite;
        dog.setFavorite(isFavorite);
        favButton.setImageResource(isFavorite ? R.drawable.ic_favorite_black_24dp :
        R.drawable.ic_favorite_border_black_24dp);
        Context context = getApplicationContext();
        CharSequence text = "HI";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
