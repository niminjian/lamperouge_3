package com.exam.lamperouge.controller.student;

import com.exam.lamperouge.utility.DateTimeUtil;
import com.exam.lamperouge.utility.HtmlUtil;
import com.exam.lamperouge.utility.JsonUtil;
import com.exam.lamperouge.utility.PageInfoHelper;
import com.exam.lamperouge.viewmodel.admin.question.QuestionEditRequestVM;
import com.exam.lamperouge.viewmodel.student.exam.ExamPaperSubmitItemVM;
import com.exam.lamperouge.viewmodel.student.question.answer.QuestionAnswerVM;
import com.exam.lamperouge.viewmodel.student.question.answer.QuestionPageStudentRequestVM;
import com.exam.lamperouge.viewmodel.student.question.answer.QuestionPageStudentResponseVM;
import com.exam.lamperouge.base.BaseApiController;
import com.exam.lamperouge.base.RestResponse;
import com.exam.lamperouge.domain.ExamPaperQuestionCustomerAnswer;
import com.exam.lamperouge.domain.Subject;
import com.exam.lamperouge.domain.TextContent;
import com.exam.lamperouge.domain.question.QuestionObject;
import com.exam.lamperouge.service.ExamPaperQuestionCustomerAnswerService;
import com.exam.lamperouge.service.QuestionService;
import com.exam.lamperouge.service.SubjectService;
import com.exam.lamperouge.service.TextContentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("StudentQuestionAnswerController")
@RequestMapping(value = "/api/student/question/answer")
public class QuestionAnswerController extends BaseApiController {

    private final ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService;
    private final QuestionService questionService;
    private final TextContentService textContentService;
    private final SubjectService subjectService;

    @Autowired
    public QuestionAnswerController(ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService, QuestionService questionService, TextContentService textContentService, SubjectService subjectService) {
        this.examPaperQuestionCustomerAnswerService = examPaperQuestionCustomerAnswerService;
        this.questionService = questionService;
        this.textContentService = textContentService;
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<QuestionPageStudentResponseVM>> pageList(@RequestBody QuestionPageStudentRequestVM model) {
        model.setCreateUser(getCurrentUser().getId());
        PageInfo<ExamPaperQuestionCustomerAnswer> pageInfo = examPaperQuestionCustomerAnswerService.studentPage(model);
        PageInfo<QuestionPageStudentResponseVM> page = PageInfoHelper.copyMap(pageInfo, q -> {
            Subject subject = subjectService.selectById(q.getSubjectId());
            QuestionPageStudentResponseVM vm = modelMapper.map(q, QuestionPageStudentResponseVM.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(q.getCreateTime()));
            TextContent textContent = textContentService.selectById(q.getQuestionTextContentId());
            QuestionObject questionObject = JsonUtil.toJsonObject(textContent.getContent(), QuestionObject.class);
            String clearHtml = HtmlUtil.clear(questionObject.getTitleContent());
            vm.setShortTitle(clearHtml);
            vm.setSubjectName(subject.getName());
            return vm;
        });
        return RestResponse.ok(page);
    }


    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<QuestionAnswerVM> select(@PathVariable Integer id) {
        QuestionAnswerVM vm = new QuestionAnswerVM();
        ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer = examPaperQuestionCustomerAnswerService.selectById(id);
        ExamPaperSubmitItemVM questionAnswerVM = examPaperQuestionCustomerAnswerService.examPaperQuestionCustomerAnswerToVM(examPaperQuestionCustomerAnswer);
        QuestionEditRequestVM questionVM = questionService.getQuestionEditRequestVM(examPaperQuestionCustomerAnswer.getQuestionId());
        vm.setQuestionVM(questionVM);
        vm.setQuestionAnswerVM(questionAnswerVM);
        return RestResponse.ok(vm);
    }

}
