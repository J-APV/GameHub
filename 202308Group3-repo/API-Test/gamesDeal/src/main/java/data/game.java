package data;

public class game {
	private String internalName;
	private String title;
	private String metacriticLink;
	private String dealID;
	private String storeID;
	private String gameID;
	private String salePrice;
	private String normalPrice;
	private String isOnSale;
	private String savings;
	private String metacriticScore;
	private String steamRatingText;
	private String steamRatingPercent;
	private String steamRatingCount;
	private String steamAppID;
	private String releaseDate;
	private String lastChange;
	private String dealRating;
	private String thumb;
	
	public game(String title, String internalName, String metacriticLink, String dealID, String storeID, String gameID, String salePrice, String normalPrice, String isOnSale, String savings, String metacriticScore,
			String steamRatingText, String steamRatingPercent, String steamRatingCount, String steamAppID, String releaseDate, String lastChange, String dealRating, String thumb) {
		this.title = title;
		this.internalName = internalName;
		this.metacriticLink = metacriticLink;
		this.dealID = dealID;
		this.storeID = storeID;
		this.gameID = gameID;
		this.salePrice = salePrice;
		this.normalPrice = normalPrice;
		this.isOnSale = isOnSale;
		this.savings = savings;
		this.metacriticScore = metacriticScore;
		this.steamRatingText = steamRatingText;
		this.steamRatingPercent = steamRatingPercent;
		this.steamRatingCount = steamRatingCount;
		this.steamAppID = steamAppID;
		this.releaseDate = releaseDate;
		this.lastChange = lastChange;
		this.dealRating = dealRating;
		this.thumb = thumb;
		
	}
	public String getInternalName() {
		return internalName;
	}
	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMetacriticLink() {
		return metacriticLink;
	}
	public void setMetacriticLink(String metacriticLink) {
		this.metacriticLink = metacriticLink;
	}
	public String getDealID() {
		return dealID;
	}
	public void setDealID(String dealID) {
		this.dealID = dealID;
	}
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	public String getGameID() {
		return gameID;
	}
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	public String getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}
	public String getSavings() {
		return savings;
	}
	public void setSavings(String savings) {
		this.savings = savings;
	}
	public String getMetacriticScore() {
		return metacriticScore;
	}
	public void setMetacriticScore(String metacriticScore) {
		this.metacriticScore = metacriticScore;
	}
	public String getIsOnSale() {
		return isOnSale;
	}
	public void setIsOnSale(String isOnSale) {
		this.isOnSale = isOnSale;
	}
	public String getSteamRatingText() {
		return steamRatingText;
	}
	public void setSteamRatingText(String steamRatingText) {
		this.steamRatingText = steamRatingText;
	}
	public String getSteamRatingPercent() {
		return steamRatingPercent;
	}
	public void setSteamRatingPercent(String steamRatingPercent) {
		this.steamRatingPercent = steamRatingPercent;
	}
	public String getSteamRatingCount() {
		return steamRatingCount;
	}
	public void setSteamRatingCount(String steamRatingCount) {
		this.steamRatingCount = steamRatingCount;
	}
	public String getSteamAppID() {
		return steamAppID;
	}
	public void setSteamAppID(String steamAppID) {
		this.steamAppID = steamAppID;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getDealRating() {
		return dealRating;
	}
	public void setDealRating(String dealRating) {
		this.dealRating = dealRating;
	}
	public String getLastChange() {
		return lastChange;
	}
	public void setLastChange(String lastChange) {
		this.lastChange = lastChange;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	@Override
	public String toString() {
		String result = "";
		result += ("Game: " + this.title + "\n");
		result += ("Normal Price: " + this.normalPrice + "\n");
		result += ("Discount price: " + this.salePrice + "\n");
		result += ("Link: " + this.metacriticLink + "\n");
		return result;
	}

}
