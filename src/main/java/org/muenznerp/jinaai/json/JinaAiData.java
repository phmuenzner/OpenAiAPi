package org.muenznerp.jinaai.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JinaAiData {
    private String image;
    private String features[] = {"de"};
    private String languages[] = {"de"};

    public void setImageAsUrl(URL url) {
        image = url.toString();
    }
    public void setImageAsPngImageFromInputStream(InputStream inputStream) throws IOException {
        //write an util class if this is too much business logic in an object class
        image = "data:image/png;base64," + new String(Base64.getEncoder().encode(inputStream.readAllBytes()));
    }

    public static JinaAiData createWithImageAsUrl(URL url) throws IOException {
        JinaAiData instance = new JinaAiData();
        instance.setImageAsUrl(url);
        return instance;
    }

    public static JinaAiData createWithImageAsPngImageFromInputStream(InputStream inputStream) throws IOException {
        JinaAiData instance = new JinaAiData();
        instance.setImageAsPngImageFromInputStream(inputStream);
        return instance;
    }

}
