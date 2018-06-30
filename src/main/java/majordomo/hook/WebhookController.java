package majordomo.hook;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WebhookController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping(value = "/pr", consumes = "application/json")
    public Map<String, Object> receive(@RequestBody Map<String, Object> payload) {
        JSONObject json = new JSONObject(payload);
        logger.info("Received payload on /pr:\n{}", json.toString(2));
        return payload;
    }
}
