package com.dreads.dogapp.network;

import com.dreads.dogapp.models.Favorite;

public class DogApi {
    private static String BASE_URL = "https://api.thedogapi.co.uk/v2";

    public static  String getDogUrl(){
        return BASE_URL + "/dog.php";
    }
    public static String getDogByIdDogs(){return  BASE_URL + "/dog.php?id=5ta5p7JdHEL";}
    public static String getDogById(){return  BASE_URL + "/dog.php?id=" + Favorite.getDogIds();}
}
