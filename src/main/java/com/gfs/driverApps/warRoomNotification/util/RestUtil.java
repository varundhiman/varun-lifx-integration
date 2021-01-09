package com.gfs.driverApps.warRoomNotification.util;

import static java.util.UUID.randomUUID;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Recover;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RestUtil<T> {

    public static final String DEFAULT_AUTH_MODE = "Basic ";
    public static final String BEARER_AUTH_MODE = "Bearer ";
    private static Logger LOG = LoggerFactory.getLogger(RestUtil.class);
    private final HttpHeaders headers;
    private String url;
    private Map<String, String> pathParams;
    private final MultiValueMap<String, String> queryParams;
    private Class<T> typeOfT;
    private final RestTemplate restTemplate;
    private T body;

    public RestUtil() {
        headers = new HttpHeaders();
        queryParams = new LinkedMultiValueMap<>();
        pathParams = new HashMap<>();
        restTemplate = new RestTemplate();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public void setPathParams(final Map<String, String> pathParams) {
        this.pathParams = pathParams;
    }

    public void setPathParam(String param, String value) {
        this.pathParams.put(param, value);
    }

    public Class<T> getTypeOfT() {
        return this.typeOfT;
    }

    public void setTypeOfT(final Class classToSet) {
        this.typeOfT = classToSet;
    }

    public T getBody() {
        return body;
    }

    public void setBody(final T body) {
        this.body = body;
    }

    public MultiValueMap<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(final String key, final String value) {
        queryParams.set(key, value);
    }

    public void setHeader(final String headerName, final String value) {
        headers.add(headerName, value);
    }

    public String getHeader(final String headerName) {
        return headers.getFirst(headerName);
    }

    public T getQueryResult() {
        T result = null;
        final String restUrl = constructUrl();
        LOG.info("Rest URL : {}", restUrl);
        final HttpEntity<T> request = new HttpEntity<>(headers);
        result = restTemplate.exchange(restUrl, HttpMethod.GET, request, typeOfT).getBody();
        queryParams.clear();
        return result;

    }

    public String submitRequest() {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpEntity<T> request = new HttpEntity<>(body, headers);

        return restTemplate.postForEntity(url, request, String.class).getBody();
    }

    public void putRequest() {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpEntity<T> request = new HttpEntity<>(body, headers);
        restTemplate.put(url, request);
    }

    @Recover
    public Object exceptionFallback(Throwable t) throws Throwable {
        final String uuid = randomUUID().toString();
        LOG.info("logId=DQMGDAEGBAM Unable to reach {} after multiple attempts. uuid={}", url, uuid);
        throw t;
    }

    public void setAuthCreds(String authMode, final String... creds) {
        String credentials = null;
        if (authMode == null || authMode == DEFAULT_AUTH_MODE) {
            authMode = DEFAULT_AUTH_MODE;
            final String plainCreds = creds[0] + ":" + creds[1];
            final byte[] plainCredsBytes = plainCreds.getBytes();
            final byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
            credentials = new String(base64CredsBytes);
        } else if (authMode == BEARER_AUTH_MODE) {
            credentials = creds[0];
        }

        final String authHeader = authMode + credentials;
        headers.add(HttpHeaders.AUTHORIZATION, authHeader);
    }

    public String constructUrl() {

        return UriComponentsBuilder.fromUriString(getUrlWithPathParams()).queryParams(queryParams).build()
                                   .toUriString();

    }

    public String getUrlWithPathParams() {
        String urlWithParams = url;
        if (pathParams != null) {
            for (final Map.Entry<String, String> paramEntry : pathParams.entrySet()) {
                urlWithParams = urlWithParams.replace(paramEntry.getKey(), paramEntry.getValue());
            }
        }
        return urlWithParams;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public String getQueryDetails() {
        return pathParams + "," + queryParams;
    }

}
