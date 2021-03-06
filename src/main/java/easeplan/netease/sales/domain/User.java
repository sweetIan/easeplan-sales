package easeplan.netease.sales.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/7</pre>
 */
@Data
@AllArgsConstructor
public class User {
    private String username;
    private String nickname;
    private String role;
}
