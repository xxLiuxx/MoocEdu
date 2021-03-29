import request from '@/utils/request'

const api_prefix = '/eduservice/front/course'

export default {
    getCourseList(page, limit, courseQuery) {
        return request({
            url: `${api_prefix}/getFrontCourseList/${page}/${limit}`,
            method: 'post',
            data: courseQuery
        })
    },
    getAllSubject() {
        return request({
            url: `/eduservice/subject/findAll`,
            method: 'get'
        })
    },
    getCourseById(id) {
        return request({
            url: `${api_prefix}/getFrontCourse/${id}`,
            method: 'get'
        })
    }
}
