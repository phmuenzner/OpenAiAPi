package org.muenznerp.openai.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiError {
    private String message;
    private String type;
    private String param;
    private String code;
}
