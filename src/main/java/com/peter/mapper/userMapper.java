package com.peter.mapper;

import com.peter.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


// 这是mybatis下的mapper，所以要加mapper注解
// 这也是dao层的所以要加@repo
@Mapper
@Repository
public interface userMapper {

    User queryUserByName(String name);
}
