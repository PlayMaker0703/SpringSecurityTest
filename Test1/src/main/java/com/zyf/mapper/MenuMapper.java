package com.zyf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyf.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("SELECT DISTINCT m.perms " +
            "FROM sys_user_role ur " +
            "LEFT JOIN sys_role r ON ur.role_id = r.id " +
            "LEFT JOIN sys_role_menu rm ON ur.role_id = rm.role_id " +
            "LEFT JOIN sys_menu m ON m.id = rm.menu_id " +
            "WHERE ur.user_id = #{id} " +
            "AND r.status = 0 " +
            "AND m.status = 0")
    List<String> SelectPermsByUserId(@Param("id")Long userId);//查询权限信息
}
