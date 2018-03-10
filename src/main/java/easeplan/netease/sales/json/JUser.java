package easeplan.netease.sales.json;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
@AllArgsConstructor
public class JUser {
    private String username;
    private String nickname;
    private String role;
}
