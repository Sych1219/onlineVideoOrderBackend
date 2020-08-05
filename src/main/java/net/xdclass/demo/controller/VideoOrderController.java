package net.xdclass.demo.controller;

import net.xdclass.demo.model.entity.VideoOrder;
import net.xdclass.demo.model.request.VideoOrderRequest;
import net.xdclass.demo.service.VideoOrderService;
import net.xdclass.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {

    @Autowired
    VideoOrderService videoOrderService;

    @PostMapping("/save")
    public JsonData save(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request){

        int userId = (int) request.getAttribute("user_id");
        int videoId = videoOrderRequest.getVideoId();

        int rows = videoOrderService.save(userId, videoId);

        return rows==1? JsonData.buildSuccess():JsonData.buildError("下单失败");
    }

    @GetMapping("/list")
    public JsonData list(HttpServletRequest request){

        int userId = (int) request.getAttribute("user_id");

        List<VideoOrder> videoOrderList = videoOrderService.listOrderByUserId(userId);

        return JsonData.buildSuccess(videoOrderList);

    }
}
