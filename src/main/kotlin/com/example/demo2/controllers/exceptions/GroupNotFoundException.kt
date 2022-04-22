package com.example.demo2.controllers.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class GroupNotFoundException : ResponseStatusException(HttpStatus.NOT_FOUND, "This group does not exist") {

}