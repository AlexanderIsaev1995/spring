package app.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("spring.primary-datasource")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties jdbcDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        return primaryDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSource jdbcTemplateDataSource() {
        return jdbcDataSourceProperties().initializeDataSourceBuilder().build();
    }
}
