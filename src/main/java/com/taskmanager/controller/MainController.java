package com.taskmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import com.taskmanager.util.MyCalendar;
import javassist.tools.reflect.CannotCreateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    private final TaskService taskService;
    private final MyCalendar calendar;

    public MainController(TaskService taskService, MyCalendar calendar) {
        this.taskService = taskService;
        this.calendar = calendar;
    }

    @GetMapping("/")
    public String redirectToCalendar() {
        String year = calendar.getProperDate().toString().substring(0, 7);
        return "redirect:/calendar/" + year;
    }

    @GetMapping("/calendar/{date}")
    public String calendar(Model model, @PathVariable("date") String date) {
        try {
            String jsonCalendar = new ObjectMapper().writeValueAsString(calendar.getCalendar(date));
            model.addAttribute("jsonCalendar", jsonCalendar);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "calendar";
    }

    @GetMapping("/day/{date}")
    public String day(Task task, Model model, @PathVariable("date") String date) {
        model.addAttribute("tasks", taskService.getTasksByDate(date));
        return "day";
    }

    @PostMapping("/day/add/{year}")
    public String addTask(Task task, @PathVariable("year") String year) {
        try {
            taskService.createTask(task);
        } catch (CannotCreateException e) {
            e.printStackTrace();
        }
        return "redirect:/day/" + year;
    }

    @GetMapping("/next-month/{year}")
    public String getNextMonth(@PathVariable("year") String year) {
        String date = calendar.getProperDateNextMonth(year).toString().substring(0, 7);
        return "redirect:/calendar/" + date;
    }

    @GetMapping("/previous-month/{year}")
    public String getPreviousMonth(@PathVariable("year") String year) {
        String date = calendar.getProperDatePreviousMonth(year).toString().substring(0, 7);
        return "redirect:/calendar/" + date;
    }

    @GetMapping("/task/delete/{date}/{id}")
    public String deleteTask(@PathVariable("date") String date, @PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/day/" + date;
    }
}
