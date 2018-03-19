package gr.foodNhealth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureListener.class);

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent e) {
        LOGGER.error("Login attempt failed for Email: {}", e.getAuthentication().getPrincipal());
        String clientIP = loginAttemptService.getClientIP();
        loginAttemptService.loginFailed(clientIP);
    }

}