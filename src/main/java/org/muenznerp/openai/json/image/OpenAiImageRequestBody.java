package org.muenznerp.openai.json.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiImageRequestBody {
    private String prompt;
    @Builder.Default
    private int n = 1;
    @Builder.Default
    private String size = "1024x1024"; //TODO: schön machen
}
