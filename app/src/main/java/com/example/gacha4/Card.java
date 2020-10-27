package com.example.gacha4;

public class Card {
    int id, image;
    String name;

    public Card(int cardImage, int cardID) {
        id = cardID;
        image = cardImage;
    }

    public Card(int cardImage, int cardID, String name) {
        id = cardID;
        image = cardImage;
        this.name = name;
    }

    public int getCardImage() {
        return image;
    }

    public int getCardID() {
        return id;
    }

}
