package majordomo.util;

import org.kohsuke.github.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class GithubUtils {

    private GitHub github;
    private GHRepository repo;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String REPO_NAME = "darrenwee/majordomo-playground";


    private GithubUtils() {
        try {
            github = GitHub.connectUsingOAuth(System.getenv("GITHUB_API_TOKEN"));
            repo = github.getRepository(REPO_NAME);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public GHRepository getRepo() {
        return repo;
    }

    public boolean isUserCollaborator(GHUser user) throws IOException {
        GHPersonSet collaborators = repo.getCollaborators();
        return collaborators.contains(user);
    }

    public GHIssue getIssue(int issueNumber)  {
        try {
            return repo.getIssue(issueNumber);
        } catch (IOException e) {
            logger.error("Could not retrieve issue #{}", issueNumber);
            return null;
        }
    }

    public Set<GHUser> getUserObjects(Set<String> users){
        Set<GHUser> userObjects = new HashSet<>();

        for (String username : users){
            try {
                userObjects.add(github.getUser(username));
            } catch (IOException e) {
                logger.error(e.getMessage()); // assumes all usernames are correct
            }
        }

        return userObjects;
    }

    public Set<GHUser> getGithubUsersFromString(String usersString) {
        Set<String> users = new HashSet<>(Arrays.asList(usersString.split(" ")));
        return getUserObjects(users);
    }

    public GHPullRequest getPullRequest(int issueNumber) {
        try {
            return repo.getPullRequest(issueNumber);
        } catch (IOException e) {
            logger.error("Could not retrieve PR #{}", issueNumber);
            return null;
        }
    }
}
