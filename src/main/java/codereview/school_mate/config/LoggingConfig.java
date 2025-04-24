package codereview.school_mate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();

        // Демонстрация query-строки
        filter.setIncludeQueryString(true);

        // Чтение тела запроса
        filter.setIncludePayload(true);

        //Лимитирование размера, до 10k символов
        filter.setMaxPayloadLength(10000);

        // Логирование заголовков
        filter.setIncludeHeaders(true);

        // Добавление префикса
        filter.setAfterMessagePrefix("REQUEST DATA : ");

        return filter;
    }
}
