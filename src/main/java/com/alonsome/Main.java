package com.alonsome;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        CommandHandler handler = new CommandHandler(new TaskService());
        handler.handle(args);
    }
}