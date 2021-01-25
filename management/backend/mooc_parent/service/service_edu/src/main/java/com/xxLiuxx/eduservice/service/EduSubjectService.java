package com.xxLiuxx.eduservice.service;

import com.xxLiuxx.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxLiuxx.eduservice.entity.bo.SubjectBo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.util.List;

/**
 * <p>
 * subject service
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-20
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * import data from .xlsx with EasyExcel
     * @param file
     */
    void importSubject(MultipartFile file);

    /**
     * find tree-like subject
     * @return
     */
    List<SubjectBo> findAllSubject();

}
