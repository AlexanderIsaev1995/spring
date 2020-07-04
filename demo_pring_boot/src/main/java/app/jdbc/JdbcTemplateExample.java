package app.jdbc;

import app.dto.SalaryReport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class JdbcTemplateExample {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateExample(
            @Qualifier("jdbcTemplateDataSource") DataSource dataSource
    ) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Object test() {
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        return jdbcTemplate.query("select first_name, last_name, salary " +
                "from employees\n" +
                "         left join dept_emp on employees.emp_no = dept_emp.emp_no " +
                "         left join departments on dept_emp.dept_no = departments.dept_no " +
                "         left join salaries salaries on employees.emp_no = salaries.emp_no " +
                "where salary in (select max(salary) " +
                "                 from employees " +
                "                          left join dept_emp on employees.emp_no = dept_emp.emp_no " +
                "                          left join departments on dept_emp.dept_no = departments.dept_no " +
                "                          left join salaries salaries on employees.emp_no = salaries.emp_no " +
                "                 group by dept_emp.dept_no) " +
                "group by dept_emp.dept_no " +
                "having salary = max(salary)", namedParameters,(rs, rowNum) -> {
            return SalaryReport.builder()
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .salary(rs.getInt("salary"))
                    .build();
        });
    }
}
