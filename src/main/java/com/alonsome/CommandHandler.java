package com.alonsome;

import java.io.IOException;
import java.util.List;

public class CommandHandler {
    
    private final TaskService taskService;
    
    public CommandHandler(TaskService taskService) {
        this.taskService = taskService;
    }
    
    public void handle(String[] args)  {
        String command = args[0];
//        String taskTitle = args[1];
//        String taskDescription = args[2];

        switch (command) {
            case "list":
                taskService.getTasks().forEach(System.out::println);
                break;
            case "add":
                handleAdd(args);
                break;
            case "delete":
                handleDelete(args);
                break;
            case "done":
                handleDone(args);
                break;
            default:

                System.out.println("Invalid command, please type 'list' or 'add' or 'delete' or 'done' or 'help' to show all commands");

                printUsage();
                break;

        }
    }

    private String handleAdd(String[] args){
        try {
            if(args.length == 0){
                System.err.println("Not enough arguments, please add a task");
            }

            if (args.length > 0 && args[0].equalsIgnoreCase(Command.ADD.toString())){
                //System.out.println(args.length);
                taskService.addTask(args[1], args[2]);
            }else{
                System.err.println("Not add command, please use the add command");
            }
            return taskService.getTasks().getFirst().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private List<Task> handleList(String[] args){
        return null;
    }

    private void handleDone(String[] args) {
        if(args.length == 0){
            System.err.println("Not enough arguments, please add a task");
        }

        if (args.length > 0 && args[0].equalsIgnoreCase(Command.DONE.toString())){
            List<Task> tasks = taskService.getTasks();
            for(Task task : tasks){
                if (task.getId() == Integer.parseInt(args[1])){
                    task.setCompleted(true);

                    taskService.updateTask(Integer.parseInt(args[1]), task);
                    break;
                }
            }
        }
    }

    private void handleDelete(String[] args){
        if(args.length == 0){
            System.err.println("Not enough arguments, please add a task");
        }

        if (args.length > 0 && args[0].equalsIgnoreCase(Command.DELETE.toString())){

            taskService.deleteTask(Integer.parseInt(args[1]));
        }
    }

    public void printUsage(){
        String help = """

############### Available commands #################:
add [title] [description]  - Add a new task
done [id]                  - Mark a task as completed
delete [id]                - Delete a task
list                       - List all tasks
help                       - Show this help message
""";
        System.out.println(help);
    }

}
