<template>
  <div class="main">
    <div class="title">
      <a href="/login">Login</a>
      <span>·</span>
      <a class="active" href="/register">Register</a>
    </div>
    <div class="sign-up-container">
      <el-form ref="userForm" :model="params">
        <el-form-item class="input-prepend restyle" prop="nickname"
                      :rules="[{ required: true, message: 'Enter your nickname', trigger: 'blur' }]">
          <div>
            <el-input type="text" placeholder="nickname" v-model="params.nickname"/>
            <i class="iconfont icon-user"/>
          </div>
        </el-form-item>
        <el-form-item class="input-prepend restyle no-radius" prop="mobile"
                      :rules="[{ required: true, message: 'Enter your mobile number', trigger: 'blur' },{validator: checkPhone, trigger: 'blur'}]">
          <div>
            <el-input type="text" placeholder="mobile number" v-model="params.mobile"/>
            <i class="iconfont icon-phone"/>
          </div>
        </el-form-item>
        <el-form-item class="input-prepend restyle no-radius" prop="code"
                      :rules="[{ required: true, message: 'Enter verify code', trigger: 'blur' }]">
          <div style="width: 100%;display: block;float: left;position: relative">
            <el-input type="text" placeholder="verify code" v-model="params.code"/>
            <i class="iconfont icon-phone"/>
          </div>
          <div class="btn" style="position:absolute;right: 0;top: 6px;width: 40%;">
            <a href="javascript:" type="button" @click="getCodeFun()" :value="codeTest"
               style="border: none;background-color: none">{{ codeTest }}</a>
          </div>
        </el-form-item>
        <el-form-item class="input-prepend" prop="password"
                      :rules="[{ required: true, message: 'Enter your password', trigger: 'blur' }]">
          <div>
            <el-input type="password" placeholder="password" v-model="params.password"/>
            <i class="iconfont icon-password"/>
          </div>
        </el-form-item>
        <div class="btn">
          <input type="button" class="sign-up-button" value="register" @click="submitRegister()">
        </div>
        <p class="sign-up-msg">
          Click the register button means you are willing to follow the website's policy
          <br>
          <a target="_blank" href="http://www.jianshu.com/p/c44d171298ce">User Policy</a>
        </p>
      </el-form>
      <!-- Other methods -->
      <div class="more-sign">
        <h6>Social Media Account</h6>
        <ul>
          <li><a id="weixin" class="weixin" target="_blank" :href="baseURL+'/api/ucenter/wx/login'"><i
              class="iconfont icon-weixin"/></a></li>
          <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a></li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import registerApi from '@/api/register'

export default {
  layout: 'sign',
  data() {
    return {
      params: {
        mobile: '',
        code: '',
        nickname: '',
        password: ''
      },
      sending: true,      // if send code
      second: 60,        // count down
      codeTest: 'Get Verify Code',
      baseURL: 'http://localhost:9001'
    }
  },
  methods: {
    getCodeFun() {
      if (!this.sending)
        return;

      this.$refs.userForm.validateField('mobile', (errMsg) => {
        if (errMsg == '') {
          registerApi.sendCode(this.params.mobile).then(res => {
            this.sending = false;
            this.timeDown();
          });
        }
      })
    },
    timeDown() {
      let result = setInterval(() => {
        --this.second;
        this.codeTest = this.second
        if (this.second < 1) {
          clearInterval(result);
          this.sending = true;
          //this.disabled = false;
          this.second = 60;
          this.codeTest = "Get Verify Code"
        }
      }, 1000);
    },
    submitRegister() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {
          registerApi.submitRegister(this.params)
              .then(response => {
                this.$message({
                  type: 'success',
                  message: "Register successfully"
                })
                this.$router.push({path: '/login'})
              })
          .catch(() => {
            this.$message({
              type: 'error',
              message: "Register fails"
            })
          })
        }
      })
    },
    checkPhone(rule, value, callback) {
      //debugger
      if (!(/^1[34578]\d{9}$/.test(value))) {
        return callback(new Error('Wrong mobile number format'))
      }
      return callback()
    }
  }
}
</script>
