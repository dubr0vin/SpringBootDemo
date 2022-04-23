package dev.dubrovin.springbootdemo.controllers.graphql

import dev.dubrovin.springbootdemo.entities.Group
import dev.dubrovin.springbootdemo.entities.Person
import dev.dubrovin.springbootdemo.repositories.GroupRepository
import dev.dubrovin.springbootdemo.repositories.PersonRepository
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