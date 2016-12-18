package pl.edu.agh.config.conversion;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * Created by hector on 04.12.16.
 */
public class String2LocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String s) {
        return LocalDate.parse(s);
    }
}
