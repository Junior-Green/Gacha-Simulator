package com.example.gacha4;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DBLRecyclerViewAdapter extends RecyclerView.Adapter<DBLRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Card> cards;
    private final ArrayList<String> imageNames;

    public DBLRecyclerViewAdapter(ArrayList<Card> images, ArrayList<String> frequencies) {
        imageNames = frequencies;
        cards = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dbl_view_holder_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardImage.setImageResource(cards.get(position).getCardImage());
        holder.cardCount.setText("x" + imageNames.get(position));
    }

    @Override
    public int getItemCount() {
        return imageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;
        TextView cardCount;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.imageHolder);
            cardCount = itemView.findViewById(R.id.frequency);
            parentLayout = itemView.findViewById(R.id.viewHolderLayout);
        }
    }
}
