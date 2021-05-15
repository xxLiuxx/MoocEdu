import request from '@/utils/request'

const api_prefix = '/orderservice/order'

export default {
    createOrder(courseId) {
        return request({
            url: `${api_prefix}/createOrder/${courseId}`,
            method: 'post'
        })
    },
    getOrderInfo(orderNo) {
        return request({
            url: `${api_prefix}/getOrder/${orderNo}`,
            method: 'get'
        })
    },
    createCode(orderNo) {
        return request({
            url: `/orderservice/paylog/createCode/${orderNo}`,
            method: 'get'
        })
    },
    checkStatus(orderNo) {
        return request({
            url: `/orderservice/paylog/checkStatus/${orderNo}`,
            method: 'get'
        })
    }
}
