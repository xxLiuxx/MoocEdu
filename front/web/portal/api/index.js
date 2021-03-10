import request from '@/utils/request'

const api_prefix = '/eduservice/front'

export default {
    getTeacherAndCourse() {
        return request({
            url: `${api_prefix}/queryTeacherAndCourse`,
            method: 'get'
        })
    },
    getTeacherById(id) {
        return request({
            url: `${api_prefix}/queryTeacherByid/${id}`,
            method: 'get'
        })
    }
}
