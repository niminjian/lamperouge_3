package com.exam.lamperouge.service.impl;

import com.exam.lamperouge.domain.UserFaceInfo;
import com.exam.lamperouge.repository.MybatisUserFaceInfoMapper;
import com.exam.lamperouge.service.UserFaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserFaceInfoServiceImpl implements UserFaceInfoService {


    @Autowired
    private MybatisUserFaceInfoMapper userFaceInfoMapper;

    @Override
    public void insertSelective(UserFaceInfo userFaceInfo) {
        userFaceInfoMapper.insertUserFaceInfo(userFaceInfo);
    }
}
