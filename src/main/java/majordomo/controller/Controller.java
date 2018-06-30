package majordomo.controller;

import majordomo.github.event.GithubIssueCommentEvent;
import majordomo.service.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public void parse(GithubIssueCommentEvent event){
        String commentBody = event.getComment().getBody();
        int issueNumber = event.getIssue().getNumber();

        List<Service> commands = getCommands(commentBody);

        for (Service command : commands){
            command.serve();
        }
    }

    public List<Service> getCommands(String commentBody) {
        List<Service> commands = new ArrayList<>();

        for (String line : commentBody.split("\n")){
            if (line.startsWith("/")){
                String[] arguments = line.trim().substring(1).split(" ", 2);
                switch (arguments[0]) {
                    case "cc":
                    case "uncc":
                        commands.add(new ReviewRequester(arguments));
                        break;
                    case "assign":
                    case "unassign":
                        commands.add(new Assigner(arguments));
                        break;
                    case "retest":
                        commands.add(new Retester());
                        break;
                    case "ongoing":
                        commands.add(new Labeller(arguments));
                        break;
                    case "onhold":
                        commands.add(new Labeller(arguments));
                        break;
                    case "ok-to-merge":
                        commands.add(new Merger());
                    // still missing disregard command
                }
            }
        }
        return commands;
    }
}
