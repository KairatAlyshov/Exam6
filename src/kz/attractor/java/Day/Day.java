package kz.attractor.java.Day;

import java.util.List;

public class Day {
    private int number;
    private List<Task> tasks;

    public Day(int number, List<Task> tasks) {
        this.number = number;
        this.tasks = tasks;
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
