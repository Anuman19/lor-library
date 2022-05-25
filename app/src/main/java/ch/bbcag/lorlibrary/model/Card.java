package ch.bbcag.lorlibrary.model;

import java.util.ArrayList;
import java.util.Arrays;

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
  private ArrayList<String> keywordRefs;
  private ArrayList<String> regionRefs;
  private ArrayList<Object> assets;

  // Integer
  private int cost;
  private int attack;
  private int health;

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
      ArrayList<String> keywordRefs,
      ArrayList<String> regionRefs,
      ArrayList<Object> assets,
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

  public String getSpellSpeed() {
    return spellSpeed;
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

  public ArrayList<String> getKeywordRefs() {
    return keywordRefs;
  }

  public void setKeywordRefs(ArrayList<String> keywordRefs) {
    this.keywordRefs = keywordRefs;
  }

  public ArrayList<String> getRegionRefs() {
    return regionRefs;
  }

  public void setRegionRefs(ArrayList<String> regionRefs) {
    this.regionRefs = regionRefs;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public int getAttack() {
    return attack;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  protected ArrayList<Object> getAssets() {
    return assets;
  }

  public void setAssets(ArrayList<Object> assets) {
    this.assets = assets;
  }

  public String[] getAssetsString() {
    String parent = String.valueOf(getAssets().get(0));
    String[] paths = parent.split("=");
    return paths[1].split(",");
  }

  @Override
  public String toString() {
    return name + '\n' + descriptionRaw + '\n' + '\n' + type + artistName;
  }
}
