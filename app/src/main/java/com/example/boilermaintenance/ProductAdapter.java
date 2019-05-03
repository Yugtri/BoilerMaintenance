package com.example.boilermaintenance;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.annotation.*;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<ProductGS> {
    private Activity ctx;
    private List<ProductGS> productList;

    public ProductAdapter(Activity ctx, List<ProductGS> productList) {
        super(ctx,R.layout.row_item,productList);
        this.ctx = ctx;
        this.productList = productList;
    }

    //@androidx.annotation.NonNull
    @NonNull


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=ctx.getLayoutInflater();
        View listView=inflater.inflate(R.layout.row_item,null,true);
        TextView id=(TextView)listView.findViewById(R.id.imageView);
        TextView title=(TextView)listView.findViewById(R.id.textViewTitle);
        TextView quan=(TextView)listView.findViewById(R.id.textViewPrice);
        ProductGS productGS =productList.get(position);
        //id.setText();
        return super.getView(position, convertView, parent);
    }
}
