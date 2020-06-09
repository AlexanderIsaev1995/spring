package app.service;

import app.dto.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto getPersonById(Long id);
    List<PersonDto> getAll();
    PersonDto createPerson(PersonDto personDto);
    PersonDto updatePerson(PersonDto personDto);
    void deletePerson(Long id);
}
