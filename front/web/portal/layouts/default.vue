<template>
  <div class="in-wrap">
    <!-- header -->
    <header id="header">
      <section class="container">
        <h1 id="logo">
          <a href="#" title="Mooc Edu">
            <img src="~/assets/img/logo.png" width="100%" alt="Mooc Edu">
          </a>
        </h1>
        <div class="h-r-nsl">
          <ul class="nav">
            <router-link to="/" tag="li" active-class="current" exact>
              <a>Home</a>
            </router-link>
            <router-link to="/course" tag="li" active-class="current">
              <a>Course</a>
            </router-link>
            <router-link to="/teacher" tag="li" active-class="current">
              <a>Teacher</a>
            </router-link>
            <router-link to="/article" tag="li" active-class="current">
              <a>Article</a>
            </router-link>
            <router-link to="/qa" tag="li" active-class="current">
              <a>Q&A</a>
            </router-link>
          </ul>
          <!-- / nav -->
          <ul class="h-r-login">
            <li v-if="!loginInfo.id" id="no-login">
              <a href="/login" title="Login">
                <em class="icon18 login-icon">&nbsp;</em>
                <span class="vam ml5">Login</span>
              </a>
              |
              <a href="/register" title="Register">
                <span class="vam ml5">Register</span>
              </a>
            </li>
            <li v-if="loginInfo.id" id="is-login-one" class="mr10">
              <a id="headerMsgCountId" href="#" title="Message">
                <em class="icon18 news-icon">&nbsp;</em>
              </a>
              <q class="red-point" style="display: none">&nbsp;</q>
            </li>
            <li v-if="loginInfo.id" id="is-login-two" class="h-r-user">
              <a href="/ucenter" title>
                <img
                    :src="loginInfo.avatar"
                    width="30"
                    height="30"
                    class="vam picImg"
                    alt
                >
                <span id="userName" class="vam disIb">{{ loginInfo.nickname }}</span>
              </a>
              <a href="javascript:void(0);" title="Logout" @click="logout()" class="ml5">Logout</a>
            </li>
            <!-- if not logged in -->
          </ul>
          <aside class="h-r-search">
            <form action="#" method="post">
              <label class="h-r-s-box">
                <input type="text" placeholder="search..." name="queryCourse.courseName" value>
                <button type="submit" class="s-btn">
                  <em class="icon18">&nbsp;</em>
                </button>
              </label>
            </form>
          </aside>
        </div>
        <aside class="mw-nav-btn">
          <div class="mw-nav-icon"></div>
        </aside>
        <div class="clear"></div>
      </section>
    </header>

    <!-- /header -->
    <nuxt/>

    <!-- footer -->
    <footer id="footer">
      <section class="container">
        <div class>
          <h4 class="hLh30">
            <span class="fsize18 f-fM c-999">link</span>
          </h4>
          <ul class="of flink-list">
            <li>
              <a href="http://www.github.com/xxLiuxx" title="Github" target="_blank">Github</a>
            </li>
          </ul>
          <div class="clear"></div>
        </div>
        <div class="b-foot">
          <section class="fl col-7">
            <section class="mr20">
              <section class="b-f-link">
                <a href="#" title="About us" target="_blank">About us</a>|
                <a href="#" title="Contact us" target="_blank">Contact us</a>|
                <a href="#" title="Help Center" target="_blank">Help Center</a>|
                <a href="#" title="Resource" target="_blank">Resource</a>|
                <span>Phone：010-56253825(Beijing) 0755-85293825(Shenzhen)</span>
                <span>Email：info@xxLiuxx.com</span>
              </section>
              <section class="b-f-link mt10">
                <span>©2020 All rights reserved by xxLiuxx</span>
              </section>
            </section>
          </section>
          <aside class="fl col-3 tac mt15">
            <section class="gf-tx">
              <span>
                <img src="~/assets/img/wx-icon.png" alt>
              </span>
            </section>
            <section class="gf-tx">
              <span>
                <img src="~/assets/img/wb-icon.png" alt>
              </span>
            </section>
          </aside>
          <div class="clear"></div>
        </div>
      </section>
    </footer>
    <!-- /footer -->
  </div>
</template>
<script>
import "~/assets/css/reset.css";
import "~/assets/css/theme.css";
import "~/assets/css/global.css";
import "~/assets/css/web.css";
import cookie from 'js-cookie'
export default {
  data(){
    return {
      token: '',
      loginInfo: {
        id: '',
        age: '',
        avatar: '',
        mobile: '',
        nickname: '',
        sex: ''
      }
    }
  },
  created() {
    this.showInfo()
  },
  methods: {
    showInfo() {
      let memberStr = cookie.get('mooc_member')
      if(memberStr) {
        this.loginInfo = JSON.parse(memberStr)
      }
    },
    logout() {
      cookie.remove('mooc_token')
      cookie.remove('mooc_member')
      window.location.href = "/"
    }
  }
}
</script>
