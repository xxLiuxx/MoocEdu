module.exports = {
  /*
  ** Headers of the page
  */
  head: {
    title: 'Mooc Edu -- Learn the latest CS technologies!',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: 'Mooc Edu is an online platform that provides a variety number of latest CS technologies courses.' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },
  // plugins
  plugins: [
    { src: '~/plugins/nuxt-swiper-plugin.js', ssr: false }
  ],
  css: [
    // 'swiper/dist/css/swiper.css'
  ],

  /*
  ** Customize the progress bar color
  */
  loading: { color: '#3B8070' },
  /*
  ** Build configuration
  */
  build: {
  }
}

