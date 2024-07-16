package abas;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpGetExample {
    public static void main(String[] args) {
        try {
            // HttpClient oluştur
            HttpClient client = HttpClient.newHttpClient();
            
            // GET isteği oluştur
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://jsonplaceholder.typicode.com/posts/1"))
                    .GET()
                    .build();
            
            // GET isteğini gönder ve yanıtı al
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Yanıt durum kodunu ve içeriğini yazdır
            System.out.println("Durum Kodu: " + response.statusCode());
            System.out.println("Yanıt Gövdesi: " + response.body());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
