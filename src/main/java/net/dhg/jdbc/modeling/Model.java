package net.dhg.jdbc.modeling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model {
    private String myName;
}
