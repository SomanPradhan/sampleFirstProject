package com.example.soman.samplefirstproject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Soman on 8/2/2017.
 */
public class ListItem implements Serializable {

    private String name;

    private String ownername;

    private String description;

    private String imageUrl;

    public ListItem(String name, String ownername, String description,String imageUrl) {
        this.name = name;
        this.ownername = ownername;
        this.description = description;
        this.imageUrl = imageUrl;
    }



    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownername;
    }

    public String getDescription() {
        return description;
    }


    public String getImageUrl(){
        return imageUrl;
    }
}
