package majordomo.github.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubUser {
    @JsonProperty("login")
    private String login;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    public String getType() {
        return type;
    }

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return String.format("user(login=%s&id=%s&type=%s)", login, id, type);
    }
}
