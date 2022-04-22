package com.example.demo2.controllers

import com.example.demo2.controllers.exceptions.GroupNotFoundException
import com.example.demo2.controllers.exceptions.PersonNotFoundException
import com.example.demo2.repositories.PersonRepository
import com.example.demo2.entities.Person
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/person")
class PersonController(
    private val repository: PersonRepository
) {
    @GetMapping("/")
    fun findAll(): MutableIterable<Person> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): Person {
        var res = repository.findById(id)
        if (res.isEmpty) {
            throw PersonNotFoundException()
        }
        return res.get()
    }

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: Long): String {
        if (!repository.existsById(id)) {
            throw PersonNotFoundException()
        }
        repository.deleteById(id);
        return "Done"
    }

    @PostMapping("/", consumes = ["application/json"])
    fun createOne(@RequestBody person: Person): Person {
        repository.save(person)
        return person
    }

    @PostMapping("/{id}/add_phone")
    fun addPhone(@PathVariable id: Long, @RequestParam(name = "phone") phone: String): Person {
        val person = repository.findById(id)
        if (person.isEmpty) {
            throw PersonNotFoundException()
        }
        var p = person.get()
        p.phones.add(phone)
        repository.save(p)
        return p
    }

    @PostMapping("/{id}/remove_phone")
    fun removePhone(@PathVariable id: Long, @RequestParam(name = "phone") phone: String): Person {
        val person = repository.findById(id)
        if (person.isEmpty) {
            throw PersonNotFoundException()
        }
        var p = person.get()
        p.phones.remove(phone)
        repository.save(p)
        return p
    }
}