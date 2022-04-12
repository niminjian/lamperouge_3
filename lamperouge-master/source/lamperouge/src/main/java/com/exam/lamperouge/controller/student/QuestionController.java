package com.exam.lamperouge.controller.student;

import com.exam.lamperouge.base.BaseApiController;
import com.exam.lamperouge.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("StudentQuestionController")
@RequestMapping(value = "/api/student/question")
public class QuestionController extends BaseApiController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
}
