package majordomo.github.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import majordomo.github.entity.GithubIssue;
import majordomo.github.entity.GithubIssueComment;
import majordomo.github.entity.GithubUser;

public class GithubIssueCommentEvent {
    @JsonProperty("action")
    private String action;

    @JsonProperty("issue")
    private GithubIssue issue;

    @JsonProperty("comment")
    private GithubIssueComment comment;

    @JsonProperty("sender")
    private GithubUser sender;

    public String getAction() {
        return action;
    }

    public GithubIssue getIssue() {
        return issue;
    }

    public GithubIssueComment getComment() {
        return comment;
    }

    public GithubUser getSender() {
        return sender;
    }

    public String toString() {
        return String.format("event_comment(action=%s&issue=%s&comment=%s&user=%s)", action, issue.toString(),
                comment.toString(), sender.toString());
    }
}
