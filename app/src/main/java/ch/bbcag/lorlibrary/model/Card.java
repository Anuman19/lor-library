package ch.bbcag.lorlibrary.model;

public class Card {

    private String cardCode;

    private String name;

    private int cost;

    public Card(String id, String name, int cost){
        this.cardCode = id;
        this.name = name;
        this.cost = cost;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardCode='" + cardCode + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
