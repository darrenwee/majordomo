package majordomo.controller;

import majordomo.github.event.GithubIssueCommentEvent;
import majordomo.service.*;
import majordomo.util.GithubUtils;
import org.kohsuke.github.GHIssue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Controller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final GithubUtils githubUtils;
    private final ReviewRequester reviewRequester;

    @Autowired
    public Controller(GithubUtils githubUtils, ReviewRequester reviewRequester) {
        this.githubUtils = githubUtils;
        this.reviewRequester = reviewRequester;
    }

    public void run(GithubIssueCommentEvent event) {
        String commentBody = event.getComment().getBody();
        int issueNumber = event.getIssue().getNumber();

        try {
            GHIssue issue = githubUtils.getIssue(issueNumber);
            runCommands(commentBody, issue);
        } catch (IOException e) {
            logger.error(String.format("Could not retrieve issue #{}", issueNumber));
        }
    }

    public void runCommands(String commentBody, GHIssue issue) {
        for (String line : commentBody.split("\n")){
            if (line.startsWith("/")){
                String[] arguments = line.trim().substring(1).split(" ", 2);
                switch (arguments[0]) {
                    case "cc":
                        reviewRequester.requestReviews(arguments[1], issue);
                        break;
                    case "uncc":
                        reviewRequester.unrequestReviews(arguments[1], issue);
                        break;
                    case "assign":
                        break;
                    case "unassign":
                        break;
                    case "retest":
                        break;
                    case "ongoing":
                        break;
                    case "onhold":
                        break;
                    case "ok-to-merge":
                        break;
                    case "disregard":
                        break;
                    default:
                        break;
                    // still missing disregard command
                }
            }
        }
    }
}
