package pl.edu.agh.config.conversion;

import org.springframework.core.convert.converter.Converter;

import java.time.Year;

/**
 * Created by hector on 17.12.16.
 */
public class Double2YearConverter implements Converter<Double, Year> {
    @Override
    public Year convert(Double aDouble) {
        return Year.of(aDouble.intValue());
    }
}
