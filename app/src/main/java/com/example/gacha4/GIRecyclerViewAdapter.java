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

public class GIRecyclerViewAdapter extends RecyclerView.Adapter<GIRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Card> cards;
    private final ArrayList<String> imageNames;

    public GIRecyclerViewAdapter(ArrayList<Card> images, ArrayList<String> frequencies) {
        imageNames = frequencies;
        cards = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_holder_layout_genshin_impact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardImage.setImageResource(cards.get(position).getCardImage());
        holder.cardCount.setText("x" + imageNames.get(position));
        holder.cardName.setText(cards.get(position).getName());
        switch (cards.get(position).getRarity()) {
            case 3:
                holder.cardRarity.setImageResource(GIBanner.THREE_STAR);
                break;
            case 4:
                holder.cardRarity.setImageResource(GIBanner.FOUR_STAR);
                break;
            case 5:
                holder.cardRarity.setImageResource(GIBanner.FIVE_STAR);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return imageNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage, cardRarity;
        TextView cardCount, cardName;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            cardRarity = itemView.findViewById(R.id.cardRarity);
            cardName = itemView.findViewById(R.id.cardName);
            cardImage = itemView.findViewById(R.id.imageHolder);
            cardCount = itemView.findViewById(R.id.frequency);
            parentLayout = itemView.findViewById(R.id.viewHolderLayout);
        }
    }
}
