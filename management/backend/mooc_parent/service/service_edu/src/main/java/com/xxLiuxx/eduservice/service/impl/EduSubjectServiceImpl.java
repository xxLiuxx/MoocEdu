package com.xxLiuxx.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.xxLiuxx.eduservice.entity.EduSubject;
import com.xxLiuxx.eduservice.entity.EduSubjectData;
import com.xxLiuxx.eduservice.entity.bo.SubjectBo;
import com.xxLiuxx.eduservice.listener.SubjectListener;
import com.xxLiuxx.eduservice.mapper.EduSubjectMapper;
import com.xxLiuxx.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * subject service implement
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-20
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    private EduSubjectMapper subjectMapper;

    @Override
    public void importSubject(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, EduSubjectData.class, new SubjectListener(this)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * find tree-like subject
     * @return
     */
    @Override
    public List<SubjectBo> findAllSubject() {
        //find all subjects
        List<EduSubject> subjectList = this.subjectMapper.selectList(null);

        List<SubjectBo> parentSubjectBoList = new ArrayList<>();

        //find all parent subjects
        for(EduSubject subject : subjectList) {
            if(StringUtils.equals(subject.getParentId(), "0")) {
                SubjectBo subjectBo = convertSubjectToBo(subject);
                parentSubjectBoList.add(subjectBo);
            }
            parentSubjectBoList.sort(Comparator.comparing(SubjectBo::getSort));
        }

        findSubSubject(subjectList, parentSubjectBoList);

        return parentSubjectBoList;
    }


    /**
     * fina corresponding children of each parent with recursion
     * @param subjectList
     * @param parentSubjectBoList
     */
    private void findSubSubject(List<EduSubject> subjectList, List<SubjectBo> parentSubjectBoList) {
        for(SubjectBo parentSubjectBo : parentSubjectBoList) {
            List<SubjectBo> childrenSubjectBoList = new ArrayList<>();
            //iterate through the list and find the children of each parent
            for(EduSubject subject : subjectList) {
                if(StringUtils.equals(parentSubjectBo.getId(), subject.getParentId())) {
                    SubjectBo childrenSubjectBo = convertSubjectToBo(subject);
                    childrenSubjectBoList.add(childrenSubjectBo);
                }
                childrenSubjectBoList.sort(Comparator.comparing(SubjectBo::getSort));
                parentSubjectBo.setChildren(childrenSubjectBoList);
            }
            //call the recursion on the current children subject
            findSubSubject(subjectList, childrenSubjectBoList);
        }
    }


    private SubjectBo convertSubjectToBo(EduSubject subject) {
        SubjectBo subjectBo = new SubjectBo();
        BeanUtils.copyProperties(subject, subjectBo);
        return subjectBo;
    }

}
