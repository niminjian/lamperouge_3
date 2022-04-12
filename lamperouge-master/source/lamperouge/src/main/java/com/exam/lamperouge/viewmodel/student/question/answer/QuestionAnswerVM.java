package com.exam.lamperouge.viewmodel.student.question.answer;

import com.exam.lamperouge.viewmodel.admin.question.QuestionEditRequestVM;
import com.exam.lamperouge.viewmodel.student.exam.ExamPaperSubmitItemVM;

public class QuestionAnswerVM {
    private QuestionEditRequestVM questionVM;
    private ExamPaperSubmitItemVM questionAnswerVM;

    public QuestionEditRequestVM getQuestionVM() {
        return questionVM;
    }

    public void setQuestionVM(QuestionEditRequestVM questionVM) {
        this.questionVM = questionVM;
    }

    public ExamPaperSubmitItemVM getQuestionAnswerVM() {
        return questionAnswerVM;
    }

    public void setQuestionAnswerVM(ExamPaperSubmitItemVM questionAnswerVM) {
        this.questionAnswerVM = questionAnswerVM;
    }
}
