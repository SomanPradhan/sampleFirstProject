package com.example.soman.samplefirstproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Soman on 8/2/2017.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    private ArrayList<ListItem> listItems;
    private Context context;

    public RecycleAdapter(ArrayList<ListItem> listItems,Context context) {
        this.listItems = listItems;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListItem listItem = listItems.get(position);

        holder.textViewName.setText(listItem.getName());
        holder.textViewOwnerName.setText(listItem.getOwnerName());

        if(!listItem.getDescription().equalsIgnoreCase("null") && listItem.getDescription()!=null)
        holder.textViewDescription.setText(listItem.getDescription());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ListItem listItem1 = listItem;
                Intent intent = new Intent(context,DetailActivity.class);

                intent.putExtra("name",listItem1.getName());
                intent.putExtra("ownername",listItem1.getOwnerName());
                intent.putExtra("description",listItem1.getDescription());
                intent.putExtra("imageUrl",listItem1.getImageUrl());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewOwnerName;
        public TextView textViewDescription;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewOwnerName = (TextView) itemView.findViewById(R.id.textViewOwnerName);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
