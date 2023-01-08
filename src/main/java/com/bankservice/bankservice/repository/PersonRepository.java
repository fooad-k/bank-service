package com.bankservice.bankservice.repository;

import com.bankservice.bankservice.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
