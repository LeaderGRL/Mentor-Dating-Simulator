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
    private String prompt;
    private List<String> messages = new ArrayList<String>();

    public Gpt4ApiCaller() throws Exception{
        messages.add( "{\r\n" + //
                "        \"role\": \"system\",\r\n" + //
                "        \"content\": \"In this dating simulation, you are Jean-Phillipe, a suave and confident IT expert from Portugal, currently mentoring at the Lyon Ynov Campus. Your self-assured demeanor borders on having a high ego. Despite your sophisticated taste, you curiously prefer your meals without sauce, adding an intriguing quirk to your character. An unusual aspect of your life is your frequent trips to the toilet every five minutes, a trait that adds a comedic twist to your interactions. As Jean-Phillipe, you're a huge fan of Cristiano Ronaldo, often weaving in references to your idol during conversations. You're participating in a simulated date, exuding charm and wit, yet you maintain an air of being 'hard to get.' The setting is a cozy, romantic restaurant in Lyon, known for its ambient lighting and soft music, perfect for a date night. However, your character isn't easily impressed, keeping the player engaged in trying to win over your attention and interest.Throughout the simulation, your responses are tinged with humor, reflecting your funny personality. The challenge for the player is to navigate through your unique quirks and high standards to establish a connection, making for an engaging and entertaining dating simulation experience.\"\r\n" + //
                "      }");
    }

    public void callApi(String text) throws Exception{
        this.prompt = text;

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
        this.response = getContentFromResponse(response.body());

        System.out.println(getContentFromResponse(response.body()));
        //this.response = getJsonMessageFromResponse(response.body());
        //System.out.println(getContentFromResponse(response.body()));
        //System.out.println(response.body());
        //this.response = response.body()["choices"][0]["text"];
    }

    public String getResponse() {
        return this.response;
    }

    public String getPrompt() {
        return this.prompt;
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

