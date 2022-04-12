package com.exam.lamperouge.repository;

import com.exam.lamperouge.viewmodel.student.user.MessageRequestVM;
import com.exam.lamperouge.domain.MessageUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageUserMapper extends BaseMapper<MessageUser> {

    List<MessageUser> selectByMessageIds(List<Integer> ids);

    int inserts(List<MessageUser> list);

    List<MessageUser> studentPage(MessageRequestVM requestVM);

    Integer unReadCount(Integer userId);
}
