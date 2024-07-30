package com.example.mapper;

import com.example.pojo.User;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.StringReader;

public interface UserMapper {
    //新增用户
    @Insert("INSERT INTO usertable (email, password, salt, confirm_code, activation_time, is_valid)"+
            "VALUES ( #{email}, #{password}, #{salt}, #{confirmCode}, #{activationTime}, #{isValid})")
    int insertUser(User user);

    //根据确认码查询用户
    @Select("SELECT email, activation_time FROM usertable WHERE confirm_code = #{confirmCode} AND is_valid = 0")
    User selectUserByConfirmCode(@Param("confirmCode") String confirmCode);

    //根据确认码查询用户并修改状态置为1
    @Update("UPDATE usertable SET is_valid = 1 WHERE confirm_code = #{confirmCode}")
    int updateUserByConfirmCode(@Param("confirmCode") String confirmCode);

    //根据邮箱查询用户
    @Select("SELECT email, password, salt FROM usertable WHERE email = #{email} AND is_valid = 1")
    List<User> selectUserByEmail(@Param("email") String email);

    @Select("SELECT id, email, password FROM usertable WHERE id = #{id}")
    User selectById(@Param("id") int id);
}
