package com.exam.lamperouge.repository;

import com.exam.lamperouge.domain.UserToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {
    UserToken getToken(String token);
}
