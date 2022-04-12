package com.exam.lamperouge.viewmodel.admin.paper;

import com.exam.lamperouge.base.BasePage;

public class ExamPaperAnswerPageRequestVM extends BasePage {
    private Integer subjectId;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
