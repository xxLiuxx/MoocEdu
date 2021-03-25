import request from '@/utils/request'

const api_prefix = '/eduservice/front/teacher'

export default {
    getTeacherList(page, limit) {
        return request({
            url: `${api_prefix}/getFrontTeacherList/${page}/${limit}`,
            method: 'post'
        })
    },
    getFrontTeacher(id) {
        return request({
            url: `${api_prefix}/getFrontTeacher/${id}`,
            method: 'get'
        })
    }
}
