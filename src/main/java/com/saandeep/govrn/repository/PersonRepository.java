package com.saandeep.govrn.repository;

import com.saandeep.govrn.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<Person, Long> {
    Person findByAadhaarNumber(String aadhaarNumber);
}
