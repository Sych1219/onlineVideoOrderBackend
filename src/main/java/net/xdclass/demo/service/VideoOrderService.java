package net.xdclass.demo.service;

import net.xdclass.demo.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {

    int save(int userId, int videoId);

    List<VideoOrder> listOrderByUserId(int userId);
}
