package org.muenznerp.jinaai;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.muenznerp.jinaai.json.JinaAiData;
import org.muenznerp.jinaai.json.JinaAiRequestBody;
import org.muenznerp.jinaai.json.JinaAiResponseBody;

import java.io.InputStream;
import java.net.URI;

public class JinaAiClient {
    private final HttpClient httpClient;
    private final URI endpoint;
    private final String apiKey;
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public JinaAiClient(final URI endpoint, final String apiKey) {
        httpClient = HttpClients.createDefault();
        this.endpoint = endpoint;
        this.apiKey = apiKey;
    }
    @SneakyThrows
    public String requestImageDescription(InputStream pngImageInputStream) {
        HttpPost request = new HttpPost(endpoint);
        request.addHeader("x-api-key", "token " + apiKey);
        request.addHeader("Content-Type", "application/json");

        //baue JinaAI-Anfrage Objekt für Json-Request
        JinaAiRequestBody requestContent = JinaAiRequestBody.builder()
                .data(JinaAiData.createWithImageAsPngImageFromInputStream(pngImageInputStream))
                .build();

        String requestString = objectMapper.writeValueAsString(requestContent);
        System.out.println("Request: " + requestString);

        //Sende Anfrage an JinaAI-Endpunkt
        request.setEntity(new StringEntity(requestString));
        HttpResponse response = httpClient.execute(request);

        //Ergebnis auswerten
        String responseString = EntityUtils.toString(response.getEntity());
        System.out.println("Response: " + responseString);

        JinaAiResponseBody responseBody = objectMapper.readValue(responseString, JinaAiResponseBody.class);

        //provisorische Fehlerbehandlung
        if (responseBody.getError()!=null) {
            throw new Exception("JinaAI Error: " + responseBody.getError().getMessage() + ", Status: " + responseBody.getError().getStatus() + ", Response: " + responseString);
        }
        if (responseBody.getResult().get(0).getError()!=null) {
            throw new Exception("JinaAI Error: " + responseBody.getResult().get(0).getError().getMessage() + ", Status: " + responseBody.getResult().get(0).getError().getStatus() + ", Response: " + responseString);
        }

        return responseBody.getResult().get(0).getI18n().getDe();
    }

    public static void main(String[] args) throws Exception {
        String apiKey = "DaDpSyEIc3G2i9UJ7Byk:659e99e5f39406871610b36fd05e28ccb929031eeb5a6dab16deaf3ef67b732d"; // JinaAI API Key
        String endpoint = "https://api.scenex.jina.ai/v1/describe"; // JinaAI/SceneXplain API Endpunkt

        JinaAiClient jina = new JinaAiClient(URI.create(endpoint), apiKey);
        String result = jina.requestImageDescription(JinaAiClient.class.getClassLoader().getResourceAsStream("test-image.png"));

        System.out.println("Result: " + result);
    }
}
