import request from '@/utils/request'

const api_prefix = '/eduservice/teacher'

export default {
  findAll() {
    return request({
      url: `${api_prefix}/findAll`,
      method: 'get'
    })
  },
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
  },

  getTeacher(id) {
    return request({
      url: `${api_prefix}/${id}`,
      method: 'get'
    })
  },

  updateTeacher(id, teacher) {
    return request({
      url: `${api_prefix}/${id}`,
      method: 'put',
      data: teacher
    })
  }
}
