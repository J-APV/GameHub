package data;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;




/**
 * Servlet implementation class games
 */
@WebServlet("/games")
public class games extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public games() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var out = response.getWriter();
		HttpRequest request1 = HttpRequest.newBuilder()
				.uri(URI.create("https://cheapshark-game-deals.p.rapidapi.com/deals?lowerPrice=0&steamRating=0&desc=0&output=json&steamworks=0&sortBy=Deal%20Rating&AAA=0&pageSize=60&exact=0&upperPrice=50&pageNumber=0&onSale=0&metacritic=0&storeID%5B0%5D=1%2C2%2C3"))
				.header("X-RapidAPI-Key", "ff00f10c4emsh96f684c61a057bfp131eabjsnaca5296146b0")
				.header("X-RapidAPI-Host", "cheapshark-game-deals.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response1 = null;
		try {
			response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		Gson gson = new Gson();
		ArrayList<game> list = new ArrayList<>();
		String jsonData = response1.body();
		
		
		JsonArray jsonArray = new JsonParser()
				.parse(jsonData)
				.getAsJsonArray();
		
		for (int i = 0; i < jsonArray.size(); i++) {
			game games = gson.fromJson(jsonArray.get(i), game.class);
			
			game adding = new game(games.getTitle(), games.getInternalName(), games.getMetacriticLink(), 
					games.getDealID(), games.getStoreID(), games.getGameID(), games.getSalePrice(), games.getNormalPrice(), 
					games.getIsOnSale(), games.getSavings(), games.getMetacriticScore(), games.getSteamRatingText(), games.getSteamRatingPercent(), 
					games.getSteamRatingCount(), games.getSteamAppID(), games.getReleaseDate(), games.getLastChange(), games.getDealRating(), games.getThumb());
			
			list.add(adding);
		}
		
		for(game games: list) {
			out.println(games.toString());
		}





}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
}

}
