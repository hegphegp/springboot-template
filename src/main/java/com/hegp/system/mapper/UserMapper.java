package com.hegp.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hegp.system.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
