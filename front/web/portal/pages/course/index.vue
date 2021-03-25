<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /course list start -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">All courses</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">Category</span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li v-for="(subjectOne, subjectOneIndex) in subjectOneList" :key="subjectOneIndex" :class="{active: oneIndex === subjectOneIndex}">
                  <a :title="subjectOne.title" href="#" @click="getSubjectTwoList(subjectOne.id, subjectOneIndex)">{{ subjectOne.title }}</a>
                </li>
              </ul>
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14"></span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li v-for="(subjectTwo, subjectTwoIndex) in subjectTwoList" :key="subjectTwoIndex" :class="{active: twoIndex === subjectTwoIndex}">
                  <a :title="subjectTwo.title" href="#" @click="searchTwo(subjectTwo.id, subjectTwoIndex)">{{ subjectTwo.title }}</a>
                </li>
              </ul>
            </dd>
          </dl>
          <div class="clear"></div>
        </section>
        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">1</i>/
              <i class="c-666 f-fM">1</i>
            </span>
          </section>
          <section class="fl">
            <ol class="js-tap clearfix">
              <li :class="{'current bg-orange':buyCountSort!=''}">
                <a title="popularity" href="#" @click="searchBuyCount()">Popularity</a>
                <span :class="{hide: buyCountSort === ''}">↓</span>
              </li>
              <li :class="{'current bg-orange':gmtCreateSort!=''}">
                <a title="time" href="#" @click="searchGmtCreate()">Time</a>
                <span :class="{hide: gmtCreateSort === ''}">↓</span>
              </li>
              <li :class="{'current bg-orange':priceSort!=''}">
                <a title="price" href="#" @click="searchPrice()">Price&nbsp;
                  <span :class="{hide: priceSort === ''}">↓</span>
                </a>
              </li>
            </ol>
          </section>
        </div>
        <div class="mt40">
          <!-- /no data notice start-->
          <section class="no-data-wrap" v-if="courseList.length === 0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">No related course, we are fixing on this...</span>
          </section>
          <!-- /no data notice end-->
          <article class="comm-course-list" v-else>
            <ul class="of" id="bna">
              <li v-for="(course, id) in courseList" :key="id">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :src="course.cover" class="img-responsive" :alt="course.title">
                    <div class="cc-mask">
                      <a :href="'/course/' + course.id" title="start" class="comm-btn c-btn-1">start</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :href="'/course/' + course.id" :title="course.title" class="course-title fsize18 c-333">{{ course.title }}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span class="fr jgTag bg-green" v-if="Number(course.price) === 0">
                      <i class="c-fff fsize12 f-fA">free</i>
                    </span>
                    <span class="fr jgTag bg-green" v-else>
                      <i class="c-fff fsize12 f-fA">$ {{Number(course.price)}}</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
        <!-- pagination start -->
        <div>
          <div class="paging">
            <a
                :class="{undisable: !pagination.hasPrevious}"
                href="#"
                title="start"
                @click.prevent="gotoPage(1)">start</a>
            <a
                :class="{undisable: !pagination.hasPrevious}"
                href="#"
                title="previous"
                @click.prevent="pagination.hasPrevious && gotoPage(pagination.currentPage - 1)">&lt;</a>
            <a
                v-for="page in pagination.totalPages"
                :key="page"
                :class="{current: pagination.currentPage === page, undisable: pagination.currentPage === page}"
                :title="'Page No.'+page"
                href="#"
                @click.prevent="gotoPage(page)">{{ page }}</a>
            <a
                :class="{undisable: !pagination.hasNext}"
                href="#"
                title="next"
                @click.prevent="pagination.hasNext && gotoPage(pagination.currentPage + 1)">&gt;</a>
            <a
                :class="{undisable: !pagination.hasNext}"
                href="#"
                title="end"
                @click.prevent="gotoPage(pagination.totalPages)">end</a>
            <div class="clear"/>
          </div>
        </div>
        <!-- pagination end -->
      </section>
    </section>
    <!-- /course list end -->
  </div>
</template>
<script>
import courseApi from '@/api/course'
export default {
  data() {
    return {
      page: 1,
      limit: 8,
      subjectOneList: [],
      subjectTwoList: [],
      courseQuery: {},
      courseList: [],
      oneIndex: -1,
      twoIndex: -1,
      buyCountSort: "",
      gmtCreateSort: "",
      priceSort: "",
      pagination: {
        currentPage: 1,
        totalRows: 0,
        hasNext: false,
        hasPrevious: false,
        currentSize: 0,
        totalPages: 0
      }
    }
  },
  created() {
    this.getCourseList(1)
    this.getAllSubject()
  },
  methods: {
    getCourseList(page) {
      courseApi.getCourseList(page, this.limit, this.courseQuery)
        .then(response => {
          this.courseList = response.data.data.courseList.items
          this.pagination.currentPage = response.data.data.courseList.currentPage
          this.pagination.totalRows = response.data.data.courseList.totalRows
          this.pagination.hasNext = response.data.data.courseList.hasNext
          this.pagination.hasPrevious = response.data.data.courseList.hasPrevious
          this.pagination.totalPages = response.data.data.courseList.totalPages
        })
    },
    getAllSubject() {
      courseApi.getAllSubject()
        .then(response => {
          this.subjectOneList = response.data.data.subjectTree
        })
    },
    gotoPage(page) {
      this.getCourseList(page)
    },
    getSubjectTwoList(subjectOneId, index) {
      this.oneIndex = index
      this.twoIndex = -1
      this.courseQuery.subjectId = ""
      this.courseQuery.subjectParentId = subjectOneId
      this.gotoPage(1)
      this.subjectTwoList = this.subjectOneList[index].children
    },
    searchTwo(subjectTwoId, index) {
      this.twoIndex = index
      this.courseQuery.subjectId = subjectTwoId
      this.gotoPage(1)
    },
    searchBuyCount() {
      this.buyCountSort = "1";
      this.gmtCreateSort = "";
      this.priceSort = "";
      this.courseQuery.buyCountSort = this.buyCountSort;
      this.courseQuery.gmtCreateSort = this.gmtCreateSort;
      this.courseQuery.priceSort = this.priceSort;
      this.gotoPage(this.page)
    },
    searchGmtCreate() {
      debugger
      this.buyCountSort = "";
      this.gmtCreateSort = "1";
      this.priceSort = "";
      this.courseQuery.buyCountSort = this.buyCountSort;
      this.courseQuery.gmtCreateSort = this.gmtCreateSort;
      this.courseQuery.priceSort = this.priceSort;
      this.gotoPage(this.page)
    },
    searchPrice() {
      this.buyCountSort = "";
      this.gmtCreateSort = "";
      this.priceSort = "1";
      this.courseQuery.buyCountSort = this.buyCountSort;
      this.courseQuery.gmtCreateSort = this.gmtCreateSort;
      this.courseQuery.priceSort = this.priceSort;
      this.gotoPage(this.page)
    }
  }
};
</script>
<style scoped>
.active {
  background: #bdbdbd;
}
.hide {
  display: none;
}
.show {
  display: block;
}
</style>
