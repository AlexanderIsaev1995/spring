package app.security;


import app.converter.CustomConversionService;
import app.dto.SignUpDto;
import app.entity.Person;
import app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Transactional
public class JwtAuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final PersonRepository personRepository;
    private final CustomConversionService conversionService;

    @Autowired
    public JwtAuthenticationController(
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            @Qualifier("customUserDetailsService") UserDetailsService userDetailsService,
            PersonRepository personRepository,
            CustomConversionService conversionService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.personRepository = personRepository;
        this.conversionService = conversionService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        Person person = conversionService.convert(signUpDto, Person.class);
        personRepository.save(person);
        return ResponseEntity.ok("");
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
