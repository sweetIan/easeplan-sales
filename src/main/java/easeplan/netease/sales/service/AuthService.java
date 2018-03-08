package easeplan.netease.sales.service;

import com.google.common.collect.ImmutableMap;
import easeplan.netease.sales.json.JUser;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Component
public class AuthService implements IAuthService {
    private static Map<String, String> PASSWORD_MAP = ImmutableMap.of(
            "buyer", "reyub",
            "seller", "relles"
    );
    private static Map<String, JUser> USER_MAP = ImmutableMap.of(
            "buyer", new JUser("buyer", "buyer"),
            "seller", new JUser("seller", "seller")
    );

    @Override
    public Optional<JUser> auth(String username, String password) {
        JUser user = null;
        if (PASSWORD_MAP.containsKey(username) && PASSWORD_MAP.get(username).equals(password)) {
            user = USER_MAP.get(username);
        }
        return Optional.ofNullable(user);
    }
}
