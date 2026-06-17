package com.interview.platform.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, TokenBucket> buckets = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String clientIp = request.getRemoteAddr();
        TokenBucket bucket = buckets.computeIfAbsent(clientIp, k -> new TokenBucket());

        if (!bucket.tryConsume()) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("success", false);
            errorDetails.put("data", null);
            Map<String, Object> errorInfo = new HashMap<>();
            errorInfo.put("status", 429);
            errorInfo.put("title", "Too Many Requests");
            errorInfo.put("detail", "Rate limit exceeded. Retry after 60 seconds.");
            errorInfo.put("timestamp", Instant.now().toString());
            errorDetails.put("error", errorInfo);

            objectMapper.writeValue(response.getWriter(), errorDetails);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private static class TokenBucket {
        private final int capacity = 100;
        private AtomicInteger tokens = new AtomicInteger(capacity);
        private long lastRefillTime = System.currentTimeMillis();

        public synchronized boolean tryConsume() {
            refill();
            if (tokens.get() > 0) {
                tokens.decrementAndGet();
                return true;
            }
            return false;
        }

        private void refill() {
            long now = System.currentTimeMillis();
            if (now - lastRefillTime > 60000) {
                tokens.set(capacity);
                lastRefillTime = now;
            }
        }
    }
}
