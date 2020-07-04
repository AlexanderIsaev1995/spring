package app.converter;

import app.dto.SignUpDto;
import app.entity.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class SignUpDtoToPersonConverter implements Converter<SignUpDto, Person> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Person convert(SignUpDto signUpDto) {
        Person person = new Person();
        person.setEmail(signUpDto.getEmail());
        person.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        return person;
    }
}
