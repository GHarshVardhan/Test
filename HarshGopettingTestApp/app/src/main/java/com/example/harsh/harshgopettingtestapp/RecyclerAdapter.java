package com.example.harsh.harshgopettingtestapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private final Activity context;
    private List<Datum> tableData;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, endDate;
        ImageView imageView;

        MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            endDate = (TextView) view.findViewById(R.id.end_date);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }


    public RecyclerAdapter(List<Datum> tabledata, Activity context) {
        this.tableData = tabledata;
        this.context = context;
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher).resetViewBeforeLoading()
                .cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(300)).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .defaultDisplayImageOptions(options)
                .memoryCache(new WeakMemoryCache())
                .build();
        ImageLoader.getInstance().init(config);
        sharedpreferences = context.getSharedPreferences("MyPREFERENCES", Context.MODE_MULTI_PROCESS);
        isStoragePermissionGranted();
    }

    private boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {


                ActivityCompat.requestPermissions(context, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Datum table = tableData.get(position);
        holder.name.setText(table.getName());
        holder.endDate.setText(table.getEndDate());
        imageLoader.displayImage(table.getIcon(), holder.imageView, options);
        holder.imageView.setTag(table.getName());
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_layout);
                // set the custom dialog components - text
                final TextView text = (TextView) dialog.findViewById(R.id.textView);
                String status = sharedpreferences.getString("key"+view.getTag(), "DEFAULT");
                if (status.equals("Added")){
                    text.setText("Remove From Cart");
                }else {
                    text.setText("Add To Cart");
                }
                // if button is clicked, close the custom dialog
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor = sharedpreferences.edit();
                        if (text.getText().equals("Add To Cart")) {
                            editor.putString("key"+view.getTag(), "Added");
                        } else {
                            editor.putString("key"+view.getTag(), "Removed");
                        }
                        editor.apply();
                        dialog.dismiss();
                    }
                });

                dialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableData.size();
    }
}
