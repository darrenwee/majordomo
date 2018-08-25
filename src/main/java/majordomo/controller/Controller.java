package majordomo.controller;

import majordomo.github.event.GithubIssueCommentEvent;
import majordomo.service.*;
import majordomo.util.GithubUtils;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHPullRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Controller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final GithubUtils githubUtils;
    private final ReviewRequester reviewRequester;
    private final Assigner assigner;

    @Autowired
    public Controller(GithubUtils githubUtils, ReviewRequester reviewRequester, Assigner assigner) {
        this.githubUtils = githubUtils;
        this.reviewRequester = reviewRequester;
        this.assigner = assigner;
    }

    public void run(GithubIssueCommentEvent event) {
        String commentBody = event.getComment().getBody();
        int issueNumber = event.getIssue().getNumber();

        GHIssue issue = githubUtils.getIssue(issueNumber);
        GHPullRequest pr = githubUtils.getPullRequest(issueNumber);
        runCommands(commentBody, issue, pr);
    }

    public void runCommands(String commentBody, GHIssue issue, GHPullRequest pr) {
        for (String line : commentBody.split("\n")){
            if (line.startsWith("/")){
                String[] arguments = line.trim().substring(1).split(" ", 2);
                switch (arguments[0]) {
                    case "cc":
                        logger.info("Requesting review of #{} from {}", issue.getNumber(), arguments[1]);
                        reviewRequester.requestReviews(arguments[1], pr);
                        break;
                    case "assign":
                        logger.info("Assigning {} to #{}", arguments[1], issue.getNumber());
                        assigner.assignUsers(arguments[1], issue);
                        break;
                    case "unassign":
                        logger.info("Unassigning {} to #{}", arguments[1], issue.getNumber());
                        assigner.unassignUsers(arguments[1], issue);
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
