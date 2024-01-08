package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


public class Gpt4ApiCaller {

    private String response;
    private List<String> messages = new ArrayList<String>();

    public Gpt4ApiCaller() throws Exception{
        messages.add( "{\r\n" + //
                "        \"role\": \"system\",\r\n" + //
                "        \"content\": \"You are a Jean-Phillipe, a mentor at Lyon Ynov Campus who is an IT expert. You have a very high ego. You ate your food without sauce. You must go to the toilet every 5 minutes. You are funny. Your from portugal. You are fan of Cristiano Ronaldo. You should act like if you are in a date but your not an easy guy\"\r\n" + //
                "      }\r\n" );
    }

    public void callApi(String text) throws Exception{
        HttpClient client = HttpClient.newHttpClient();

        messages.add("{ \"role\": \"user\", \"content\": \"" + text.trim() + "\" }");

        String json = "{\r\n" + //
                "    \"model\": \"gpt-3.5-turbo\",\r\n" + //
                "    \"messages\": " + messages.toString() + "\r\n" + //
                "  }";

        System.out.println(json);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://api.openai.com/v1/chat/completions"))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"))
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
    
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        messages.add(getJsonMessageFromResponse(response.body()));

        System.out.println(getContentFromResponse(response.body()));
        //this.response = getJsonMessageFromResponse(response.body());
        //System.out.println(getContentFromResponse(response.body()));
        //System.out.println(response.body());
        //this.response = response.body()["choices"][0]["text"];
    }

    public String getResponse() {
        return this.response;
    }

    public String getContentFromResponse(String jsonResponse) {
        Gson gson = new Gson();
        GptResponse response = gson.fromJson(jsonResponse, GptResponse.class);
    
        if (response != null && response.getChoices() != null && response.getChoices().length > 0) {
            Message message = response.getChoices()[0].getMessage();
            if (message != null) {
                return message.getContent();
            }
        }
        return null; // or handle this case as per your logic
    }

    // public Message getMessageFromResponse(String jsonResponse) {
    //     Gson gson = new Gson();
    //     GptResponse response = gson.fromJson(jsonResponse, GptResponse.class);
    
    //     if (response != null && response.getChoices() != null && response.getChoices().length > 0) {
    //         Message message = response.getChoices()[0].getMessage();
    //         if (message != null) {
    //             return message;
    //         }
    //     }
    //     return null; // or handle this case as per your logic
    // }

    public String getJsonMessageFromResponse(String jsonResponse) {
        Gson gson = new Gson();
        GptResponse response = gson.fromJson(jsonResponse, GptResponse.class);
    
        if (response != null && response.getChoices() != null && response.getChoices().length > 0) {
            Message message = response.getChoices()[0].getMessage();
            if (message != null) {
                return gson.toJson(message);
            }
        }
        return null; // or handle this case as per your logic
    }
}

