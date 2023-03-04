package kz.attractor.java.Day;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskDataModel {
    private List<Task> tasks = new ArrayList<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("data/task.json");

    public TaskDataModel() {
        tasks.addAll(readEmployersFile());
    }
    public static List<Task> readEmployersFile(){
        String json = "";
        try {
            json = Files.readString(PATH);
        }catch (IOException e){
            e.printStackTrace();
        }        return GSON.fromJson(json, new TypeToken<List<Task>>() {}.getType());
    }

    public static void writeEmployersFile(List<Task> employeeList){
        String str = GSON.toJson(employeeList);
        try {
            byte[] strToBytes = str.getBytes();
            Files.write(PATH, strToBytes);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
