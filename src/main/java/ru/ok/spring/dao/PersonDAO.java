package ru.ok.spring.dao;

import org.springframework.stereotype.Component;
import ru.ok.spring.models.Level;
import ru.ok.spring.models.Person;

import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {
    private static int LAST_PEOPLE_ID;
    private List<Person> employee;

    {
        employee = new ArrayList<>();

        employee.add(new Person(++LAST_PEOPLE_ID, "Employee1", Level.LOW));
        employee.add(new Person(++LAST_PEOPLE_ID, "Employee2", Level.LOW));
        employee.add(new Person(++LAST_PEOPLE_ID, "Employee3", Level.LOW));

        employee.add(new Person(++LAST_PEOPLE_ID, "Employee11", Level.MIDDLE));
        employee.add(new Person(++LAST_PEOPLE_ID, "Employee12", Level.MIDDLE));
        employee.add(new Person(++LAST_PEOPLE_ID, "Employee13", Level.MIDDLE));

        employee.add(new Person(++LAST_PEOPLE_ID, "Employee21", Level.HIGH));
        employee.add(new Person(++LAST_PEOPLE_ID, "Employee22", Level.HIGH));
        employee.add(new Person(++LAST_PEOPLE_ID, "Employee23", Level.HIGH));

        employee.add(new Person(++LAST_PEOPLE_ID, "Employee31", Level.TOP));
        employee.add(new Person(++LAST_PEOPLE_ID, "Employee32", Level.TOP));
        employee.add(new Person(++LAST_PEOPLE_ID, "Employee33", Level.TOP));
    }

    public List<Person> index() {
        return employee;
    }

    public Person show(int id) {
        return employee.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++LAST_PEOPLE_ID);
        employee.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setLevel(updatedPerson.getLevel());
    }

    public void delete(int id) {
        employee.removeIf(p -> p.getId() == id);
    }
}