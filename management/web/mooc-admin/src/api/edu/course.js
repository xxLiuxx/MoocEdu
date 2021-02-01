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
  }
}
