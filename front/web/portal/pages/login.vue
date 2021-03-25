<template>
  <div class="main">
    <div class="title">
      <a class="active" href="/login">Login</a>
      <span>·</span>
      <a href="/register">Register</a>
    </div>
    <div class="sign-up-container">
      <el-form ref="userForm" :model="user">
        <el-form-item class="input-prepend restyle" prop="mobile"
                      :rules="[{ required: true, message: 'Enter your mobile number', trigger: 'blur' },{validator: checkPhone, trigger: 'blur'}]">
          <div>
            <el-input type="text" placeholder="mobile number" v-model="user.mobile"/>
            <i class="iconfont icon-phone"/>
          </div>
        </el-form-item>
        <el-form-item class="input-prepend" prop="password"
                      :rules="[{ required: true, message: 'Enter your password', trigger: 'blur' }]">
          <div>
            <el-input type="password" placeholder="password" v-model="user.password"/>
            <i class="iconfont icon-password"/>
          </div>
        </el-form-item>
        <div class="btn">
          <input type="button" class="sign-in-button" value="Login" @click="submitLogin()">
        </div>
      </el-form>
      <!-- Other methods -->
      <div class="more-sign">
        <h6>Social Media Login</h6>
        <ul>
          <li><a id="weixin" class="weixin" target="_blank"
                 :href = "baseURL+'/api/ucenter/wx/login'"><i class="iconfont icon-weixin"/></a>
          </li>
          <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a></li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import cookie from 'js-cookie'
import loginApi from '@/api/login.js'
export default {
  layout: 'sign',
  data() {
    return {
      user: {
        mobile: '',
        password: ''
      },
      loginInfo: {},
      baseURL: 'http://localhost:9001'
    }
  },
  methods: {
    submitLogin() {
      loginApi.submitLogin(this.user)
          .then(response => {
            // put token in cookie or local storage
            cookie.set('mooc_token', response.data.data.token, {domain: 'localhost'})
            // get member info from token
            loginApi.getLoginInfo()
                .then(response => {
                  this.loginInfo = response.data.data.member
                  // put member info into cookie
                  cookie.set('mooc_member', this.loginInfo, {domain: 'localhost'})
                  window.location.href = "/"
                })
          })
    },
    checkPhone(rule, value, callback) {
      //debugger
      if (!(/^1[34578]\d{9}$/.test(value))) {
        return callback(new Error('Wrong Mobile Number Format'))
      }
      return callback()
    }
  }
}
</script>
