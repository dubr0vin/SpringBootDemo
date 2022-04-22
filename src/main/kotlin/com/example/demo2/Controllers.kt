package com.example.demo2

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
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist")
        }
        return res.get()
    }

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: Long): String {
        if (!repository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist")
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
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist")
        }

        val person = personRepository.findById(person_id)
        if (person.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This person does not exist")
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
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist")
        }

        val person = personRepository.findById(person_id)
        if (person.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This person does not exist")
        }
        var g = group.get()
        g.people.remove(person.get())

        repository.save(g)
        return g
    }

}

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
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist")
        }
        return res.get()
    }

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: Long): String {
        if (!repository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist")
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
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This person does not exist")
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
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This person does not exist")
        }
        var p = person.get()
        p.phones.remove(phone)
        repository.save(p)
        return p
    }
}

@RestController
@RequestMapping("/api")
class PingController(private val repository: GroupRepository) {
    @GetMapping("/ping")
    fun ping() = "pong"
}