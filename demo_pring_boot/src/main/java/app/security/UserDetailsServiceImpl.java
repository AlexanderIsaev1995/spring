package app.security;

import app.entity.Person;
import app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@Qualifier("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email);
        if (Objects.isNull(person)){
            return null;
        }
        return new User(
                person.getEmail(),
                person.getPassword(),
                Collections.singletonList(() -> "ROLE_ADMIN")
        );
    }
}
