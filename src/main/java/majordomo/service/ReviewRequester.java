package majordomo.service;

import majordomo.util.GithubUtils;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@Component
public class ReviewRequester {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final GithubUtils githubUtils;

    @Autowired
    public ReviewRequester(GithubUtils githubUtils) {
        this.githubUtils = githubUtils;
     }

    public void requestReviews(String usersString, GHPullRequest pr) {
        Set<GHUser> userObjects = githubUtils.getGithubUsersFromString(usersString);

        if (pr == null) {
            logger.error("Unable to request review on null PR");
            return;
        }

        try {
            pr.requestReviewers(new ArrayList<>(userObjects));
        } catch (IOException e) {
            logger.error("Failed to request {} to review #{}: {}", usersString, pr.getNumber(), e.getCause().getMessage());
        }
    }

    public void unrequestReviews(String usersString, GHPullRequest pr) {
        Set<GHUser> userObjects = githubUtils.getGithubUsersFromString(usersString);

        if (pr == null) {
            logger.error("Unable to unrequest review on PR");
            return;
        }

        try {
            pr.removeAssignees(userObjects);
        } catch (IOException e) {
            logger.error("Failed to unassign {} from #{}: {}", usersString, pr.getNumber(), e.getCause().getMessage());
        }
    }
}
