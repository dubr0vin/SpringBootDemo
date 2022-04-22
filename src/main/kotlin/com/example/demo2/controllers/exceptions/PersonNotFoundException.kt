package com.example.demo2.controllers.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class PersonNotFoundException: ResponseStatusException(HttpStatus.NOT_FOUND, "This person does not exist") {
}