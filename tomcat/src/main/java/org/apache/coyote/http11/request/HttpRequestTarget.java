package org.apache.coyote.http11.request;

import java.util.Map;

public class HttpRequestTarget {

    private final String path;
    private final HttpRequestQueryString queryString;

    public HttpRequestTarget(final String targetUrl) {
        this.path = extractPath(targetUrl);
        this.queryString = new HttpRequestQueryString(extractQueryString(targetUrl));
    }

    private String extractPath(final String targetUrl) {
        final int questionMarkIndex = targetUrl.indexOf('?');
        if (questionMarkIndex != -1) {
            return targetUrl.substring(0, questionMarkIndex);
        }
        return targetUrl;
    }

    private String extractQueryString(final String targetUrl) {
        final int questionMarkIndex = targetUrl.indexOf('?');
        if (questionMarkIndex != -1) {
            return targetUrl.substring(questionMarkIndex + 1);
        }
        return "";
    }

    public String getPath() {
        return path;
    }

    public boolean containsParameterKey(final String parameterKey) {
        return queryString.contains(parameterKey);
    }

    public String getParameterValue(final String parameterKey) {
        return queryString.getValue(parameterKey);
    }

    public Map<String, String> getParameters() {
        return queryString.getParameters();
    }
}
