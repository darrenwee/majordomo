package majordomo.service;

import majordomo.util.GithubUtils;
import org.kohsuke.github.GHPersonSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Assigner implements Service{
    private String command;
    private Set<String> users;


    public Assigner(String[] arguments) {
        command = arguments[0];
        users = new HashSet<>(Arrays.asList(arguments[1].split(" ")));

    }

    @Override
    public void serve() {

    }

    public Set<String> getUsers() {
        return users;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public boolean equals(Object o) {
        Assigner object = (Assigner) o;
        return object.getCommand() != null &&
                object.getUsers() != null &&
                command.equals(object.getCommand()) &&
                users.equals(object.getUsers());
    }

}
