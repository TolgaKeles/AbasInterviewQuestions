package abas;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

public class HttpExample {

    public static void main(String[] args) {
        try {
            // Perform the POST request
            String postResponse = sendPostRequest();
            System.out.println("POST Response: " + postResponse);
            
            // Perform the GET request (assuming the created post has an ID of 101)
            String getResponse = sendGetRequest("101");
            System.out.println("GET Response: " + getResponse);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to perform POST request
    public static String sendPostRequest() throws Exception {
        // Create an HttpClient instance
        HttpClient client = HttpClient.newHttpClient();

        // JSON data to send with POST request
        String json = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        // Build the POST request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/posts"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();

        // Send the POST request and receive the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the status code and response body
        System.out.println("POST Status Code: " + response.statusCode());
        return response.body();
    }

    // Method to perform GET request
    public static String sendGetRequest(String postId) throws Exception {
        // Create an HttpClient instance
        HttpClient client = HttpClient.newHttpClient();

        // Build the GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/posts/" + postId))
                .GET()
                .build();

        // Send the GET request and receive the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the status code and response body
        System.out.println("GET Status Code: " + response.statusCode());
        return response.body();
    }
}
