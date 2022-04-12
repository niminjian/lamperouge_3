package com.exam.lamperouge.repository;

import com.exam.lamperouge.viewmodel.admin.message.MessagePageRequestVM;
import com.exam.lamperouge.domain.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> page(MessagePageRequestVM requestVM);

    List<Message> selectByIds(List<Integer> ids);

    int readAdd(Integer id);
}
