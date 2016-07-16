package br.com.gsw.slack.sonar.notifier.web.client;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.apache.commons.lang3.StringUtils;

public class FeignFactory {
    private FeignFactory() {
        // private constructor
    }

    public static <T> T build(final String url, Class<T> feignClass) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException(String.format("It was not possible to create a Feign rest client for class %s, because the URL is null", feignClass.getSimpleName()));
        }
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(feignClass, url);
    }

    public static <T> T build(final String url, final String user, final String password, Class<T> feignClass) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException(String.format("It was not possible to create a Feign rest client for class %s, because the URL is null", feignClass.getSimpleName()));
        }
        final Feign.Builder builder = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder());

        if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(password)) {
            builder.requestInterceptor(new BasicAuthRequestInterceptor(user, password));
        }
        return builder.target(feignClass, url);
    }
}