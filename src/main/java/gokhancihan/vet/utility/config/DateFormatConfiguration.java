package gokhancihan.vet.utility.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.FormattingConversionService;

import java.time.format.DateTimeFormatter;

@Configuration(proxyBeanMethods = false)
public class DateFormatConfiguration {

    private final DateTimeFormatterRegistrar registrar =
            new DateTimeFormatterRegistrar();

    @Autowired
    public void configure(final FormattingConversionService conversionService) {
        registrar.registerFormatters(conversionService);
    }

    public DateFormatConfiguration() {
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    }

}
