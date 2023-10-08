package org.muenznerp.openai.json.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiChatRequestBody {
    private String model;
    @Singular
    private List<OpenAiMessage> messages = new ArrayList<>();
}
