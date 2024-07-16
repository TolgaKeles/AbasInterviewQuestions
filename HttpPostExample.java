package abas;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

public class HttpPostExample {
    public static void main(String[] args) {
        try {
            // HttpClient oluştur
            HttpClient client = HttpClient.newHttpClient();
            
            // POST isteği için JSON verisi
            String json = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";
            
            // POST isteği oluştur
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://jsonplaceholder.typicode.com/posts"))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(json))
                    .build();
            
            // POST isteğini gönder ve yanıtı al
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Yanıt durum kodunu ve içeriğini yazdır
            System.out.println("Durum Kodu: " + response.statusCode());
            System.out.println("Yanıt Gövdesi: " + response.body());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
