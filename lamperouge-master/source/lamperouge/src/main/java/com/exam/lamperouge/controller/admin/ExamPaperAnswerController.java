package com.exam.lamperouge.controller.admin;

import com.exam.lamperouge.utility.DateTimeUtil;
import com.exam.lamperouge.utility.ExamUtil;
import com.exam.lamperouge.utility.PageInfoHelper;
import com.exam.lamperouge.viewmodel.admin.paper.ExamPaperAnswerPageRequestVM;
import com.exam.lamperouge.viewmodel.student.exampaper.ExamPaperAnswerPageResponseVM;
import com.exam.lamperouge.base.BaseApiController;
import com.exam.lamperouge.base.RestResponse;
import com.exam.lamperouge.domain.ExamPaperAnswer;
import com.exam.lamperouge.domain.Subject;
import com.exam.lamperouge.domain.User;
import com.github.pagehelper.PageInfo;
import com.exam.lamperouge.service.ExamPaperAnswerService;
import com.exam.lamperouge.service.SubjectService;
import com.exam.lamperouge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("AdminExamPaperAnswerController")
@RequestMapping(value = "/api/admin/examPaperAnswer")
public class ExamPaperAnswerController extends BaseApiController {

    private final ExamPaperAnswerService examPaperAnswerService;
    private final SubjectService subjectService;
    private final UserService userService;

    @Autowired
    public ExamPaperAnswerController(ExamPaperAnswerService examPaperAnswerService, SubjectService subjectService, UserService userService) {
        this.examPaperAnswerService = examPaperAnswerService;
        this.subjectService = subjectService;
        this.userService = userService;
    }


    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamPaperAnswerPageResponseVM>> pageJudgeList(@RequestBody ExamPaperAnswerPageRequestVM model) {
        PageInfo<ExamPaperAnswer> pageInfo = examPaperAnswerService.adminPage(model);
        PageInfo<ExamPaperAnswerPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamPaperAnswerPageResponseVM vm = modelMapper.map(e, ExamPaperAnswerPageResponseVM.class);
            Subject subject = subjectService.selectById(vm.getSubjectId());
            vm.setDoTime(ExamUtil.secondToVM(e.getDoTime()));
            vm.setSystemScore(ExamUtil.scoreToVM(e.getSystemScore()));
            vm.setUserScore(ExamUtil.scoreToVM(e.getUserScore()));
            vm.setPaperScore(ExamUtil.scoreToVM(e.getPaperScore()));
            vm.setSubjectName(subject.getName());
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            User user = userService.selectById(e.getCreateUser());
            vm.setUserName(user.getUserName());
            return vm;
        });
        return RestResponse.ok(page);
    }


}
