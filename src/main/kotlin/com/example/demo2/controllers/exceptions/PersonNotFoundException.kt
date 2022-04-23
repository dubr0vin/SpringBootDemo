package com.example.demo2.controllers.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class PersonNotFoundException : ResponseStatusException {
    constructor(id: Long) : super(HttpStatus.NOT_FOUND, "Person $id does not exist")
}