<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- teacher list start -->
    <section class="container">
      <header class="comm-title all-teacher-title">
        <h2 class="fl tac">
          <span class="c-333">All teachers</span>
        </h2>
        <section class="c-tab-title">
          <a id="subjectAll" title="All" href="#">All</a>
          <!-- <c:forEach var="subject" items="${subjectList }">
          <a id="${subject.subjectId}" title="${subject.subjectName }" href="javascript:void(0)" onclick="submitForm(${subject.subjectId})">${subject.subjectName }</a>
          </c:forEach>-->
        </section>
      </header>
      <section class="c-sort-box unBr">
        <div>
          <!-- no data warning start-->
          <section class="no-data-wrap" v-if="pagination.totalRows === 0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">No data, we are working on it</span>
          </section>
          <!-- no data warning end-->
          <article class="i-teacher-list" v-else>
            <ul class="of">
              <li v-for="(teacher, id) in teacherList" :key="id">
                <section class="i-teach-wrap">
                  <div class="i-teach-pic">
                    <a :href="'/teacher/'+teacher.id" :title="teacher.name" target="_blank">
                      <img :src="teacher.avatar" :alt="teacher.name">
                    </a>
                  </div>
                  <div class="mt10 hLh30 txtOf tac">
                    <a :href="'/teacher/'+teacher.id" :title="teacher.name" target="_blank"
                       class="fsize18 c-666">{{ teacher.name }}</a>
                  </div>
                  <div class="hLh30 txtOf tac">
                    <span class="fsize14 c-999">{{ teacher.career }}</span>
                  </div>
                  <div class="mt15 i-q-txt">
                    <p class="c-999 f-fA">{{ teacher.intro }}</p>
                  </div>
                </section>
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
    <!-- teacher list end -->
  </div>
</template>
<script>
import teacherApi from '@/api/teacher'

export default {
  data() {
    return {
      teacherList: [],
      page: 1,
      limit: 8,
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
    this.getTeacherList(this.page, this.limit)
  },
  methods: {
    getTeacherList(page, limit) {
      teacherApi.getTeacherList(page, limit)
          .then(response => {
            this.teacherList = response.data.data.teacherList.items
            this.pagination.currentPage = response.data.data.teacherList.currentPage
            this.pagination.totalRows = response.data.data.teacherList.totalRows
            this.pagination.hasNext = response.data.data.teacherList.hasNext
            this.pagination.hasPrevious = response.data.data.teacherList.hasPrevious
            this.pagination.totalPages = response.data.data.teacherList.totalPages
          })
    },
    gotoPage(page) {
      this.getTeacherList(page, this.limit)
    }
  }
}
</script>
