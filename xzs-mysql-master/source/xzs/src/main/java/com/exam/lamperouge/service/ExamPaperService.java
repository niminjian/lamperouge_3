package com.exam.lamperouge.service;

import com.exam.lamperouge.domain.ExamPaper;
import com.exam.lamperouge.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.exam.lamperouge.viewmodel.student.dashboard.PaperInfo;
import com.exam.lamperouge.viewmodel.student.exam.ExamPaperPageVM;
import com.exam.lamperouge.domain.User;
import com.exam.lamperouge.viewmodel.admin.exam.ExamPaperPageRequestVM;
import com.exam.lamperouge.viewmodel.student.dashboard.PaperFilter;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExamPaperService extends BaseService<ExamPaper> {

    PageInfo<ExamPaper> page(ExamPaperPageRequestVM requestVM);

    PageInfo<ExamPaper> taskExamPage(ExamPaperPageRequestVM requestVM);

    PageInfo<ExamPaper> studentPage(ExamPaperPageVM requestVM);

    ExamPaper savePaperFromVM(ExamPaperEditRequestVM examPaperEditRequestVM, User user);

    ExamPaperEditRequestVM examPaperToVM(Integer id);

    List<PaperInfo> indexPaper(PaperFilter paperFilter);

    Integer selectAllCount();

    List<Integer> selectMothCount();
}
