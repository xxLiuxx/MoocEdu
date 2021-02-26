import request from '@/utils/request'

const api_prefix = '/educms/bannerAdmin'

export default {
  getBanner(pageNumber, limit) {
    return request({
      url: `${api_prefix}/queryBanner/${pageNumber}/${limit}`,
      method: 'get'
    })
  },
  deleteBanner(id) {
    return request({
      url: `${api_prefix}/deleteBanner/${id}`,
      method: 'delete'
    })
  },
  addBanner(banner) {
    return request({
      url: `${api_prefix}/addBanner`,
      method: 'post',
      data: banner
    })
  }
}
