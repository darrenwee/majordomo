package majordomo.github.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubIssue {
    @JsonProperty("number")
    private String number;

    @JsonProperty("id")
    private String id;

    public String getNumber() {
        return number;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return String.format("issue(issue_number=%s&id=%s)", number, id);
    }
}
