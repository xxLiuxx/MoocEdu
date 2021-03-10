<template>

  <div>
    <!-- banner start -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <div v-for="(banner, index) in bannerList" :key="index" class="swiper-slide" style="background: #040B1B;">
          <a target="_blank" :href="banner.linkUrl">
            <img :src="banner.imageUrl" :alt="banner.title">
          </a>
        </div>
      </div>
      <div class="swiper-pagination swiper-pagination-white"></div>
      <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
      <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
    </div>
    <!-- banner end -->

    <div id="aCoursesList">
      <!-- Course start -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">Hot</span>
            </h2>
          </header>
          <div>
            <article class="comm-course-list">
              <ul class="of" id="bna">
                <li v-for="(course, index) in courseList" :key="index">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img
                          :src="course.cover"
                          class="img-responsive"
                          :alt="course.title"
                      >
                      <div class="cc-mask">
                        <a href="#" title="Start" class="comm-btn c-btn-1">Start</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a href="#" :title="course.title" class="course-title fsize18 c-333">{{course.title}}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <span class="fr jgTag bg-green" v-if="Number(course.price) === 0">
                        <i class="c-fff fsize12 f-fA">free</i>
                      </span>
                      <span class="fr jgTag bg-green" v-else>
                        <i class="c-fff fsize12 f-fA">${{course.price}}</i>
                      </span>
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">9634 students</i>
                        |
                        <i class="c-999 f-fA">9634 reviews</i>
                      </span>
                    </section>
                  </div>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="#" title="All Courses" class="comm-btn c-btn-2">All Courses</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /Course end -->
      <!-- Instructor start -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">Famous Instructor</span>
            </h2>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
                <li v-for="(teacher, index) in teacherList" :key="index">
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <a href="/teacher/1" :title="teacher.name">
                        <img :alt="teacher.name" :src="teacher.avatar">
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a href="/teacher/1" :title="teacher.name" class="fsize18 c-666">{{teacher.name}}</a>
                    </div>
                    <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999">{{teacher.career}}</span>
                    </div>
                    <div class="mt15 i-q-txt">
                      <p class="c-999 f-fA">{{teacher.intro}}</p>
                    </div>
                  </section>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="#" title="All Instructors" class="comm-btn c-btn-2">All Instructors</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /Instructor end -->
    </div>
  </div>
</template>

<script>
import bannerApi from '@/api/banner'
import indexApi from '@/api/index'
export default {
  data () {
    return {
      bannerList: [],
      courseList: [],
      teacherList: [],
      swiperOption: {
        // pagination
        pagination: {
          el: '.swiper-pagination'
        },
        // navigation
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        }
      }
    }
  },
  created() {
    this.queryBanner()
    this.queryTeacherAndCourse()
  },
  methods: {
    queryBanner() {
      bannerApi.queryBanner()
        .then(response => {
          this.bannerList = response.data.data.bannerList
        })
    },
    queryTeacherAndCourse() {
      indexApi.getTeacherAndCourse()
        .then(response => {
          this.courseList = response.data.data.courseList
          this.teacherList = response.data.data.teacherList
        })
    }
  }
}
</script>
