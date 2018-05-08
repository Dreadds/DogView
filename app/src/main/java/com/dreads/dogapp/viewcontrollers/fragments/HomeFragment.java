package com.dreads.dogapp.viewcontrollers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dreads.dogapp.R;
import com.dreads.dogapp.models.Dog;
import com.dreads.dogapp.network.DogApi;
import com.dreads.dogapp.viewcontrollers.adapters.DogsAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private List<Dog> dogs;
    private RecyclerView dogsRecyclerView;
    private DogsAdapter dogsAdapter;
    private RecyclerView.LayoutManager dogsLayoutManager;
    private static String TAG = "DogApi";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        dogsRecyclerView = view.findViewById(R.id.recycler_dogs);
        dogs = new ArrayList<>();
        dogsAdapter = new DogsAdapter(dogs);
        dogsLayoutManager = new GridLayoutManager(view.getContext(), 2);
        dogsRecyclerView.setAdapter(dogsAdapter);
        dogsRecyclerView.setLayoutManager(dogsLayoutManager);
        updateData();
        return view;
    }

    private void updateData() {
        AndroidNetworking
                .get(DogApi.getDogUrl())
                .addQueryParameter("limit", "20")
                //.addQueryParameter("language", "en")
                .setPriority(Priority.LOW)
                .setTag(TAG)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(org.json.JSONObject response) {

                        try {
                            if ("error".equalsIgnoreCase(response.getString("error"))){
                                Log.d(TAG, String.format("Response Error: %s",
                                        response.getString("message")));
                                return;
                            }
                            dogs = Dog.Builder.from(response.getJSONArray("data")).buildAll();
                            dogsAdapter.setDogs(dogs);
                            dogsAdapter.notifyDataSetChanged();
                            Log.d(TAG,String.format("Dogs Count %d", dogs.size()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("DogApp",anError.getErrorDetail());
                    }
                });
    }

}
