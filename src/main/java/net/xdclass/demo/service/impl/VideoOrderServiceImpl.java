package net.xdclass.demo.service.impl;

import net.xdclass.demo.exception.XDExcetpion;
import net.xdclass.demo.mapper.EpisodeMapper;
import net.xdclass.demo.mapper.PlayOrderMapper;
import net.xdclass.demo.mapper.VideoMapper;
import net.xdclass.demo.mapper.VideoOrderMapper;
import net.xdclass.demo.model.entity.Episode;
import net.xdclass.demo.model.entity.PlayOrder;
import net.xdclass.demo.model.entity.Video;
import net.xdclass.demo.model.entity.VideoOrder;
import net.xdclass.demo.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    VideoOrderMapper videoOrderMapper;

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    EpisodeMapper episodeMapper;

    @Autowired
    PlayOrderMapper playOrderMapper;

    @Transactional
    @Override
    public int save(int userId, int videoId) {
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoId(userId, videoId);
        if (videoOrder != null) {
            return 0;
        }
        Video video = videoMapper.findVideoById(videoId);
        VideoOrder newVideoOrder = new VideoOrder();

        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setVideoId(video.getId());
        newVideoOrder.setVideoTitle(video.getTitle());
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setUserId(userId);

        int rows = videoOrderMapper.saveOrder(newVideoOrder);

        if(rows == 1){
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);
            if(episode == null){
                throw  new XDExcetpion(-1,"视频没有集信息，请运营人员检查");
            }

            PlayOrder playOrder = new PlayOrder();
            playOrder.setUserId(userId);
            playOrder.setVideoId(videoId);
            playOrder.setCurrentNum(episode.getNum());
            playOrder.setEpisodeId(episode.getId());
            playOrder.setCreateTime(new Date());

            playOrderMapper.saveRecord(playOrder);
        }

        return rows;
    }

    @Override
    public List<VideoOrder> listOrderByUserId(int userId) {
        return videoOrderMapper.listOrderByUserId(userId);
    }
}
