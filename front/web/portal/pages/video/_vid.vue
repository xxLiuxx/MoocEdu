<template>
  <div>
    <link rel="stylesheet" href="https://g.alicdn.com/de/prismplayer/2.9.3/skins/default/aliplayer-min.css" />
    <script type="text/javascript" charset="utf-8" src="https://g.alicdn.com/de/prismplayer/2.9.3/aliplayer-min.js"></script>
    <div id="J_prismPlayer" class="prism-player"></div>
  </div>
</template>

<script>
import videoApi from '@/api/video'
export default {
  layout: 'index',
  asyncData({ params, error }) {
    return videoApi.getPlayAuth(params.vid).then(response => {
      return {
        vid: params.vid,
        playAuth: response.data.data.playAuth
      }
    })
  },
  mounted() {
    new Aliplayer({
      id: 'J_prismPlayer',
      vid: this.vid,
      playauth: this.playAuth,
      encryptType: '1',
      width: '100%',
      height: '500px',
      autoplay: false
    }, function(player) {
      console.log('player created')
    })
  }
}
</script>
<style scoped>

</style>
