package com.exam.lamperouge.service;

import com.exam.lamperouge.viewmodel.admin.task.TaskPageRequestVM;
import com.exam.lamperouge.viewmodel.admin.task.TaskRequestVM;
import com.exam.lamperouge.domain.TaskExam;
import com.exam.lamperouge.domain.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TaskExamService extends BaseService<TaskExam> {

    PageInfo<TaskExam> page(TaskPageRequestVM requestVM);

    void edit(TaskRequestVM model, User user);

    TaskRequestVM taskExamToVM(Integer id);

    List<TaskExam> getByGradeLevel(Integer gradeLevel);
}
