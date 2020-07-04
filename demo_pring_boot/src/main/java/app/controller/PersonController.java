package app.controller;

import app.dto.PersonDto;
import app.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class PersonController {
    private final PersonService personService;

    @GetMapping(value = "/{id}")
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    public PersonDto getPersonById(@PathVariable("id") Long id) {
        return personService.getPersonById(id);
    }

    @GetMapping
    public List<PersonDto> getAll() {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return personService.getAll();
    }

    @GetMapping("/map")
    public Map<Long, PersonDto> getMap() {
        return personService.getAll().stream().collect(Collectors.toMap(PersonDto::getId, Function.identity()));
    }

    @GetMapping("/map/red")
    public ModelAndView redirect(HttpServletResponse servletResponse) {
        return new ModelAndView("redirect:/person/map");
    }

    @PostMapping
    public PersonDto createPerson(@RequestBody PersonDto personDto) {
        return personService.createPerson(personDto);
    }

    @PutMapping
    public PersonDto updatePerson(@RequestBody PersonDto personDto) {
        return personService.updatePerson(personDto);
    }

    @DeleteMapping
    public void deletePerson(@PathParam("id") Long id) {
        personService.deletePerson(id);
    }

    @PostMapping("/file")
    public String handleFile(@RequestParam("file") MultipartFile file) throws IOException {
        return IOUtils.toString(file.getBytes(), StandardCharsets.UTF_8.name());
    }
}
