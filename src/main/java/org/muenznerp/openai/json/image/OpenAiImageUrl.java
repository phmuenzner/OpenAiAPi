package org.muenznerp.openai.json.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.net.URL;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiImageUrl {
    private URL url;
}
