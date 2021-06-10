package com.taskmanager.util;

import com.taskmanager.service.TaskService;
import org.springframework.stereotype.Component;

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
        fillMonth(previousMonth, previous, firstDayInPreviousMonth);

        firstDayOfWeekInCurrentMonth += firstDayInCurrentMonth.getDayOfWeek().getValue();
        previousMonth.subList(0, previous.lengthOfMonth() - firstDayOfWeekInCurrentMonth).clear();//daysBeforeFirstDayInCurrentMonth

        List<LocalDate> currentMonth = new ArrayList<>(firstDayInCurrentMonth.lengthOfMonth());
        fillMonth(currentMonth, current, firstDayInCurrentMonth);

        List<LocalDate> nextMonth = new ArrayList<>(firstDayInNextMonth.lengthOfMonth());
        fillMonth(nextMonth, next, firstDayInNextMonth);

        addDaysToCalendar(calendar, previousMonth);
        addDaysToCalendar(calendar, currentMonth);

        nextMonth.subList(42 - calendar.size(), nextMonth.size()).clear();
        addDaysToCalendar(calendar, nextMonth);

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

    private void fillMonth(List<LocalDate> monthToFill, LocalDate month, LocalDate firstDayInMonth) {
        for (int i = 1; i <= firstDayInMonth.lengthOfMonth(); i++) {
            monthToFill.add(LocalDate.of(month.getYear(), month.getMonth().getValue(), i));
        }
    }

    private void addDaysToCalendar(Map<LocalDate, Integer> calendar, List<LocalDate> listToAdd) {
        int count;
        for (LocalDate date : listToAdd) {
            count = taskService.getTasksByDate(date.toString()).size();
            calendar.put(date, count);
        }
    }
}
