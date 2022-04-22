package com.example.demo2

import org.springframework.data.repository.CrudRepository

interface GroupRepository : CrudRepository<Group, Long> {
}

interface PersonRepository : CrudRepository<Person, Long> {
}
