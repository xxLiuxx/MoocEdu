package com.xxLiuxx.vodservice.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.xxLiuxx.servicebase.handler.MyException;
import com.xxLiuxx.vodservice.config.VodConfig;
import com.xxLiuxx.vodservice.service.VodService;
import com.xxLiuxx.vodservice.utils.InitVodClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Yuchen Liu
 */

@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(VodConfig.ACCESS_KEY, VodConfig.ACCESS_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = "";
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteVideo(String videoId) {
        try{
            DefaultAcsClient client = InitVodClient.initVodClient(VodConfig.ACCESS_KEY, VodConfig.ACCESS_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        }catch (ClientException e){
            throw new MyException(500, "fail to delete video");
        }
    }

    @Override
    public void deleteBatch(List<String> videoIdList) {
        try{
            DefaultAcsClient client = InitVodClient.initVodClient(VodConfig.ACCESS_KEY, VodConfig.ACCESS_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();

            String ids = StringUtils.join(videoIdList.toArray(), ",");
            request.setVideoIds(ids);
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        }catch (ClientException e){
            throw new MyException(500, "fail to delete video");
        }
    }

    @Override
    public String getVodAuth(String id) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(VodConfig.ACCESS_KEY, VodConfig.ACCESS_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();

            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            String playAuth = response.getPlayAuth();
            return playAuth;
        }catch (ClientException e) {
            throw new MyException(500, "fail to get video auth");
        }
    }
}
