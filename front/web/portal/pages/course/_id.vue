<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- course detail start -->
    <section class="container">
      <section class="path-wrap txtOf hLh30">
        <a href="/" title class="c-999 fsize14">Home</a>
        \
        <a href="#" title class="c-999 fsize14">{{ course.subjectLevelOne }}</a>
        \
        <span class="c-333 fsize14">{{ course.subjectLevelTwo }}</span>
      </section>
      <div>
        <article class="c-v-pic-wrap" style="height: 357px;">
          <section class="p-h-video-box" id="videoPlay">
            <img height="100%" width="100%" :src="course.cover" :alt="course.title" class="dis c-v-pic">
          </section>
        </article>
        <aside class="c-attr-wrap">
          <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
              <span class="c-fff fsize24">{{ course.title }}</span>
            </h2>
            <section class="c-attr-jg" v-if="Number(course.price) === 0">
              <span class="c-fff">Price：</span>
              <b class="c-yellow" style="font-size:24px;">Free</b>
            </section>
            <section class="c-attr-jg" v-else>
              <span class="c-fff">Price：</span>
              <b class="c-yellow" style="font-size:24px;">{{ Number(course.price) }}</b>
            </section>
            <section class="c-attr-mt c-attr-undis">
              <span class="c-fff fsize14">Instructor: {{ course.teacherName }}&nbsp;&nbsp;&nbsp;</span>
            </section>
            <section class="c-attr-mt of">
              <span class="ml10 vam">
                <em class="icon18 scIcon"></em>
                <a class="c-fff vam" title="collect" href="#">Collect</a>
              </span>
            </section>
            <section class="c-attr-mt">
              <a href="#" @click="createOrder" title="buy" class="comm-btn c-btn-3">Buy</a>
            </section>
          </section>
        </aside>
        <aside class="thr-attr-box">
          <ol class="thr-attr-ol clearfix">
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">Students</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{ course.buyCount }}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">Lesson Num</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{ course.lessonNum }}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">Views</span>
                <br>
                <h6 class="c-fff f-fM mt10">{{ course.viewCount }}</h6>
              </aside>
            </li>
          </ol>
        </aside>
        <div class="clear"></div>
      </div>
      <!-- course detail -->
      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <div class="i-box">
              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="Course Detail">Course Detail</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>Course Description</span>
                  </h6>
                  <div class="course-txt-body-wrap">
                    <section class="course-txt-body">
                      <p v-html="course.description">
                      </p>
                    </section>
                  </div>
                </div>
                <!-- course intro -->
                <div class="mt50">
                  <h6 class="c-g-content c-infor-title">
                    <span>Syllabus</span>
                  </h6>
                  <section class="mt20">
                    <div class="lh-menu-wrap">
                      <menu id="lh-menu" class="lh-menu mt10 mr10">
                        <ul>
                          <!-- chapter -->
                          <li class="lh-menu-stair" v-for="(chapter, i) in chapterVideo" :key="i">
                            <a href="javascript: void(0)" :title="chapter.title" class="current-1">
                              <em class="lh-menu-i-1 icon18 mr10"></em>{{ chapter.title }}
                            </a>
                            <ol class="lh-menu-ol" style="display: block;">
                              <li class="lh-menu-second ml30" v-for="(video, j) in chapter.videoList" :key="j">
                                <a :href="'/video/' + video.videoSourceId" target="_blank">
                                  <span class="fr">
                                    <i class="free-icon vam mr10">Trail</i>
                                  </span>
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{ video.title }}
                                </a>
                              </li>
                            </ol>
                          </li>
                        </ul>
                      </menu>
                    </div>
                  </section>
                </div>
                <!-- course intro -->
              </article>
            </div>
          </section>
        </article>
        <aside class="fl col-3">
          <div class="i-box">
            <div>
              <section class="c-infor-tabTitle c-tab-title">
                <a title href="/teacher">Instructor</a>
              </section>
              <section class="stud-act-list">
                <ul style="height: auto;">
                  <li>
                    <div class="u-face">
                      <a :href="'/teacher/' + course.teacherId">
                        <img :src="course.avatar" width="50" height="50" alt>
                      </a>
                    </div>
                    <section class="hLh30 txtOf">
                      <a class="c-333 fsize16 fl" :href="'/teacher/' + course.teacherId">{{ course.teacherName }}</a>
                    </section>
                    <section class="hLh20 txtOf">
                      <span class="c-999">{{ course.intro }}</span>
                    </section>
                  </li>
                </ul>
              </section>
            </div>
          </div>
        </aside>
        <div class="clear"></div>
      </div>
    </section>
    <!-- course detail end -->
  </div>
</template>
<script>
import courseApi from '@/api/course'
import orderApi from '@/api/order'
import cookie from 'js-cookie'

export default {
  data() {
    return {
      course: {},
      chapterVideo: [],
      id: ""
    }
  },
  created() {
    this.id = this.$route.params.id
    this.getCourse(this.id)
  },
  methods: {
    getCourse(id) {
      courseApi.getCourseById(id)
          .then(response => {
            this.course = response.data.data.course
            this.chapterVideo = response.data.data.chapterVideo
          })
    },
    createOrder() {
      if (!cookie.get('mooc_member')) {
        this.$router.push({path: '../login'})
      } else {
        orderApi.createOrder(this.id)
            .then(response => {
              this.$router.push({path: '/orders/' + response.data.data.orderNumber})
            })
      }
    }
  }
}
</script>
