package easeplan.netease.sales.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/13</pre>
 */
@Service
public interface IStorageService {
    void init();
    String store(MultipartFile file);
    Resource loadAsResource(String filename);
}
