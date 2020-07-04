package app.controller;

import app.jdbc.JdbcTemplateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jdbc")
public class JdbcTemplateController {

    private final JdbcTemplateExample jdbcTemplateExample;

    @Autowired
    public JdbcTemplateController(JdbcTemplateExample jdbcTemplateExample) {
        this.jdbcTemplateExample = jdbcTemplateExample;
    }

    @GetMapping
    public Object trigger(){
       return jdbcTemplateExample.test();
    }
}
