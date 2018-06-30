package majordomo.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="webhook", method=RequestMethod.POST)
public class WebhookController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping
    public String githubWebhookEndpoint(@RequestParam("body") String event) {
        logger.info(event);
        return event;
    }
}
