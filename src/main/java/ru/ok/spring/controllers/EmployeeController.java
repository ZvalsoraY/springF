package ru.ok.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ok.spring.dao.PersonDAO;
import ru.ok.spring.models.Level;
import ru.ok.spring.models.Person;


import javax.validation.Valid;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final PersonDAO personDAO;

    @Autowired
    public EmployeeController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("employee", personDAO.index());
        return "employee/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "employee/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        model.addAttribute("levels", Level.values());
        return "employee/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "employee/new";

        personDAO.save(person);
        return "redirect:/employee";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("levels", Level.values());
        return "employee/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "employee/edit";

        personDAO.update(id, person);
        return "redirect:/employee";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/employee";
    }
}

