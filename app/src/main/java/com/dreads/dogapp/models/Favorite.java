package com.dreads.dogapp.models;

import com.orm.SugarRecord;

import java.util.List;

public class Favorite extends SugarRecord {
    private String dogId;

    public Favorite() {
    }

    public Favorite(String dogId) {
        this.dogId = dogId;
    }

    public String getDogId() {
        return dogId;
    }

    public Favorite setDogId(String dogId) {
        this.dogId = dogId;
        return this;
    }

    public static Favorite findByDogId(String dogId){
        List<Favorite> favorites = Favorite.find(Favorite.class,
                "dog_id = ?", dogId);
        return favorites.isEmpty() ? null : favorites.get(0);
    }
    public static  String getDogIds(){
        List<Favorite> favorites = Favorite.listAll(Favorite.class);
        if (favorites.isEmpty()) return "";

        int size = favorites.size() > 20 ? 20: favorites.size();
        String dogIds = "";
        for (int i = 0; i< size; i++)
            dogIds += favorites.get(i).getDogId().length() + ",";
        dogIds.substring(0, dogIds.length() - 2);
        return dogIds;
    }
}
