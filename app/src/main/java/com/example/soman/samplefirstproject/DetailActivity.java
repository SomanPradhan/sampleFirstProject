package com.example.soman.samplefirstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = new Intent();
        intent = getIntent();
        String name = intent.getStringExtra("name");
        String ownername = intent.getStringExtra("ownername");
        String imageUrl = intent.getStringExtra("imageUrl");
        String description = intent.getStringExtra("description");

        TextView TextViewname= (TextView) findViewById(R.id.TextViewNewName);
        TextView TextViewownername = (TextView) findViewById(R.id.textviewNewOwnerName);
        TextView TextViewdescription =  (TextView) findViewById(R.id.textviewNewDescription);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);


        Picasso.with(this).load(imageUrl).into(imageView);
        TextViewname.setText(name);
        TextViewownername.setText(ownername);
        if(!description.equalsIgnoreCase("null") && description!=null)
        TextViewdescription.setText(description);


    }
}
