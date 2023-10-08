package org.muenznerp.jinaai.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.extern.jackson.Jacksonized;
import org.muenznerp.openai.json.chat.OpenAiMessage;

import java.util.ArrayList;
import java.util.List;

@Data
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class JinaAiRequestBody {
    @Singular("data")
    private List<JinaAiData> data = new ArrayList();
}
