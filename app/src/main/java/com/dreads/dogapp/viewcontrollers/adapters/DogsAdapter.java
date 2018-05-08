package com.dreads.dogapp.viewcontrollers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.dreads.dogapp.R;
import com.dreads.dogapp.models.Dog;
import com.dreads.dogapp.viewcontrollers.activities.DogActivity;

import java.util.List;

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.ViewHolder> {
    private List<Dog> dogs;

    public DogsAdapter() {
    }

    public DogsAdapter(List<Dog> dogs) {
        this.dogs = dogs;
    }

    @NonNull
    @Override
    public DogsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dog, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DogsAdapter.ViewHolder holder, int position) {
        holder.updateView(dogs.get(position));
    }

    @Override
    public int getItemCount() {
        return dogs.size();
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public DogsAdapter setDogs(List<Dog> dogs) {
        this.dogs = dogs;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ANImageView pictureImageView;
        private TextView titleTexView;
        private ConstraintLayout dogLayout;
        private ImageView favoriteImageView;
        public ViewHolder(View view) {
            super(view);
            pictureImageView = (ANImageView) view.findViewById(R.id.image_picture);
            titleTexView = (TextView) view.findViewById(R.id.text_title);
            dogLayout = (ConstraintLayout) view.findViewById(R.id.layout_dog);
            favoriteImageView = (ImageView) view.findViewById(R.id.image_favorite);
        }

        public void updateView(final Dog dog){
            pictureImageView.setDefaultImageResId(R.mipmap.ic_launcher_dog);
            pictureImageView.setErrorImageResId(R.mipmap.ic_launcher_dog);
            pictureImageView.setImageUrl(dog.getUrl());
            titleTexView.setText(dog.getId());
            favoriteImageView.setImageResource(dog.isFavorite() ?
            R.drawable.ic_favorite_black_24dp :
            R.drawable.ic_favorite_border_black_24dp);
            dogLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    context.startActivity(new Intent(context, DogActivity.class)
                            .putExtras(dog.toBundle()));
                }
            });
        }


    }
}
