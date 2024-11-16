package com.example.asm_and103.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asm_and103.Models.ProductModel;
import com.example.asm_and103.R;

import java.util.List;

public class AdapterProduct extends BaseAdapter {

    Context context;
    List<ProductModel> list;

    public AdapterProduct(Context context, List<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.layout_itemproduct, parent, false);

        TextView tvID = (TextView) rowView.findViewById(R.id.tvId);
        ImageView imgAvatar = (ImageView) rowView.findViewById(R.id.imgAvatatr);
        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);
        TextView tvPrice = (TextView) rowView.findViewById(R.id.tvPrice);
        TextView tvDescription = (TextView) rowView.findViewById(R.id.tvDescription);
        TextView tvCateID = (TextView)rowView.findViewById(R.id.tvCateID);
//        String imageUrl = mList.get(position).getThumbnailUrl();
//        Picasso.get().load(imageUrl).into(imgAvatar);
////        imgAvatar.setImageResource(imageId[position]);
        tvName.setText(String.valueOf(list.get(position).getProductName()));

//        tvID.setText(String.valueOf(list.get(position).getProductID()));

        tvPrice.setText(String.valueOf(list.get(position).getPrice()));

        tvDescription.setText(String.valueOf(list.get(position).getDescription()));
        tvCateID.setText(String.valueOf(list.get(position).getCateID()));
        return rowView;
    }
}
