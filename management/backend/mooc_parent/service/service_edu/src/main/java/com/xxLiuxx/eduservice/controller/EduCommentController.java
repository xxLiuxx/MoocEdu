package com.xxLiuxx.eduservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.entity.EduComment;
import com.xxLiuxx.eduservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * comment controller
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-03-31
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;

    @GetMapping("getAllComment/{page}/{limit}")
    public CommonResult getAllComment(@PathVariable("page") Long page, @PathVariable("limit") Long limit) {
        PageResult<EduComment> commentPageResult = this.commentService.getAllComment(page, limit);
        List<EduComment> commentList = commentPageResult.getItems();
        return CommonResult.success().data("commentList", commentList);
    }

    @PostMapping("addComment")
    public CommonResult addComment(@RequestBody EduComment comment, HttpServletRequest request) {
        int flag = this.commentService.addComment(comment, request);
        if(flag != 1) {
            return CommonResult.error().message("fail to add comment");
        }
        return CommonResult.success().message("comment added");
    }

}

