package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users", method = RequestMethod.DELETE)
public class UserController {

    @GetMapping(path = "{email}")
    public UserDto getUserByEmail(
            @PathVariable(name = "email") String email,
            @RequestParam("email") String emailAsParam,
            @RequestParam Map<String, String> param,
            @RequestHeader Map<String, String> headers,
            @RequestPart MultipartFile file
    ) {
        return UserDto.builder().email(email).build();
    }

    @GetMapping
    public List<UserDto> getAll() {
        return Arrays.asList(UserDto.builder().email("1").build(), UserDto.builder().email("2").build());
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto user) {
        return user;
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto user) {
        return user;
    }

    @DeleteMapping
    public void deleteUser(@RequestBody UserDto user) {
        System.out.println("deleted");
    }
}
