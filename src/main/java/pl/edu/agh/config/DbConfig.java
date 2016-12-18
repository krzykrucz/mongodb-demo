package pl.edu.agh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import pl.edu.agh.config.conversion.Double2YearConverter;
import pl.edu.agh.config.conversion.String2LocalDateConverter;
import pl.edu.agh.config.conversion.String2MoneyConverter;

import java.util.Arrays;

/**
 * Created by hector on 04.12.16.
 */

@Configuration
public class DbConfig {

    @Bean
    public CustomConversions customConversions() {
        return new CustomConversions(Arrays.asList(
                new String2LocalDateConverter(),
                new String2MoneyConverter(),
                new Double2YearConverter()
        ));

    }
}
