import request from '@/utils/request'

const api_prefix = '/eduservice/video'

export default {
  addVideo(video) {
    return request({
      url: `${api_prefix}/addVideo`,
      method: 'post',
      data: video
    })
  },
  getVideo(videoId) {
    return request({
      url: `${api_prefix}/` + videoId,
      method: 'get'
    })
  },
  updateVideo(video) {
    return request({
      url: `${api_prefix}/updateVideo`,
      method: 'put',
      data: video
    })
  },
  deleteVideo(videoId) {
    return request({
      url: `${api_prefix}/` + videoId,
      method: 'delete'
    })
  }
}
