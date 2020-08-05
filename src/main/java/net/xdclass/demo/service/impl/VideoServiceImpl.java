package net.xdclass.demo.service.impl;

import net.xdclass.demo.model.entity.Video;
import net.xdclass.demo.model.entity.VideoBanner;
import net.xdclass.demo.mapper.VideoMapper;
import net.xdclass.demo.service.VideoService;
import net.xdclass.demo.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.xdclass.demo.config.CacheKeyMannager.*;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    BaseCache baseCache;

    @Override
    public List<Video> listVideo() {

        try {
            Object cahceObject = baseCache.getTenMinuteCache().get(INDEX_VIDEO_KEY, () -> {
                System.out.println("vido list 查询数据库");
                return videoMapper.listVideo();
            });

            if (cahceObject instanceof List) {
                List<Video> videoList = (List<Video>) cahceObject;
                return videoList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return videoMapper.listVideo();
    }

    @Override
    public List<VideoBanner> listBanner() {
        try {
            Object cacheObjcet = baseCache.getTenMinuteCache().get(INDEX_BANNER_KEY, () -> {
                System.out.println("banner list 查询数据库");
                List<VideoBanner> videoBanners = videoMapper.listBanner();
                return videoBanners;
            });

            if (cacheObjcet instanceof List) {
                List<VideoBanner> videoBanners = (List<VideoBanner>) cacheObjcet;
                return videoBanners;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Video findDetailById(int videoId) {

        String videoCachKey = String.format(INDEX_DETAIL_KEY, videoId);

        try {
            Object cacheObject = baseCache.getOneHourCache().get(videoCachKey, () -> {
                System.out.println("数据库三表联查");
                Video video = videoMapper.findDetailById(videoId);
                return video;
            });

            if (cacheObject instanceof Video) {
                Video video= (Video) cacheObject;
                return video;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
