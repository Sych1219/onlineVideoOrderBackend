package net.xdclass.demo.controller;

import net.xdclass.demo.model.entity.Video;
import net.xdclass.demo.model.entity.VideoBanner;
import net.xdclass.demo.service.VideoService;
import net.xdclass.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    @RequestMapping("/list_banner")
    public JsonData listBanner(){
        List<VideoBanner> list = videoService.listBanner();
        return JsonData.buildSuccess(list);
    }

    @RequestMapping("/list")
    public JsonData listVideo(){
        List<Video> list = videoService.listVideo();
        return JsonData.buildSuccess(list);
    }

    @RequestMapping("/find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id",required = true) int videoId){
        Video video = videoService.findDetailById(videoId);
        return JsonData.buildSuccess(video);
    }

}
