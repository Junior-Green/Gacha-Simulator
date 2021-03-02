package com.example.gacha4;

public class Card {
    final int id, image, rarity;
    final String name;

    public Card(int cardImage, int cardID) {
        id = cardID;
        image = cardImage;
        rarity = 0;
        name = "";
    }

    public Card(int cardImage, int cardID, int rarity, String name) {
        this.id = cardID;
        image = cardImage;
        this.rarity = rarity;
        this.name = name;
    }

    public int getRarity() {
        return rarity;
    }

    public String getName() {
        return name;
    }

    public int getCardImage() {
        return image;
    }

    public int getCardID() {
        return id;
    }

}
