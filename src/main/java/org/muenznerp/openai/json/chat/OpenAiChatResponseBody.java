package org.muenznerp.openai.json.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.muenznerp.openai.json.OpenAiError;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiChatResponseBody {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<OpenAiChoice> choices;
    private OpenAiUsage usage;
    private OpenAiError error;
}
