package org.muenznerp.jinaai.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.muenznerp.openai.json.OpenAiError;
import org.muenznerp.openai.json.chat.OpenAiChoice;
import org.muenznerp.openai.json.chat.OpenAiUsage;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JinaAiResponseBody {
    private String code;
    private String status;
    private List<JinaAiResult> result;
    private JinaAiError error;
}
