package gr.foodNhealth.security;

import gr.foodNhealth.dto.CustomUserDetails;
import gr.foodNhealth.model.Nutritionist;
import gr.foodNhealth.repository.NutritionistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.TreeSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private NutritionistRepository userRepository;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        String ip = loginAttemptService.getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            LOGGER.error("User blocked, email/IP: {}/{}", email, ip);
            throw new RuntimeException("blocked");
        }

        Nutritionist user = userRepository.findByEmailAndIsActive(email, true);

        if (user == null) {
            LOGGER.error("User not found or inactive, username: {}", email);
            throw new UsernameNotFoundException("");
        }

        return new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword(), user.getIsActive(), new TreeSet<>());
    }
}
