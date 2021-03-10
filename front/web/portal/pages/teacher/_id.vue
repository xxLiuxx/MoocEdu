<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- instructor begin -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">Instructor Description</span>
        </h2>
      </header>
      <div class="t-infor-wrap">
        <!-- Basic Info -->
        <section class="fl t-infor-box c-desc-content">
          <div class="mt20 ml20">
            <section class="t-infor-pic">
              <img :src="teacher.avatar">
            </section>
            <h3 class="hLh30">
              <span class="fsize24 c-333">{{teacher.name}}</span>
            </h3>
            <section class="mt10">
              <span class="t-tag-bg">{{teacher.career}}</span>
            </section>
            <section class="t-infor-txt">
              <p class="mt20">{{teacher.intro}}</p>
            </section>
            <div class="clear"></div>
          </div>
        </section>
        <div class="clear"></div>
      </div>
      <section class="mt30">
        <div>
          <header class="comm-title all-teacher-title c-course-content">
            <h2 class="fl tac">
              <span class="c-333">Courses</span>
            </h2>
            <section class="c-tab-title">
              <a href="javascript: void(0)">&nbsp;</a>
            </section>
          </header>
          <!-- /no data begin-->
          <section class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">Now data now, we are working on this...</span>
          </section>
          <!-- /nodata end-->
          <article class="comm-course-list">
            <ul class="of">
              <li>
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img src="~/assets/photo/course/1442295455437.jpg" class="img-responsive" >
                    <div class="cc-mask">
                      <a href="#" title="开始学习" target="_blank" class="comm-btn c-btn-1">开始学习</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a href="#" title="零基础入门学习Python课程学习" target="_blank" class="course-title fsize18 c-333">零基础入门学习Python课程学习</a>
                  </h3>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
      </section>
    </section>
    <!-- /instructor end -->
  </div>
</template>
<script>
import indexApi from '@/api/index'
export default {
  data() {
    return {
      teacher: '',
      id: ''
    }
  },
  created() {
    this.id = this.$route.params.id
    this.getTeacherById()
  },
  methods: {
    getTeacherById() {
      indexApi.getTeacherById(this.id)
        .then(response => {
          this.teacher = response.data.data.teacher
        })
    }
  }
}
</script>
