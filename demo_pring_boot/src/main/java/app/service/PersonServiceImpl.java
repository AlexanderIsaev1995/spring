package app.service;

import app.dto.PersonDto;
import app.entity.Person;
import app.exception.PersonException;
import app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ConversionService conversionService;
    private final String value;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, ConversionService conversionService, @Value("test.field") String value) {
        this.personRepository = personRepository;
        this.conversionService = conversionService;
        this.value = value;
    }

    @Override
    public PersonDto getPersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonException("Person is not found"));
        return conversionService.convert(person, PersonDto.class);
    }

    @Override
    public List<PersonDto> getAll() {
        List<Person> people = personRepository.findAll();
        return people.stream().map(person -> conversionService.convert(person, PersonDto.class)).collect(Collectors.toList());
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = conversionService.convert(personDto, Person.class);
        return conversionService.convert(personRepository.save(person), PersonDto.class);
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto) {
        Person person = personRepository.findById(personDto.getId()).orElseThrow(() -> new PersonException("Person is not found"));
        person.setLastName(personDto.getLastName());
        person.setFirstName(personDto.getFirstName());
        return conversionService.convert(personRepository.save(person), PersonDto.class);
    }

    @Override
    public void deletePerson(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonException("Person is not found"));
        personRepository.delete(person);
    }
}
