package io.bookquest.config.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bookquest.config.integration.LogClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import software.amazon.awssdk.utils.IoUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LogHandlerFIlter extends OncePerRequestFilter {

    @Autowired
    private LogClient logClient;

    @Value("${new_relic.key}")
    private String logApiKey;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String requestBody = IoUtils.toUtf8String(request.getInputStream());
        var requestContent = new ContentCachingRequestWrapper(request);
        var content = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestContent, content);

        byte[] responseByte = content.getContentAsByteArray();
        var responseBody = new String(responseByte, StandardCharsets.UTF_8);

        content.copyBodyToResponse();

        try {
            var logData = new LogData(requestBody, responseBody);
            logClient.log(logApiKey, logData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
