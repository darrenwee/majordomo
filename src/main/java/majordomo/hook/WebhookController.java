package majordomo.hook;

import majordomo.controller.Controller;
import majordomo.github.event.GithubIssueCommentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private Controller controller;

    public WebhookController(Controller controller) {
        this.controller = controller;
    }

    @PostMapping(value = "/pr", consumes = "application/json")
    public GithubIssueCommentEvent receive(@RequestBody GithubIssueCommentEvent payload) {
        logger.info("Received payload on /pr: {}", payload.toString());
        controller.run(payload);
        return payload;
    }
}
