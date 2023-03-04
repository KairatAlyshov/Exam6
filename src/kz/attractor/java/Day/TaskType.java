package kz.attractor.java.Day;
import com.google.gson.Gson;

public enum TaskType {
    ORDINARY("ordinary", "yellow"),
    URGENT("urgent", "red"),
    WORK("work", "green"),
    SHOPPING("shopping", "blue"),
    OTHER("other", "brown");

    private String name;
    private String color;

    TaskType(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}