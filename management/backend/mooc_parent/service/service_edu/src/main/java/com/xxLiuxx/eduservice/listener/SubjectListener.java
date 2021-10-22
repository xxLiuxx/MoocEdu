package com.xxLiuxx.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxLiuxx.eduservice.entity.EduSubject;
import com.xxLiuxx.eduservice.entity.EduSubjectData;
import com.xxLiuxx.eduservice.service.EduSubjectService;
import com.xxLiuxx.servicebase.handler.MyException;

/**
 * The EasyExcel is used to read data from .xlsx and import them to database
 * This class extends the Listener in the EasyExcel and implements the business logic
 * @author Yuchen Liu
 */
public class SubjectListener extends AnalysisEventListener<EduSubjectData> {
    // the AnalysisEventListener object is not managed by spring
    // so we can not use beans managed by spring such as subjectService in this class
    // since the mapper is managed by the spring as well, we are not able to operate the database either
    // so we need to pass the object from the class we need to this class

    public EduSubjectService subjectService;

    public SubjectListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(EduSubjectData data, AnalysisContext analysisContext) {
        if (data == null) {
            throw new MyException(500, "import failure! Empty file!");
        }

        // check if the current subject is category one and exist in the database
        EduSubject subjectOne = checkCategoryOneExist(subjectService, data.getSubjectOneTitle());
        if(subjectOne == null) {
            subjectOne = new EduSubject();
            subjectOne.setTitle(data.getSubjectOneTitle());
            subjectOne.setParentId("0");
            this.subjectService.save(subjectOne);
        }

        // check if the current subject is category two and exist in the database
        EduSubject subjectTwo = checkCategoryTwoExist(subjectService, data.getSubjectTwoTitle(), subjectOne.getId());
        if(subjectTwo == null) {
            subjectTwo = new EduSubject();
            subjectTwo.setTitle(data.getSubjectTwoTitle());
            subjectTwo.setParentId(subjectOne.getId());
            this.subjectService.save(subjectTwo);
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    private EduSubject checkCategoryOneExist(EduSubjectService eduSubjectService, String title) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        wrapper.eq("parent_id", "0");
        return eduSubjectService.getOne(wrapper);
    }

    private EduSubject checkCategoryTwoExist(EduSubjectService eduSubjectService, String title, String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        wrapper.eq("parent_id", parentId);
        return eduSubjectService.getOne(wrapper);
    }
}
