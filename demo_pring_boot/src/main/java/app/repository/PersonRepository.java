package app.repository;

import app.entity.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    Person findByFirstName(String firstName);

    @Query(value = "SELECT person FROM Person person left join fetch person.address address where address.street like %:streetPattern%")
    List<Person> fetchPersonWithAddress(@Param("streetPattern") String streetPattern, Pageable sort);

    Person findByEmail(String email);
}