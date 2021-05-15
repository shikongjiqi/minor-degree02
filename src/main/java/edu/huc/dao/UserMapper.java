package edu.huc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.huc.bean.User;
import edu.huc.common.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select u.user_id as id,u.username as username,u.password as password,u.name as name,u.sex as sex,u.identity as identity,s.classes as classes,s.major_name " +
            "as majorName,s.academy_name as academyName from tb_users u,tb_student s where u.user_id = s.reserved1 and u.user_id = #{userId}")
    UserInfoVo selectUserInfoById(int userId);

    @Select("update tb_users set password = #{newPassword} where user_id = #{userId}")
    void editPassword(String newPassword,int userId);
}