package ebolasafe.security;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class BasicSecureSimpleClientHttpRequestFactory extends SimpleClientHttpRequestFactory {
    @Autowired
    Credentials credentials;

    public BasicSecureSimpleClientHttpRequestFactory() {
    }

    @Override
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        ClientHttpRequest result = super.createRequest(uri, httpMethod);
        System.out.println(uri);
        System.out.println(uri.getAuthority());
        System.out.println(credentials.getCredentials());

        for (Map<String, String> map: credentials.getCredentials().values()) {
            String authority = map.get("authority");
            if (authority != null && authority.equals(uri.getAuthority())) {
                result.getHeaders().add("Authorization", map.get("authorization"));
                break;
            }
        }

        if (credentials.getCredentials().containsKey(uri.getAuthority())) {
        }
        return result;
    }


@Bean
public ClientHttpRequestFactory requestFactory() {
    return new BasicSecureSimpleClientHttpRequestFactory();
}

@Bean
@ConfigurationProperties(locations="classpath:META-INF/integration/credentials.yml")
public Credentials getCredentials() {
    return new Credentials();
}

public static class Credentials {
    private Map<String, Map<String, String>> credentials = new HashMap<>();
    public Map<String, Map<String, String>> getCredentials() {
        return this.credentials;
    }
}
}