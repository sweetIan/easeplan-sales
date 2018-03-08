package easeplan.netease.sales.service;

import easeplan.netease.sales.json.JUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Service
public interface IAuthService {
    Optional<JUser> auth(String username, String password);
}
