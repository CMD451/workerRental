package indie.outsource.WorkerRental;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:key.properties")
public class SecretConfig {
}
