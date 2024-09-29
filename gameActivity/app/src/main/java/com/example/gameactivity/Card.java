package com.example.gameactivity;

public class Card {
    private int id;
    private int frontImage; // Resource ID của hình ảnh mặt trước
    private boolean isFlipped;
    private boolean isMatched;

    public Card(int id, int frontImage) {
        this.id = id;
        this.frontImage = frontImage;
        this.isFlipped = false;
        this.isMatched = false;
    }

    public int getId() {
        return id;
    }

    public int getFrontImage() {
        return frontImage;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}
