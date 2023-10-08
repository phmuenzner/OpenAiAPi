package org.muenznerp.openai;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.muenznerp.openai.json.chat.OpenAiMessage;
import org.muenznerp.openai.json.chat.OpenAiChatRequestBody;
import org.muenznerp.openai.json.chat.OpenAiChatResponseBody;
import org.muenznerp.openai.json.image.OpenAiImageRequestBody;
import org.muenznerp.openai.json.image.OpenAiImageResponseBody;

import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OpenAiClient {
    private final HttpClient httpClient;
    private final URI endpoint;
    private final String apiKey;
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public OpenAiClient(final URI endpoint, final String apiKey) {
        httpClient = HttpClients.createDefault();
        this.endpoint = endpoint;
        this.apiKey = apiKey;
    }
    @SneakyThrows
    public String requestCompletion(String content) {
        HttpPost request = new HttpPost(endpoint.resolve("chat/completions"));
        request.addHeader("Authorization", "Bearer " + apiKey);
        request.addHeader("Content-Type", "application/json");

        //baue OpenAI-Anfrage Objekt für Json-Request
        OpenAiChatRequestBody requestContent = OpenAiChatRequestBody.builder()
                .model("gpt-3.5-turbo")
                .message(new OpenAiMessage("user", content))
                .build();

        String requestString = objectMapper.writeValueAsString(requestContent);
        System.out.println("Request: " + requestString);

        //Sende Anfrage an OpenAI-Endpunkt für /chat/completion
        request.setEntity(new StringEntity(requestString, StandardCharsets.UTF_8));
        HttpResponse response = httpClient.execute(request);

        //Ergebnis auswerten
        String responseString = EntityUtils.toString(response.getEntity());
        System.out.println("Response: " + responseString);

        OpenAiChatResponseBody responseBody = objectMapper.readValue(responseString, OpenAiChatResponseBody.class);

        //provisorische Fehlerbehandlung
        if (responseBody.getError()!=null) {
            throw new Exception("OpenAI Error: " + responseBody.getError().getMessage() + ", Response: " + responseString);
        }

        return responseBody.getChoices().get(0).getMessage().getContent();
    }

    @SneakyThrows
    public URL requestImage(String prompt) {
        HttpPost request = new HttpPost(endpoint.resolve("images/generations"));
        request.addHeader("Authorization", "Bearer " + apiKey);
        request.addHeader("Content-Type", "application/json");

        //baue OpenAI-Anfrage Objekt für Json-Request
        OpenAiImageRequestBody requestContent = OpenAiImageRequestBody.builder()
                .prompt(prompt)
                .build();

        String requestString = objectMapper.writeValueAsString(requestContent);
        System.out.println("Request: " + requestString);

        //Sende Anfrage an OpenAI-Endpunkt für /images/generations
        request.setEntity(new StringEntity(requestString, StandardCharsets.UTF_8));
        HttpResponse response = httpClient.execute(request);

        //Ergebnis auswerten
        String responseString = EntityUtils.toString(response.getEntity());
        System.out.println("Response: " + responseString);

        OpenAiImageResponseBody responseBody = objectMapper.readValue(responseString, OpenAiImageResponseBody.class);

        //provisorische Fehlerbehandlung
        if (responseBody.getError()!=null) {
            throw new Exception("OpenAI Error: " + responseBody.getError().getMessage() + ", Response: " + responseString);
        }

        return responseBody.getData().get(0).getUrl();
    }


    public static void main(String[] args) throws Exception {
        String apiKey = "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"; // OpenAI API key
        String endpoint = "https://api.openai.com/v1/"; // OpenAI API Endpunkt

        OpenAiClient openApi = new OpenAiClient(URI.create(endpoint), apiKey);

        //String result = openApi.requestCompletion("Wieviele Hörner hat ein Einhorn?");

        URL result = openApi.requestImage("Ein Pinguin schwimmt in einer Kaffeetasse.");
        System.out.println("Result: " + result);
    }
}
