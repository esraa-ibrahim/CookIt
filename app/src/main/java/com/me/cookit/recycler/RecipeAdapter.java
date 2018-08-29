package com.me.cookit.recycler;

import android.content.Context;
import android.net.Uri;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.me.cookit.R;

import java.util.List;

/**
 * Created by Dytstudio.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    private List<ItemRecipe> items;
    private Context context;
    private boolean active;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView recipe, time;
        public RatingBar ratingBar;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);

            recipe = (TextView) view.findViewById(R.id.tv_recipe_name);
            time = (TextView) view.findViewById(R.id.tv_time);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            imageView = (ImageView) view.findViewById(R.id.iv_recipe);
        }


    }


    public RecipeAdapter(List<ItemRecipe> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);

        ((TextView)itemView.findViewById(R.id.tv_time)).setCompoundDrawablesWithIntrinsicBounds(
                AppCompatResources.getDrawable(context, R.drawable.ic_time),
                null, null, null);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ItemRecipe itemRecipe = items.get(position);
        holder.recipe.setText(itemRecipe.getRecipe());
        holder.time.setText(itemRecipe.getTime());
        holder.ratingBar.setRating(itemRecipe.getRating());
        Glide.with(context)
                .load(Uri.parse(itemRecipe.getImg()))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}