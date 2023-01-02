package ebolasafe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class EbolaSafeApplication {
	@Autowired
	private WebMvcProperties mvcProperties = new WebMvcProperties();

	class CustomResponseErrorHandler implements ResponseErrorHandler {
		private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

		@Override
		public boolean hasError(ClientHttpResponse response) throws IOException {
			return errorHandler.hasError(response);
		}

		@Override
		public void handleError(ClientHttpResponse response) throws IOException {
		}
	}
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate _restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		_restTemplate.setMessageConverters(messageConverters);
		_restTemplate.setErrorHandler(new CustomResponseErrorHandler());

		return _restTemplate;
	}

    public static void main(String[] args) {
        SpringApplication.run(EbolaSafeApplication.class, args);
    }
}
