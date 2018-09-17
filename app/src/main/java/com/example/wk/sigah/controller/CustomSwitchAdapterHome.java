package com.example.wk.sigah.controller;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wk.sigah.R;

/**
 * Created by Kharisma on 26/03/2018.
 */

public class CustomSwitchAdapterHome extends PagerAdapter {
    private int[] image_resource={R.drawable.homepagenew,R.drawable.home2,R.drawable.home3};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public CustomSwitchAdapterHome(Context ctx){
        this.ctx=ctx;
    }

    @Override
    public int getCount() {
        return image_resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return(view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView=(ImageView)item_view.findViewById(R.id.image_view);
        TextView textView=(TextView)item_view.findViewById(R.id.image_count);
        imageView.setImageResource(image_resource[position]);
        textView.setText(" "+position);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((LinearLayout)object);
    }

}
