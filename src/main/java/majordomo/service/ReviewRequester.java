package majordomo.service;

import majordomo.util.GithubUtils;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class ReviewRequester {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final GithubUtils githubUtils;

    @Autowired
    public ReviewRequester(GithubUtils githubUtils) {
        this.githubUtils = githubUtils;
     }


    public void requestReviews(String usersString, GHIssue issue) {
        Set<GHUser> userObjects = getGithubUsersFromString(usersString);
        GHPullRequest pr = (GHPullRequest) issue;

        try {
            pr.requestReviewers(new ArrayList<>(userObjects));
        } catch (IOException e) {
            logger.error(String.format("Failed to request {} to review #{}", users, issue.getNumber()));
        }
    }


    public void unrequestReviews(String usersString, GHIssue issue) {
        Set<GHUser> userObjects = getGithubUsersFromString(usersString);
        GHPullRequest pr = (GHPullRequest) issue;

        try {
            issue.removeAssignees(userObjects);
        } catch (IOException e) {
            logger.error(String.format("Failed to unassign {} from #{}", users, issue.getNumber()));
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof ReviewRequester) {
            return object.getCommand() != null &&
                    object.getUsers() != null &&
                    command.equals(object.getCommand()) &&
                    users.equals(object.getUsers());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Command: " + command + "| Users: " + users;
    }

    private Set<GHUser> getGithubUsersFromString(String usersString) {
        Set<String> users = new HashSet<>(Arrays.asList(usersString.split(" ")));
        return githubUtils.getUserObjects(users);
    }
}
