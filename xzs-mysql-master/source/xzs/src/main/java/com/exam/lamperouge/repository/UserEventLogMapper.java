package com.exam.lamperouge.repository;

import com.exam.lamperouge.viewmodel.admin.user.UserEventPageRequestVM;
import com.exam.lamperouge.domain.UserEventLog;
import com.exam.lamperouge.domain.other.KeyValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserEventLogMapper extends BaseMapper<UserEventLog> {

    List<UserEventLog> getUserEventLogByUserId(Integer id);

    List<UserEventLog> page(UserEventPageRequestVM requestVM);

    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
