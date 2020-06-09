package app.converter;

import app.dto.PersonDto;
import app.entity.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonDtoConverter implements Converter<Person, PersonDto> {
    @Override
    public PersonDto convert(Person person) {
        PersonDto target = new PersonDto();
        target.setFirstName(person.getFirstName());
        target.setLastName(person.getLastName());
        target.setId(person.getId());
        return target;
    }
}
