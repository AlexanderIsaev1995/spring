package app.converter;

import app.dto.AddressDto;
import app.dto.PersonDto;
import app.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PersonToPersonDtoConverter implements Converter<Person, PersonDto> {
    private final CustomConversionService conversionService;

    @Autowired
    public PersonToPersonDtoConverter(CustomConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public PersonDto convert(Person person) {
        PersonDto target = new PersonDto();
        target.setFirstName(person.getFirstName());
        target.setLastName(person.getLastName());
        target.setId(person.getId());
        target.setAddressDtos(conversionService.convert(person.getAddress(), AddressDto.class));
        return target;
    }
}
