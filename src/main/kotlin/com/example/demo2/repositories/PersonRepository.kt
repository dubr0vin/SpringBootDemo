package com.example.demo2.repositories

import com.example.demo2.entities.Person
import org.springframework.data.repository.CrudRepository

interface PersonRepository : CrudRepository<Person, Long> {
}
