package io.bookquest.config.security;

import io.bookquest.entrypoint.v1.integration.database.dto.UserDataTransfer;
import io.bookquest.entrypoint.v1.mapper.UserMapper;
import io.bookquest.usecase.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsComponent implements UserDetailsService {

    @Autowired
    private DatabaseRepository databaseRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDataTransfer user = databaseRepository.getUser(username);
        return UserSecurity.toUserDetails(username, user.getSenha());
    }
}
