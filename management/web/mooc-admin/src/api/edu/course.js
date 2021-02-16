import request from '@/utils/request'

const api_prefix = '/eduservice/course'

export default {
  addCourse(courseInfo) {
    return request({
      url: `${api_prefix}/addCourse`,
      method: 'post',
      data: courseInfo
    })
  },
  getCourse(courseId) {
    return request({
      url: `${api_prefix}/getCourseInfo/` + courseId,
      method: 'get'
    })
  },
  updateCourse(courseForm) {
    return request({
      url: `${api_prefix}/updateCourse`,
      method: 'put',
      data: courseForm
    })
  },
  getPublish(courseId) {
    return request({
      url: `${api_prefix}/getFinalPublish/` + courseId,
      method: 'get'
    })
  },
  publishCourse(courseId) {
    return request({
      url: `${api_prefix}/publishCourse/` + courseId,
      method: 'post'
    })
  },
  queryCourse(pageNumber, limit, courseQuery) {
    return request({
      url: `${api_prefix}/queryCourse/${pageNumber}/${limit}`,
      method: 'post',
      data: courseQuery
    })
  },
  deleteCourse(courseId) {
    return request({
      url: `${api_prefix}/${courseId}`,
      method: 'delete'
    })
  }
}
