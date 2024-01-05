package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Gpt4ApiCaller {

    public Gpt4ApiCaller() throws Exception{
        // TODO implement here
    }

    public void callApi(String text) throws Exception{
        HttpClient client = HttpClient.newHttpClient();

        String json = "{\r\n" + //
                "    \"model\": \"gpt-3.5-turbo\",\r\n" + //
                "    \"messages\": [\r\n" + //
                "      {\r\n" + //
                "        \"role\": \"system\",\r\n" + //
                "        \"content\": \"You are a helpful assistant.\"\r\n" + //
                "      },\r\n" + //
                "      {\r\n" + //
                "        \"role\": \"user\",\r\n" + //
                "        \"content\": \"" + text.trim() + "\"\r\n" + //
                "      }\r\n" + //
                "    ]\r\n" + //
                "  }";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://api.openai.com/v1/chat/completions"))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"))
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
    
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}

