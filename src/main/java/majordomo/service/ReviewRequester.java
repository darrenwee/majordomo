package majordomo.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReviewRequester implements Service {
    private String command;
    private Set<String> users;
    
    public ReviewRequester(String[] arguments) {
        command = arguments[0];
        users = new HashSet<>(Arrays.asList(arguments[1].split(" ")));
     }

    @Override
    public void serve() {

    }

    @Override
    public boolean equals(Object o) {
        ReviewRequester object = (ReviewRequester) o;
        return object.getCommand() != null &&
                object.getUsers() != null &&
                command.equals(object.getCommand()) &&
                users.equals(object.getUsers());
    }

    @Override
    public String toString() {
        return "Command: " + command + "| Users: " + users;
    }

    public String getCommand() {
        return command;
    }

    public Set<String> getUsers() {
        return users;
    }


}
