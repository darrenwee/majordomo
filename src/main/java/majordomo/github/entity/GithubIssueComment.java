package majordomo.github.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubIssueComment {
    @JsonProperty("url")
    private String url;

    @JsonProperty("author_association")
    private String author_association;

    @JsonProperty("body")
    private String body;

    @JsonProperty("id")
    private String id;

    @JsonProperty("user")
    private GithubUser user;

    public String getUrl() {
        return url;
    }

    public String getAuthor_association() {
        return author_association;
    }

    public String getBody() {
        return body;
    }

    public String getId() {
        return id;
    }

    public GithubUser getUser() {
        return user;
    }

    public String toString() {
//        return "body=\'" + body + "\'&user=" + user.toString();
        return String.format("comment(body=\"%s\"&user=%s&id=%s)", body, user.getId(), id);
    }
}
