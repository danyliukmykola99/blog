package danyliuk.mykola.config;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

/**
 * @author Mykola Danyliuk
 */
@Configuration
public class RestConfig {

  @Bean
  public MultipartFilter multipartFilter(){

    MultipartFilter multipartFilter = new MultipartFilter();
    multipartFilter.setMultipartResolverBeanName("multipartResolver");
    return multipartFilter;
  }

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Bean
  public LocaleResolver localeResolver() {
    return new FixedLocaleResolver(new Locale("uk_UA"));
  }

}
