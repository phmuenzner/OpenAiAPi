package org.muenznerp.openai.json.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.muenznerp.openai.json.OpenAiError;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiImageResponseBody {
    private long created;
    private List<OpenAiImageUrl> data;
    private OpenAiError error;
}
