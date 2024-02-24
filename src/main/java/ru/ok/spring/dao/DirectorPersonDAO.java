package ru.ok.spring.dao;

import org.springframework.stereotype.Component;

import ru.ok.spring.models.DirectorPerson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class DirectorPersonDAO {
    private static int LAST_DIRECTORPERSON_ID;
    private List<DirectorPerson> directorPersonList;

    {
        directorPersonList = new ArrayList<>();
        directorPersonList.add(new DirectorPerson(++LAST_DIRECTORPERSON_ID, "Director1", "Department1", new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4))));
        directorPersonList.add(new DirectorPerson(++LAST_DIRECTORPERSON_ID, "Director2", "Department2", new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5))));
        directorPersonList.add(new DirectorPerson(++LAST_DIRECTORPERSON_ID, "Director3", "Department3", new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6))));

    }

    public List<DirectorPerson> index() {
        return directorPersonList;
    }

    public DirectorPerson show(int id) {
        return directorPersonList.stream().filter(directorPerson -> directorPerson.getId() == id).findAny().orElse(null);
    }

    public void save(DirectorPerson directorPerson) {
        directorPerson.setId(++LAST_DIRECTORPERSON_ID);
        directorPersonList.add(directorPerson);
    }

    public void update(int id, DirectorPerson updatedDirectorPerson) {
        DirectorPerson personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedDirectorPerson.getName());
        personToBeUpdated.setDepartment(updatedDirectorPerson.getDepartment());
        personToBeUpdated.setPersonList(updatedDirectorPerson.getPersonList());
    }

    public void delete(int id) {
        directorPersonList.removeIf(p -> p.getId() == id);
    }
}
