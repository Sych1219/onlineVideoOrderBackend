package net.xdclass.demo.controller;

import net.xdclass.demo.model.entity.User;
import net.xdclass.demo.model.request.LoginRequest;
import net.xdclass.demo.service.UserService;
import net.xdclass.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public JsonData register(@RequestBody Map<String, String> userInfo) {
        int rows = userService.save(userInfo);

        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildError("注册失败, 请重试");

    }

    @PostMapping("/login")
    public JsonData login(@RequestBody LoginRequest loginRequest) {

        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(), loginRequest.getPwd());

        return token != null ? JsonData.buildSuccess(token) : JsonData.buildError("用戶名或密碼錯誤");

    }

    @GetMapping("/find_by_token")
    public JsonData findByToken(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        if(userId == null){
            return JsonData.buildError("没有找到用户信息");
        }
        User user = userService.findUserById(userId);

        return JsonData.buildSuccess(user);

    }
}
