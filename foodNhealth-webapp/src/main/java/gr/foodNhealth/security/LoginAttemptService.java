package gr.foodNhealth.security;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    @Value("${security.maxLoginAttempts:3}")
    private int maxLoginAttempts;

    @Value("${security.banTime:600}")
    private long banTime;

    @Autowired
    private HttpServletRequest request;

    private LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() {
        super();
    }

    @PostConstruct
    public void init() {
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(banTime, TimeUnit.SECONDS).
                build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(final String key) {
                        return 0;
                    }
                });
    }

    public void loginSucceeded(final String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(final String key) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(key);
        } catch (final ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(final String key) {
        try {
            return attemptsCache.get(key) >= maxLoginAttempts;
        } catch (final ExecutionException e) {
            return false;
        }
    }

    public String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
