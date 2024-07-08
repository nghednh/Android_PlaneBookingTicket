package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ImageButtonAdapter extends BaseAdapter {

    private Context context;
    private int[] images;
    private String[] texts;
    private LayoutInflater inflater;

    public ImageButtonAdapter(Context context, int[] images, String[] texts) {
        this.context = context;
        this.images = images;
        this.texts = texts;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_image_button, parent, false);
        }

        ImageButton imageButton = convertView.findViewById(R.id.imageButton);
        TextView textView = convertView.findViewById(R.id.textView);

        imageButton.setImageResource(images[position]);
        textView.setText(texts[position]);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                    case 2:
                    case 3:
                        //toast message: in development
                        Toast.makeText(context, "This feature is currently in development!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        context.startActivity(new Intent(context, TransportActivity.class));
                        break;
                }
            }
        });

        return convertView;
    }
}
