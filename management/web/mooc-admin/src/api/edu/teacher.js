import request from '@/utils/request'

const api_prefix = '/eduservice/teacher'

export default {
  getTeacherList(pageNumber, limit, teacherQuery) {
    return request({
      url: `${api_prefix}/pageTeacher/${pageNumber}/${limit}`,
      method: 'post',
      // "data" transforms object into json object so that the backend can user RequestBody to accept params
      data: teacherQuery
    })
  },

  deleteTeacher(id) {
    return request({
      url: `${api_prefix}/${id}`,
      method: 'delete'
    })
  },

  addTeacher(teacher) {
    return request({
      url: `${api_prefix}/addTeacher`,
      method: 'post',
      data: teacher
    })
  }
}
