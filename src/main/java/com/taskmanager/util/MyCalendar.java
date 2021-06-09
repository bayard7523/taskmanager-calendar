package com.taskmanager.util;

import com.taskmanager.service.TaskService;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Component
public class MyCalendar {
    private final TaskService taskService;

    public MyCalendar(TaskService taskService) {
        this.taskService = taskService;
    }

    public Map<LocalDate, Integer> getCalendar(String properDate) {
        int firstDayOfWeekInCurrentMonth = -1;
        LocalDate firstDayInCurrentMonth;

        LocalDate firstDayInPreviousMonth;

        LocalDate firstDayInNextMonth;

        Map<LocalDate, Integer> calendar = new TreeMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDate current = YearMonth.parse(properDate, formatter).atDay(1);

        LocalDate previous = current.minus(1, ChronoUnit.MONTHS);
        LocalDate next = current.plus(1, ChronoUnit.MONTHS);

        firstDayInCurrentMonth = current.with(TemporalAdjusters.firstDayOfMonth());

        firstDayInPreviousMonth = previous.with(TemporalAdjusters.firstDayOfMonth());

        firstDayInNextMonth = next.with(TemporalAdjusters.firstDayOfMonth());

        List<LocalDate> previousMonth = new ArrayList<>(firstDayInPreviousMonth.lengthOfMonth());
        for (int i = 1; i <= firstDayInPreviousMonth.lengthOfMonth(); i++) {
            previousMonth.add(LocalDate.of(previous.getYear(), previous.getMonth().getValue(), i));
        }

        firstDayOfWeekInCurrentMonth += firstDayInCurrentMonth.getDayOfWeek().getValue();
        previousMonth.subList(0, previous.lengthOfMonth() - firstDayOfWeekInCurrentMonth).clear();//daysBeforeFirstDayInCurrentMonth

        List<LocalDate> currentMonth = new ArrayList<>(firstDayInCurrentMonth.lengthOfMonth());
        for (int i = 1; i <= firstDayInCurrentMonth.lengthOfMonth(); i++) {
            currentMonth.add(LocalDate.of(current.getYear(), current.getMonth().getValue(), i));
        }

        List<LocalDate> nextMonth = new ArrayList<>(firstDayInNextMonth.lengthOfMonth());
        for (int i = 1; i <= firstDayInNextMonth.lengthOfMonth(); i++) {
            nextMonth.add(LocalDate.of(next.getYear(), next.getMonth().getValue(), i));
        }
        int count = 0;
        for (LocalDate date : previousMonth) {
            count = taskService.getTasksByDate(date.toString()).size();
            if (count == 0) {
                calendar.put(date, count);
            }
            calendar.put(date, count);
        }

        for (LocalDate date : currentMonth) {
            count = taskService.getTasksByDate(date.toString()).size();
            if (count == 0) {
                calendar.put(date, count);
            }
            calendar.put(date, count);
        }
        nextMonth.subList(42 - calendar.size(), nextMonth.size()).clear();

        for (LocalDate date : nextMonth) {
            count = taskService.getTasksByDate(date.toString()).size();
            if (count == 0) {
                calendar.put(date, count);
            }
            calendar.put(date, count);
        }

        return calendar;
    }

    public LocalDate getProperDate() {
        return LocalDate.now();
    }

    public LocalDate getProperDateNextMonth(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDate currentDate = YearMonth.parse(date, formatter).atDay(1);
        return currentDate.plus(1, ChronoUnit.MONTHS);
    }

    public LocalDate getProperDatePreviousMonth(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDate currentDate = YearMonth.parse(date, formatter).atDay(1);
        return currentDate.minus(1, ChronoUnit.MONTHS);
    }
}
