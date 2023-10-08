package org.muenznerp.openai.json.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiUsage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}
