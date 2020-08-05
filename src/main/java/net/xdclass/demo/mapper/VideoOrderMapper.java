package net.xdclass.demo.mapper;

import net.xdclass.demo.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoOrderMapper {
    VideoOrder findByUserIdAndVideoId(@Param("user_id") int userId, @Param("video_id") int videoId);
    int saveOrder(VideoOrder videoOrder);

    List<VideoOrder> listOrderByUserId(@Param("user_id") int userId);
}
