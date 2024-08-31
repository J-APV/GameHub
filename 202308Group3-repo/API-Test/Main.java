
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class Main {
    public static void main(String args[]) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://epic-store-games.p.rapidapi.com/onSale?searchWords=Mount&locale=us&country=us"))
                .header("X-RapidAPI-Key", "YOUR TOKEN GOES HERE")
                .header("X-RapidAPI-Host", "epic-store-games.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build(); 

        //creates http client and a send request all in one
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        parse(response.body()); // parse method being called
    }

	
    // parsing method to get each object from JSON array
    public static String parse(String responseBody){
        int count = 0;
        JSONArray games = new JSONArray(responseBody); // input for parse
        for(int i = 0; i < games.length(); i++){ // loop through the json array that api outputs normally and set variable for each key/index
            JSONObject game = games.getJSONObject(i);
            count += + 1;
            String title =  game.getString("title"); // now the variable title contains what was in index/key "title"
           // String price = game.getString( "price");
            String date = game.getString("effectiveDate");
            String url = game.getString("url");
            System.out.println(title + " |date: " + date + " URL: " + url);

        }
        System.out.println("Number of games: " + count);


        return null;
    }
}
