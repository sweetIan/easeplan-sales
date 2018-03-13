package easeplan.netease.sales.controller;

import easeplan.netease.sales.service.IAuthService;
import easeplan.netease.sales.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片上传
 *
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/13</pre>
 */
@Controller
public class PictureController {
    @Autowired
    IAuthService authService;
    @Autowired
    IStorageService storageService;

    /**
     * 获取图片
     *
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/pics/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        Resource file = storageService.loadAsResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    /**
     * 图片上传
     *
     * @param file
     * @param identity
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/api/pics", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @CookieValue(value = "identity", required = false) String identity,
            HttpServletResponse response
    ) throws IOException {
        if (authService.isSeller(identity)) {
            String filename = storageService.store(file);
            return "/pics/" + filename;
        } else {
            response.sendError(401);
            return null;
        }
    }
}
