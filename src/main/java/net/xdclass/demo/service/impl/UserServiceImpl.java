package net.xdclass.demo.service.impl;

import net.xdclass.demo.model.entity.User;

import net.xdclass.demo.mapper.UserMapper;
import net.xdclass.demo.service.UserService;
import net.xdclass.demo.utils.CommonUtils;
import net.xdclass.demo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import static net.xdclass.demo.utils.CommonUtils.MD5;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(Integer userId) {
        User user = userMapper.findUserById(userId);
        user.setPwd("");
        return user;
    }

    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
       User user =  userMapper.findByPhoneAndPwd(phone, CommonUtils.MD5(pwd));
       if(user !=null){
           String token = JWTUtils.getWebToken(user);
           return token;
       }else {
           return null;
       }

    }

    @Override
    public int save(Map<String, String> userInfo) {

        User user = parseToUser(userInfo);
        if (user != null) {
            return userMapper.save(user);
        } else {
            return -1;
        }
    }


    private User parseToUser(Map<String, String> userInfo){
        if(userInfo.containsKey("name")&& userInfo.containsKey("pwd") && userInfo.containsKey("phone")){
            User user = new User();
            user.setName(userInfo.get("name"));
            String pwd = userInfo.get("pwd");
            user.setPwd(MD5(pwd));
            user.setPhone(userInfo.get("phone"));
            user.setHeadImg(getRandomImg());
            user.setCreateTime(new Date());
            return user;
        }else {
            return null;
        }
    }

    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRandomImg(){
        int size = headImg.length;

        Random random = new Random();
        int index = random.nextInt(size);

        return headImg[index];

    }


}
