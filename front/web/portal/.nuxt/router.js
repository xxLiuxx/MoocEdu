import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _2588a386 = () => interopDefault(import('../pages/course/index.vue' /* webpackChunkName: "pages/course/index" */))
const _11b5f571 = () => interopDefault(import('../pages/login.vue' /* webpackChunkName: "pages/login" */))
const _77e33c6a = () => interopDefault(import('../pages/register.vue' /* webpackChunkName: "pages/register" */))
const _0da9006d = () => interopDefault(import('../pages/teacher/index.vue' /* webpackChunkName: "pages/teacher/index" */))
const _d8546124 = () => interopDefault(import('../pages/course/_id.vue' /* webpackChunkName: "pages/course/_id" */))
const _0acfe838 = () => interopDefault(import('../pages/orders/_id.vue' /* webpackChunkName: "pages/orders/_id" */))
const _43338bd6 = () => interopDefault(import('../pages/teacher/_id.vue' /* webpackChunkName: "pages/teacher/_id" */))
const _5db9c3e4 = () => interopDefault(import('../pages/video/_vid.vue' /* webpackChunkName: "pages/video/_vid" */))
const _480e525a = () => interopDefault(import('../pages/index.vue' /* webpackChunkName: "pages/index" */))

const emptyFn = () => {}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: '/',
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/course",
    component: _2588a386,
    name: "course"
  }, {
    path: "/login",
    component: _11b5f571,
    name: "login"
  }, {
    path: "/register",
    component: _77e33c6a,
    name: "register"
  }, {
    path: "/teacher",
    component: _0da9006d,
    name: "teacher"
  }, {
    path: "/course/:id",
    component: _d8546124,
    name: "course-id"
  }, {
    path: "/orders/:id?",
    component: _0acfe838,
    name: "orders-id"
  }, {
    path: "/teacher/:id",
    component: _43338bd6,
    name: "teacher-id"
  }, {
    path: "/video/:vid?",
    component: _5db9c3e4,
    name: "video-vid"
  }, {
    path: "/",
    component: _480e525a,
    name: "index"
  }],

  fallback: false
}

export function createRouter (ssrContext, config) {
  const base = (config.app && config.app.basePath) || routerOptions.base
  const router = new Router({ ...routerOptions, base  })

  // TODO: remove in Nuxt 3
  const originalPush = router.push
  router.push = function push (location, onComplete = emptyFn, onAbort) {
    return originalPush.call(this, location, onComplete, onAbort)
  }

  const resolve = router.resolve.bind(router)
  router.resolve = (to, current, append) => {
    if (typeof to === 'string') {
      to = normalizeURL(to)
    }
    return resolve(to, current, append)
  }

  return router
}
