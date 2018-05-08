package com.dreads.dogapp.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dog {
    private String id;
    private String url;
    private String time;
    private String format;
    private String verified;
    private String checked;

    public Dog(String id, String url, String time, String format, String verified, String checked) {
        this.id = id;
        this.url = url;
        this.time = time;
        this.format = format;
        this.verified = verified;
        this.checked = checked;
    }

    public Dog() {
    }


    public String getId() {
        return id;
    }

    public Dog setId(String id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Dog setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Dog setTime(String time) {
        this.time = time;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public Dog setFormat(String format) {
        this.format = format;
        return this;
    }

    public String getVerified() {
        return verified;
    }

    public Dog setVerified(String verified) {
        this.verified = verified;
        return this;
    }

    public String getChecked() {
        return checked;
    }

    public Dog setChecked(String checked) {
        this.checked = checked;
        return this;
    }
    public boolean isFavorite(){
        return  Favorite.findByDogId(getId()) !=null;
    }
    public Dog setFavorite(boolean isFavorite){
        if (isFavorite == isFavorite()) return this;
        if (isFavorite){
            new Favorite(getId()).save();
        }else {
            Favorite.findByDogId(getId()).delete();
        }
        return this;
    }
    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("id",getId());
        bundle.putString("url",getUrl());
        bundle.putString("time",getTime());
        bundle.putString("format",getFormat());
        bundle.putString("verified",getVerified());
        bundle.putString("checked",getChecked());
        return bundle;
    }

    public static class Builder{
        private Dog dog;
        private List<Dog> dogs;

        public Builder(){
            this.dog = new Dog();
            this.dogs = new ArrayList<>();
        }
        public Builder(Dog data){
            this.dog = data;
        }
        public Builder(List<Dog> dataList){
            this.dogs = dataList;
        }
        public Dog build(){
            return dog;
        }
        public List<Dog> buildAll(){
            return dogs;
        }

        public static Builder from(Bundle bundle){
            return new Builder(new Dog(
                    bundle.getString("id"),
                    bundle.getString("url"),
                    bundle.getString("time"),
                    bundle.getString("format"),
                    bundle.getString("verified"),
                    bundle.getString("checked")));
        }
        public static Builder from(JSONObject jsonDog){
            try {
                return new Builder(new Dog(
                        jsonDog.getString("id"),
                        jsonDog.getString("url"),
                        jsonDog.optString("time"),
                        jsonDog.optString("format"),
                        jsonDog.optString("verified"),
                        jsonDog.optString("checked")));

            }catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }
        public static Builder from(JSONArray jsonDogs){
            int length = jsonDogs.length();
            List<Dog> dogs = new ArrayList<>();
            for (int i = 0; i < length; i++){
                try {
                    dogs.add(Builder.from(jsonDogs.getJSONObject(i)).build());
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
            return new Builder(dogs);
        }
    }
}
