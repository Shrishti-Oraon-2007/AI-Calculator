package src;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

public class pro2 {

    public static void ask(String question) {
        
        // It's generally better practice to load API keys from environment variables
        // or a configuration file rather than hardcoding them directly in the source.
        // For this example, we'll keep it as is.
        String apiKey = "your api key"; 
        
        String requestBody = """
        {
          "model": "google/gemma-3n-e2b-it:free",
          "messages": [
            {
              "role": "user",
              "content": "You are a strict math tutor. Only answer math-related questions (like algebra, geometry, calculus, etc.). If my question is not related to math, simply reply: 'It is beyond my scope! Response should be in 3-4 lines'.\n\nHere is the question: %s"
            }
          ]
        }
        """.formatted(question);

        try {
            // Make the API request
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://openrouter.ai/api/v1/chat/completions"))
                    .timeout(Duration.ofSeconds(30))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Extract and print response
            JSONObject json = new JSONObject(response.body());
            String content = json
            .getJSONArray("choices")
            .getJSONObject(0)
            .getJSONObject("message")
            .getString("content");
            
            System.out.println("******************************");
            System.out.println("AI says:");
            System.out.println("******************************");
            System.out.println(content.trim());
            System.out.println("******************************");

        } catch (Exception e) {
            System.out.println("\nError while communicating with API:");
            e.printStackTrace();
        }
    }
}


