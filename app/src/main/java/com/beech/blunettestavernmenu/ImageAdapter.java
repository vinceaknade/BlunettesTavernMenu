package com.beech.blunettestavernmenu;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Wayne Beech on 12/8/2015.
 */
public class ImageAdapter extends BaseAdapter
{

    private Context context;
    private int itemBackground;
    Integer[] imageIDs;

    public ImageAdapter(Context context, Integer[] imageIDs)
    {
        this.context = context;
        this.imageIDs = imageIDs;

        //NOTE watch this part closely may need debugging
        // sets a grey background; wraps around the images
        TypedArray a = context.getTheme().obtainStyledAttributes(R.styleable.MenuGallery);
        itemBackground = a.getResourceId(R.styleable.MenuGallery_android_galleryItemBackground, 0);
        a.recycle();
    }
    // returns the number of images
    public int getCount() {
        return imageIDs.length;
    }
    // returns the ID of an item
    public Object getItem(int position) {
        return position;
    }
    // returns the ID of an item
    public long getItemId(int position) {
        return position;
    }
    // returns an ImageView view
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageIDs[position]);
        imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));
        imageView.setBackgroundResource(itemBackground);
        return imageView;
    }
}
