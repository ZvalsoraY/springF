package ru.ok.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ok.spring.dao.DirectorPersonDAO;
import ru.ok.spring.dao.PersonDAO;
import ru.ok.spring.models.DirectorPerson;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/director")
public class DirectorController {

    private final DirectorPersonDAO directorPersonDAO;
    private final PersonDAO personDAO;

    @Autowired
    public DirectorController(DirectorPersonDAO directorPersonDAO, PersonDAO personDAO) {
        this.directorPersonDAO = directorPersonDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("director", directorPersonDAO.index());
        return "director/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        DirectorPerson directorLocal = directorPersonDAO.show(id);
        model.addAttribute("directorPerson", directorLocal);
        model.addAttribute("employee", personDAO.index()
                .stream().filter(x -> directorLocal.getPersonList().contains(x.getId())).toArray());
        return "director/show";
    }

    @GetMapping("/new")
    public String newDirectorPerson(Model model) {
        DirectorPerson directorPerson = new DirectorPerson();
        model.addAttribute("directorPerson", directorPerson);
        model.addAttribute("employee", personDAO.index());
        return "director/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("directorPerson") @Valid DirectorPerson directorPerson,
                         @RequestParam("personId") List<Integer> personId, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "director/new";

        directorPerson.setPersonList(personId);
        directorPersonDAO.save(directorPerson);
        return "redirect:/director";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("directorPerson", directorPersonDAO.show(id));
        model.addAttribute("employee", personDAO.index());
        return "director/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("directorPerson") @Valid DirectorPerson directorPerson, BindingResult bindingResult,
                         @RequestParam("personId") List<Integer> personId, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "director/edit";
        directorPerson.setPersonList(personId);
        directorPersonDAO.update(id, directorPerson);
        return "redirect:/director";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        directorPersonDAO.delete(id);
        return "redirect:/director";
    }
}