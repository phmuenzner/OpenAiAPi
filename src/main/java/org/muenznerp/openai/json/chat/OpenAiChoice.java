package org.muenznerp.openai.json.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiChoice {
    private int index;
    private OpenAiMessage message;
    private String finish_reason;
}
