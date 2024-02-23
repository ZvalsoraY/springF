package ru.ok.spring.dao;

import org.springframework.stereotype.Component;
import ru.ok.spring.models.Person;

import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> employee;

    {
        employee = new ArrayList<>();

        employee.add(new Person(++PEOPLE_COUNT, "Tom", 24, "tom@mail.ru"));
        employee.add(new Person(++PEOPLE_COUNT, "Bob", 52, "bob@mail.ru"));
        employee.add(new Person(++PEOPLE_COUNT, "Mike", 18, "mike@yahoo.com"));
        employee.add(new Person(++PEOPLE_COUNT, "Katy", 34, "katy@gmail.com"));
    }

    public List<Person> index() {
        return employee;
    }

    public Person show(int id) {
        return employee.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        employee.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        employee.removeIf(p -> p.getId() == id);
    }
}
