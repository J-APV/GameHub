package com.example.demo;

import com.example.demo.rest.Deal;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApiCall {

    private final WebClient webClient;
    private int storeID = 1; // Default storeID
    private int pageNumber = 0; // Default page number
    private String title = "";

    public ApiCall(WebClient.Builder builder) {
        // Update the base URL to the CheapShark API endpoint
        webClient = builder.baseUrl("https://www.cheapshark.com/api/1.0").build();
    }

    public Deal[] getDeals() {
        // Modify the URI to specify the correct API endpoint path and query parameters
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/deals")
                        .queryParam("storeID", storeID) // Use the stored storeID
                        .queryParam("pageNumber", pageNumber) // Add page number parameter
                        .queryParam("title", title) // Add page number parameter
                        .build())
                .retrieve()
                .bodyToMono(Deal[].class) // Update to match the API response class
                .block();
    }

    // Setter method to update the storeID
    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    // Setter method to update the page number
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    // Setter method to update the title
    public void setTitle(String title) {
        this.title = title;
    }

}
