package com.example.yassarchohan.virtual_shopping_app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Yassar chohan on 9/30/2017.
 */
public class Custom_adapter extends RecyclerView.Adapter<Custom_adapter.Myview> {

    ImageView img;
    TextView txt;
    TextView txt2;
    TextView txt3;
    RatingBar rating;

    private List<Getter_methods> list;

    public Custom_adapter(feed_back feed_back, List<Getter_methods> list) {

    }


    public class Myview extends RecyclerView.ViewHolder{

        public Myview(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.toshowproductimgs);
        txt = (TextView) itemView.findViewById(R.id.forproductname);
         txt2 = (TextView) itemView.findViewById(R.id.forproduct_desc);
        txt3 = (TextView) itemView.findViewById(R.id.productprize);
        rating = (RatingBar) itemView.findViewById(R.id.ratings);



        }



    }

    public Custom_adapter(List<Getter_methods> clothlist){
        this.list = clothlist;
    }



    @Override
    public Myview onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_custom_view, parent, false);

        return new Myview(itemView);
    }

    @Override
    public void onBindViewHolder(Myview holder, int position) {


        Getter_methods message = list.get(position);
        img.setImageResource(message.getId());
        txt.setText(message.getProductname());
        txt2.setText(message.getProductdesc());
        txt3.setText("RS."+ message.getPrize());
        rating.setRating((float) message.getRatings());




    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}



































//@Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView == null){
//            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.activity_custom_view,parent,false);
//        }
//
//        ImageView img = (ImageView) convertView.findViewById(R.id.toshowproductimgs);
//        TextView txt = (TextView) convertView.findViewById(R.id.forproductname);
//        TextView txt2 = (TextView) convertView.findViewById(R.id.forproduct_desc);
//        TextView txt3 = (TextView) convertView.findViewById(R.id.productprize);
//        RatingBar rating = (RatingBar) convertView.findViewById(R.id.ratings);
//
//
//
//        Getter_methods message = getItem(position);
//
//        img.setImageResource(message.getId());
//        txt.setText(message.getProductname());
//        txt2.setText(message.getProductdesc());
//        txt3.setText("RS."+ message.getPrize());
//        rating.setRating((float) message.getRatings());
//
//
//        return convertView;
//    }