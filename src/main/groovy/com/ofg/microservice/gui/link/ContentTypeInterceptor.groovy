package com.ofg.microservice.gui.link

import groovy.transform.TypeChecked
import org.springframework.http.HttpRequest
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.client.support.HttpRequestWrapper

@TypeChecked
class ContentTypeInterceptor implements ClientHttpRequestInterceptor {
    private final String contentType;

    public ContentTypeInterceptor(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request)
        requestWrapper.getHeaders().setContentType(MediaType.valueOf(contentType))
        requestWrapper.getHeaders().setAccept([MediaType.valueOf(contentType)])
        return execution.execute(requestWrapper, body)
    }
}