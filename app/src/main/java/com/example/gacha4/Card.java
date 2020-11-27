package com.example.gacha4;

public class Card {
    int id, image;
    String id_string;

    public Card(int cardImage, int cardID) {
        id = cardID;
        image = cardImage;
    }
    public Card(int cardImage, String cardID)
    {
        id_string = cardID;
        image = cardImage;
    }


    public int getCardImage() {
        return image;
    }

    public int getCardID() {
        return id;
    }

    public String getCardIDString() {
        return id_string;
    }

}
