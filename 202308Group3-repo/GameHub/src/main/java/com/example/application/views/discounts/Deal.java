
package com.example.application.views.discounts;

import com.example.application.data.Bookmark;
import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "internalName",
    "title",
    "metacriticLink",
    "dealID",
    "storeID",
    "gameID",
    "salePrice",
    "normalPrice",
    "isOnSale",
    "savings",
    "metacriticScore",
    "steamRatingText",
    "steamRatingPercent",
    "steamRatingCount",
    "steamAppID",
    "releaseDate",
    "lastChange",
    "dealRating",
    "thumb"
})
@Generated("jsonschema2pojo")
public class Deal{

    public Deal(String title, String thumb, String price) {
        this.title = title;
        this.thumb = thumb;
        this.normalPrice = price;
    }

    @JsonProperty("internalName")
    private String internalName;
    @JsonProperty("title")
    private String title;
    @JsonProperty("metacriticLink")
    private String metacriticLink;
    @JsonProperty("dealID")
    private String dealID;
    @JsonProperty("storeID")
    private String storeID;
    @JsonProperty("gameID")
    private String gameID;
    @JsonProperty("salePrice")
    private String salePrice;
    @JsonProperty("normalPrice")
    private String normalPrice;
    @JsonProperty("isOnSale")
    private String isOnSale;
    @JsonProperty("savings")
    private String savings;
    @JsonProperty("metacriticScore")
    private String metacriticScore;
    @JsonProperty("steamRatingText")
    private String steamRatingText;
    @JsonProperty("steamRatingPercent")
    private String steamRatingPercent;
    @JsonProperty("steamRatingCount")
    private String steamRatingCount;
    @JsonProperty("steamAppID")
    private String steamAppID;
    @JsonProperty("releaseDate")
    private Integer releaseDate;
    @JsonProperty("lastChange")
    private Integer lastChange;
    @JsonProperty("dealRating")
    private String dealRating;
    @JsonProperty("thumb")
    private String thumb;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("internalName")
    public String getInternalName() {
        return internalName;
    }

    @JsonProperty("internalName")
    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("metacriticLink")
    public String getMetacriticLink() {
        return metacriticLink;
    }

    @JsonProperty("metacriticLink")
    public void setMetacriticLink(String metacriticLink) {
        this.metacriticLink = metacriticLink;
    }

    @JsonProperty("dealID")
    public String getDealID() {
        return dealID;
    }

    @JsonProperty("dealID")
    public void setDealID(String dealID) {
        this.dealID = dealID;
    }

    @JsonProperty("storeID")
    public String getStoreID() {
        return storeID;
    }

    @JsonProperty("storeID")
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    @JsonProperty("gameID")
    public String getGameID() {
        return gameID;
    }

    @JsonProperty("gameID")
    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    @JsonProperty("salePrice")
    public String getSalePrice() {
        return salePrice;
    }

    @JsonProperty("salePrice")
    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    @JsonProperty("normalPrice")
    public String getNormalPrice() {
        return normalPrice;
    }

    @JsonProperty("normalPrice")
    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    @JsonProperty("isOnSale")
    public String getIsOnSale() {
        return isOnSale;
    }

    @JsonProperty("isOnSale")
    public void setIsOnSale(String isOnSale) {
        this.isOnSale = isOnSale;
    }

    @JsonProperty("savings")
    public String getSavings() {
        return savings;
    }

    @JsonProperty("savings")
    public void setSavings(String savings) {
        this.savings = savings;
    }

    @JsonProperty("metacriticScore")
    public String getMetacriticScore() {
        return metacriticScore;
    }

    @JsonProperty("metacriticScore")
    public void setMetacriticScore(String metacriticScore) {
        this.metacriticScore = metacriticScore;
    }

    @JsonProperty("steamRatingText")
    public String getSteamRatingText() {
        return steamRatingText;
    }

    @JsonProperty("steamRatingText")
    public void setSteamRatingText(String steamRatingText) {
        this.steamRatingText = steamRatingText;
    }

    @JsonProperty("steamRatingPercent")
    public String getSteamRatingPercent() {
        return steamRatingPercent;
    }

    @JsonProperty("steamRatingPercent")
    public void setSteamRatingPercent(String steamRatingPercent) {
        this.steamRatingPercent = steamRatingPercent;
    }

    @JsonProperty("steamRatingCount")
    public String getSteamRatingCount() {
        return steamRatingCount;
    }

    @JsonProperty("steamRatingCount")
    public void setSteamRatingCount(String steamRatingCount) {
        this.steamRatingCount = steamRatingCount;
    }

    @JsonProperty("steamAppID")
    public String getSteamAppID() {
        return steamAppID;
    }

    @JsonProperty("steamAppID")
    public void setSteamAppID(String steamAppID) {
        this.steamAppID = steamAppID;
    }

    @JsonProperty("releaseDate")
    public Integer getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("releaseDate")
    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonProperty("lastChange")
    public Integer getLastChange() {
        return lastChange;
    }

    @JsonProperty("lastChange")
    public void setLastChange(Integer lastChange) {
        this.lastChange = lastChange;
    }

    @JsonProperty("dealRating")
    public String getDealRating() {
        return dealRating;
    }

    @JsonProperty("dealRating")
    public void setDealRating(String dealRating) {
        this.dealRating = dealRating;
    }

    @JsonProperty("thumb")
    public String getThumb() {
        return thumb;
    }

    @JsonProperty("thumb")
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getLink(){
        return ("https://www.cheapshark.com/redirect?dealID=" + this.dealID);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}