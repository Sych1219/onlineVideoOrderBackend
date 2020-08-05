package net.xdclass.demo.service;

import net.xdclass.demo.model.entity.User;

import java.util.Map;

public interface UserService {
  int save(Map<String,String> userInfo);

  String findByPhoneAndPwd(String phone, String pwd);

    User findUserById(Integer userId);
}
