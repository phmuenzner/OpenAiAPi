package org.muenznerp.jinaai.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JinaAiResult {
    private JinaAiError error;
    private I18N i18n;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class I18N {
        private String de;
    }
}
