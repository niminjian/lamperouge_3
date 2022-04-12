package com.exam.lamperouge.service;

import com.exam.lamperouge.domain.Message;
import com.exam.lamperouge.domain.MessageUser;
import com.exam.lamperouge.viewmodel.admin.message.MessagePageRequestVM;
import com.exam.lamperouge.viewmodel.student.user.MessageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MessageService {

    List<Message> selectMessageByIds(List<Integer> ids);

    PageInfo<MessageUser> studentPage(MessageRequestVM requestVM);

    PageInfo<Message> page(MessagePageRequestVM requestVM);

    List<MessageUser> selectByMessageIds(List<Integer> ids);

    void sendMessage(Message message, List<MessageUser> messageUsers);

    void read(Integer id);

    Integer unReadCount(Integer userId);

    Message messageDetail(Integer id);
}
