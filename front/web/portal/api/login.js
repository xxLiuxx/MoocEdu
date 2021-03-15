import request from '@/utils/request'

const api_prefix = '/ucenterservice/ucenter-member'

export default {
    submitLogin(userInfo) {
        return request({
            url: `${api_prefix}/login`,
            method: 'post',
            data: userInfo
        })
    },
    getLoginInfo() {
        return request({
            url: `${api_prefix}/auth`,
            method: 'get'
        })
    }
}
