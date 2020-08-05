package net.xdclass.demo.mapper;

import net.xdclass.demo.model.entity.Video;
import net.xdclass.demo.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoMapper {
    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(@Param("video_id") int videoId);

    Video findVideoById(@Param("video_id") int videoId);
}
