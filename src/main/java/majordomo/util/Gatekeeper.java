package majordomo.util;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class Gatekeeper {
    @Resource(name = "blacklist")
    private Set<String> blacklist;

    @Resource(name = "committers")
    private Set<String> committers;

    @Resource(name = "seniordevs")
    private Set<String> seniordevs;

    @Resource(name = "owners")
    private Set<String> owners;

    private Gatekeeper() {

    }

    public boolean isBlacklisted(String username) {
        return blacklist.contains(username);
    }

    public boolean isCommitters(String username) {
        return committers.contains(username) || isSeniorDev(username);
    }

    public boolean isSeniorDev(String username) {
        return seniordevs.contains(username) || isOwner(username);
    }

    public boolean isOwner(String username) {
        return owners.contains(username);
    }
}
