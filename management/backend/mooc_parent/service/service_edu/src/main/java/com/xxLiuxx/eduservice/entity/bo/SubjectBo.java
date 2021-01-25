package com.xxLiuxx.eduservice.entity.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * The subject tree at the front end is like the following
 * [{
 *     id: parentId
 *     title: parentTitle
 *
 *     children: [
 *      {
 *       id: childId
 *       title: childTitle
 *
 *       children:[...]
 *       },
 *       {...}
 *     ]
 * }]
 *
 * @author Yuchen Liu
 */
public class SubjectBo implements Serializable {

    private static final long serialVersionUID = 1L;

    public String id;

    public String title;

    @JsonIgnore
    public int sort;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<SubjectBo> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectBo> getChildren() {
        return children;
    }

    public void setChildren(List<SubjectBo> children) {
        this.children = children;
    }
}
