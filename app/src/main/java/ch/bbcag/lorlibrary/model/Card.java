package ch.bbcag.lorlibrary.model;

import androidx.annotation.NonNull;

import java.util.List;

public class Card {

    // Strings
    private String cardCode;
    private String name;
    private String descriptionRaw;
    private String levelupDescriptionRaw;
    private String flavorText;
    private String artistName;
    private String rarityRef;
    private String type;
    private String spellSpeed;

    // Lists
    private final List<String> keywordRefs;
    private final List<String> regionRefs;
    private List<Object> assets;

    // Integer
    private final int cost;
    private final int attack;
    private final int health;

    public Card(
            String cardCode,
            String name,
            String descriptionRaw,
            String levelupDescriptionRaw,
            String flavorText,
            String artistName,
            String spellSpeed,
            String rarityRef,
            String type,
            List<String> keywordRefs,
            List<String> regionRefs,
            List<Object> assets,
            int cost,
            int attack,
            int health) {
        this.cardCode = cardCode;
        this.name = name;
        this.descriptionRaw = descriptionRaw;
        this.levelupDescriptionRaw = levelupDescriptionRaw;
        this.flavorText = flavorText;
        this.artistName = artistName;
        this.spellSpeed = spellSpeed;
        this.rarityRef = rarityRef;
        this.type = type;
        this.keywordRefs = keywordRefs;
        this.regionRefs = regionRefs;
        this.assets = assets;
        this.cost = cost;
        this.attack = attack;
        this.health = health;
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

    public String getDescriptionRaw() {
        return descriptionRaw;
    }

    public void setDescriptionRaw(String descriptionRaw) {
        this.descriptionRaw = descriptionRaw;
    }

    public String getLevelupDescriptionRaw() {
        return levelupDescriptionRaw;
    }

    public void setLevelupDescriptionRaw(String levelupDescriptionRaw) {
        this.levelupDescriptionRaw = levelupDescriptionRaw;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setSpellSpeed(String spellSpeed) {
        this.spellSpeed = spellSpeed;
    }

    public String getRarityRef() {
        return rarityRef;
    }

    public void setRarityRef(String rarityRef) {
        this.rarityRef = rarityRef;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstRegion() {
        return regionRefs.get(0);
    }

    protected List<Object> getAssets() {
        return assets;
    }

    public void setAssets(List<Object> assets) {
        this.assets = assets;
    }

    public String getBanner() {
        String parent = String.valueOf(getAssets().get(0));
        String[] paths = parent.split(",");
        String[] banner = paths[1].split("=");
        String bannerPath = banner[1];
        bannerPath = bannerPath.substring(0, bannerPath.length() - 1);
        return bannerPath;
    }

    public String getCardImage() {
        String parent = String.valueOf(getAssets().get(0));
        String[] paths = parent.split(",");
        String[] cardImage = paths[0].split("=");
        return cardImage[1];
    }

    public String[] getAssetsString() {
        String parent = String.valueOf(getAssets().get(0));
        String[] paths = parent.split("=");
        System.out.println(parent);
        return paths[1].split(",");
    }

    @NonNull
    @Override
    public String toString() {
        return name + "\n" + type;
    }
}
