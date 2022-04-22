package com.example.demo2.entities

import com.example.demo2.entities.Person
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "group_table")
class Group(
    var title: String,

    @OneToMany
    var people: MutableList<Person> = LinkedList(),

    @Id @GeneratedValue var id: Long? = null


) {
    override fun toString(): String {
        return "Group(title='$title', id=$id)"
    }
}