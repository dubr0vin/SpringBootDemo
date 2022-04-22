package com.example.demo2.controllers

import com.example.demo2.controllers.exceptions.GroupNotFoundException
import com.example.demo2.controllers.exceptions.PersonNotFoundException
import com.example.demo2.repositories.GroupRepository
import com.example.demo2.repositories.PersonRepository
import com.example.demo2.entities.Group
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/group")
class GroupController(
    private val repository: GroupRepository,
    private val personRepository: PersonRepository
) {


    @GetMapping("/")
    fun findAll(): MutableIterable<Group> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): Group {
        var res = repository.findById(id)
        if (res.isEmpty) {
            throw GroupNotFoundException()
        }
        return res.get()
    }

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: Long): String {
        if (!repository.existsById(id)) {
            throw GroupNotFoundException()
        }
        repository.deleteById(id);
        return "Done"
    }

    @PostMapping("/", consumes = ["application/json"])
    fun createOne(@RequestBody group: Group): Group {
        repository.save(group)
        return group
    }

    @PostMapping("/{id}/add_person")
    fun addPerson(@PathVariable id: Long, @RequestParam(name = "id") person_id: Long): Group {
        val group = repository.findById(id)
        if (group.isEmpty) {
            throw GroupNotFoundException()
        }

        val person = personRepository.findById(person_id)
        if (person.isEmpty) {
            throw PersonNotFoundException()
        }
        var g = group.get()
        g.people.add(person.get())

        repository.save(g)
        return g
    }

    @PostMapping("/{id}/remove_person")
    fun removePerson(@PathVariable id: Long, @RequestParam(name = "id") person_id: Long): Group {
        val group = repository.findById(id)
        if (group.isEmpty) {
            throw GroupNotFoundException()
        }

        val person = personRepository.findById(person_id)
        if (person.isEmpty) {
            throw PersonNotFoundException()
        }
        var g = group.get()
        g.people.remove(person.get())

        repository.save(g)
        return g
    }

}