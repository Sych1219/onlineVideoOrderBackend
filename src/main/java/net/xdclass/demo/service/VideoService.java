package net.xdclass.demo.service;

import net.xdclass.demo.model.entity.Video;
import net.xdclass.demo.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {
    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);
}
