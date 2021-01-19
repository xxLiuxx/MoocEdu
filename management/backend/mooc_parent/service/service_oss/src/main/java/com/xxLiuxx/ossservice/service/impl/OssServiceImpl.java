package com.xxLiuxx.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.xxLiuxx.ossservice.config.OssConfig;
import com.xxLiuxx.ossservice.service.OssService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author Yuchen Liu
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String fileUpload(MultipartFile file) {
        String endpoint = OssConfig.ENDPOINT;
        String bucket = OssConfig.BUCKET;
        String accessKeyId = OssConfig.ACCESS_KEY;
        String accessKeySecret = OssConfig.ACCESS_SECRET;

        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();

            //ensure the unique filename
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String fileName = uuid + file.getOriginalFilename();

            String datePath = new DateTime().toString("yyyy/MM/dd");

            fileName = datePath + "/" + fileName;

            ossClient.putObject(bucket,fileName, inputStream);
            String url = "http://" + bucket + "." + endpoint + "/" + fileName;
            ossClient.shutdown();
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
