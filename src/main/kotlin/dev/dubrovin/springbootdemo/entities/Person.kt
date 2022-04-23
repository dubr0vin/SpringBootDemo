package dev.dubrovin.springbootdemo.entities

import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Person(
    var name: String,

    @ElementCollection
    var phones: MutableSet<String> = HashSet(),

    @Id @GeneratedValue var id: Long? = null
) {
    override fun toString(): String {
        return "Person(name='$name', id=$id)"
    }
}