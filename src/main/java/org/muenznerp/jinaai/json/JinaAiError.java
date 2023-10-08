package org.muenznerp.jinaai.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JinaAiError {
    private String message;
    private String status;
}
