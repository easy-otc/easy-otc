package com.easytech.otc.mvc.controller.api;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.easytech.otc.common.DateFormatTool;
import com.easytech.otc.common.FileUploadUtil;
import com.easytech.otc.mvc.controller.WebConst;
import com.easytech.otc.mvc.protocol.ACL;
import com.easytech.otc.mvc.protocol.Resp;
import com.easytech.otc.mvc.protocol.RetCodeEnum;
import com.easytech.otc.mvc.vo.PictureVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 图片上传
 */
@Slf4j
@RestController
@RequestMapping(WebConst.API_V1_PREFIX + "upload")
public class FileUploadController {

    private static final String[] IMAGE_TYPE     = new String[] { ".bmp", ".jpg", ".jpeg", ".gif", ".png" };

    // 文件名前缀
    public static final String    UPLOAD_TAG     = "APP_";

    // 文件大小限制 5*1024*1024
    private static final int      MAX_UPLOADSIZE = 5242880;

    /**
     * 图片上传
     *
     * @param uid
     * @param uploadFile
     * @return
     */
    @PostMapping(value = "/pic/{uid}")
    @ACL(authTagKey = "uid")
    public Resp<PictureVo> upload(@PathVariable("uid") Integer uid, @RequestParam("uploadFile") MultipartFile uploadFile) {

        Resp<PictureVo> response = new Resp<>();
        PictureVo pic = new PictureVo();

        // 图片不存在
        if (uploadFile == null) {
            response.setFail(RetCodeEnum.ILLEGAL_ARGUMENT.getCode(), "图片不存在");
            return response;
        }

        // 验证图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }
        if (!isLegal) {
            response.setFail(RetCodeEnum.ILLEGAL_ARGUMENT.getCode(), "非法的图片格式");
            return response;
        }

        // 图片大小检验
        if (uploadFile.getSize() > MAX_UPLOADSIZE) {
            response.setFail(RetCodeEnum.ILLEGAL_ARGUMENT.getCode(), "图片大小超出5M限制");
            return response;
        }

        // 校验图片是否合法
        isLegal = false;
        try {
            BufferedImage img = ImageIO.read(uploadFile.getInputStream());
            if (img != null) {
                isLegal = true;
            }
        } catch (Exception e) {
        }

        if (!isLegal) {
            response.setFail(RetCodeEnum.ILLEGAL_ARGUMENT.getCode(), "非法的图片内容");
            return response;
        }

        try {

            String suffix = StringUtils.substring(uploadFile.getOriginalFilename(), uploadFile.getOriginalFilename().lastIndexOf("."));
            // TODO 这里的key有极小的重复概率
            String key = UPLOAD_TAG + DateFormatTool.formatDate2MilliSecond(new Date()) + uid + suffix;

            // TODO 上传逻辑
            FileUploadUtil.uploadPicture(uploadFile, null, key);

        } catch (Exception e) {
            String msg = "图片上传失败";
            LOGGER.error(msg, e);
            response.setFail(RetCodeEnum.FAIL.getCode(), msg);
            return response;
        }

        response.setSuccess().setData(pic);
        return response;
    }
}