package com.alonsome;

public enum Command {
    NONE,
    HELP,
    ADD,
    LIST,
    DONE,
    DELETE;

    public static String getCommand(Command cmd) {
        switch (cmd) {
            case HELP:
                return "help";
                case ADD:
                    return "add";
                    case LIST:
                        return "list";
                        case DONE:
                            return "done";
                            case DELETE:
                                return "delete";
            default:
                    return null;
        }
    }

}
