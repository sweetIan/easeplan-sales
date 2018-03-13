package easeplan.netease.sales.service.implementation;

import easeplan.netease.sales.exception.StorageException;
import easeplan.netease.sales.service.IStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/13</pre>
 */
@Component
public class StorageService implements IStorageService {
    private static String RANDOM_CANDIDATES = "0123456789QWERTYUIOPASDFGHJKLZXCVBNM";
    private static int RANDOM_SIZE = RANDOM_CANDIDATES.length();
    private static int RANDOM_LENGTH = 8;
    private final Path rootLocation = Paths.get("./pics/");

    private static String RANDOM_FILENAME() {
        StringBuffer tmp = new StringBuffer();
        int i = 0;
        while (i < RANDOM_LENGTH) {
            tmp.append(RANDOM_CANDIDATES.charAt((int) Math.ceil(Math.random() * 100000000) % RANDOM_SIZE));
            i++;
        }
        return tmp.toString();
    }

    @Override
    public String store(MultipartFile file) {
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("不能上传空文件！");
            }
            if (file.getSize() > 1024 * 1024) {
                throw new StorageException("上传失败，文件不能大于1M！");
            }
            String filename = RANDOM_FILENAME() + "." + extension;
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new StorageException("服务器错误，上传失败！");
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException("无法读取图片！");
            }
        } catch (MalformedURLException e) {
            throw new StorageException("无法读取图片！", e);
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Failed to initialize picture storage path.", e);
        }
    }
}
