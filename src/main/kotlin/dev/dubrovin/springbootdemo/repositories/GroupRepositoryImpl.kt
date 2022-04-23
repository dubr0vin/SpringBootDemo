package dev.dubrovin.springbootdemo.repositories;

import dev.dubrovin.springbootdemo.entities.Group;
import dev.dubrovin.springbootdemo.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
class GroupRepositoryImpl {
    @PersistenceContext
    var entityManager: EntityManager? = null;

    @Autowired
    @Lazy
    var groupRepository: GroupRepository? = null;

    fun addPersonToGroup(group: Group, person: Person): Group {
        group.people.add(person)
        groupRepository!!.save(group)
        return group
    }

    fun deletePersonFromGroup(group: Group, person: Person): Group {
        group.people.remove(person)
        groupRepository!!.save(group)
        return group
    }
}
