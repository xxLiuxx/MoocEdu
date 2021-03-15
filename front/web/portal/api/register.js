import request from '@/utils/request'

const api_prefix = '/ucenterservice/ucenter-member'

export default {
    sendCode(mobile) {
        return request({
            url: `/edumsm/msm/send/${mobile}`,
            method: 'get'
        })
    },
    submitRegister(registerItem) {
        return request({
            url: `${api_prefix}/register`,
            method: 'post',
            data: registerItem
        })
    }
}
