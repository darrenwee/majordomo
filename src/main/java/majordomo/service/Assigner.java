package majordomo.service;

import majordomo.util.GithubUtils;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class Assigner {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final GithubUtils githubUtils;

    public Assigner(GithubUtils githubUtils) {
        this.githubUtils = githubUtils;
    }

    public void assignUsers(String usersString, GHIssue issue) {
        Set<GHUser> userObjects = githubUtils.getGithubUsersFromString(usersString);

        try {
            issue.addAssignees(userObjects);
        } catch (IOException e) {
            logger.error(String.format("Failed to assign #{} to {}: {}", issue, usersString), e.getCause().getMessage());
        }
    }

    public void unassignUsers(String usersString, GHIssue issue) {
        Set<GHUser> userObjects = githubUtils.getGithubUsersFromString(usersString);

        try {
            issue.removeAssignees(userObjects);
        } catch (IOException e) {
            logger.error(String.format("Failed to unassign {} from #{}: {}", issue, usersString), e.getCause().getMessage());
        }
    }
}
