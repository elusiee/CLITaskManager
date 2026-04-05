package com.alonsome;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    //private final List<Task> tasks =  new ArrayList<>();
    private final Path filePath = Path.of("tasks.json");
    private ObjectMapper mapper;

    public TaskService() {
    }

    public void addTask(String title, String description) throws IOException {
        List<Task> tasks = getTasks();
        tasks.add(new Task(title, description));

        mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), tasks);
    }

    public List<Task> getTasks() {
        mapper = new ObjectMapper();

        try {
            if (!filePath.toFile().exists() || filePath.toFile().length() == 0){
                mapper.writeValue(filePath.toFile(), new ArrayList<Task>());
                return new ArrayList<>();
            }
            return mapper.readValue(filePath.toFile(), new TypeReference<List<Task>>() {
            });
        }catch(Exception e){
            System.err.println("Error reading file");
        }
        return null;
    }

    public void updateTask(int id, Task updateTask) {
        List<Task> tasks = getTasks();
        for (Task task : tasks) {
            if (task.getId() == id) {
                int index = tasks.indexOf(task);
                tasks.set(index, updateTask);
                mapper = new ObjectMapper();
                try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), tasks);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }

    }

    public void deleteTask(int id) {
        List<Task> tasks = getTasks();
        for (Task task : tasks) {
            if (task.getId() == id) {
                tasks.remove(task);
                mapper = new ObjectMapper();
                try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), tasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


}
