package com.example.demo2.controllers.graphql

import com.example.demo2.entities.Group
import com.example.demo2.entities.Person
import com.example.demo2.repositories.GroupRepository
import com.example.demo2.repositories.PersonRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class GraphQLQueryController(
    var groupRepository: GroupRepository,
    var personRepository: PersonRepository
) {


    @QueryMapping
    fun groups(@Argument count: Int?): Iterable<Group> {
        return groupRepository.findAll();
    }

    @QueryMapping
    fun group(@Argument id: Long): Group {
        return groupRepository.findById(id).get();
    }

    @QueryMapping
    fun people(@Argument count: Int?): Iterable<Person> {
        return personRepository.findAll();
    }

    @QueryMapping
    fun person(@Argument id: Long): Person {
        return personRepository.findById(id).get();
    }

}