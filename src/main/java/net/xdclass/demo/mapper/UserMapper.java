package net.xdclass.demo.mapper;

import net.xdclass.demo.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int save(User user);

    User findByPhoneAndPwd(@Param("phone")String phone, @Param("pwd") String pwd);

    User findUserById(@Param("user_id") Integer userId);
}
