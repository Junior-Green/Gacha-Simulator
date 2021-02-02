package com.example.gacha4;

public class Card {
    final int id, image, rarity;


    public Card(int cardImage, int cardID) {
        id = cardID;
        image = cardImage;
        rarity = 0;
    }

    public Card(int cardImage, int cardID, int rarity) {
        this.id = cardID;
        image = cardImage;
        this.rarity = rarity;
    }


    public int getCardImage() {
        return image;
    }

    public int getCardID() {
        return id;
    }

}
