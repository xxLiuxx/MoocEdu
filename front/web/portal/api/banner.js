import request from '@/utils/request'

const api_prefix = '/educms/bannerFront'

export default {
    queryBanner() {
        return request({
            url: `${api_prefix}/queryAllBanner`,
            method: 'get'
        })
    }
}
