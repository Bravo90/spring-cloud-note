package sun.study.note.config;

        import feign.Logger;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

/**
 * FeignLogConfiguration:
 *
 * @author: sunzhen
 * Date: 2021-03-31
 */
@Configuration
public class FeignLogConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
