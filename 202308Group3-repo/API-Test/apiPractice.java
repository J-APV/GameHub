package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class apiPractice
 */
@WebServlet({"/apiPractice", "/games_data/?app_id=1593500"})
public class apiPractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public apiPractice() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpRequest request1 = HttpRequest.newBuilder()
				.uri(URI.create("https://steamgames-special-offers.p.rapidapi.com/games_list/?start=0&count=100&region=US"))
				.header("X-RapidAPI-Key", "ff00f10c4emsh96f684c61a057bfp131eabjsnaca5296146b0")
				.header("X-RapidAPI-Host", "steamgames-special-offers.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response1 = null;
		try {
			response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] res = response1.body().split("[,]", 0);
		
		res[0] = "1675200";
		
		res[60] = "1675200";
		
		for(String myStr: res) {
			try {
				Thread.sleep(1001);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(myStr.equals("1432860]") || myStr.equals("\"possible_has_more\":true") || myStr.equals("\"total_games\":9063}")) {
				 continue;
			 }
			 HttpRequest request2 = HttpRequest.newBuilder()
						.uri(URI.create("https://steamgames-special-offers.p.rapidapi.com/games_data/?app_id=" + myStr))
						.header("X-RapidAPI-Key", "ff00f10c4emsh96f684c61a057bfp131eabjsnaca5296146b0")
						.header("X-RapidAPI-Host", "steamgames-special-offers.p.rapidapi.com")
						.method("GET", HttpRequest.BodyPublishers.noBody())
						.build();
	          
			 HttpResponse<String> response2 = null;
			try {
				response2 = HttpClient.newHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 response.getWriter().print(response2.body());
	       }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
