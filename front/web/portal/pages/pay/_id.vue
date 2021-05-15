<template>
  <div class="cart py-container">
    <div class="checkout py-container  pay">
      <div class="checkout-tit">
        <h4 class="fl tit-txt"><span class="success-icon"></span><span class="success-info">Order created, please make the payment, order number: {{ payObj.out_trade_no }}</span>
        </h4>
        <span class="fr"><em class="sui-lead">Total: </em><em class="orange money"> $ {{ payObj.total_fee }}</em></span>
        <div class="clearfix"></div>
      </div>
      <div class="checkout-steps">
        <div class="fl weixin">WeChat Pay</div>
        <div class="fl sao">
          <p class="red">Please scan with WeChat</p>
          <div class="fl code">
            <!-- <img id="qrious" src="~/assets/img/erweima.png" alt=""> -->
            <!-- <qriously value="weixin://wxpay/bizpayurl?pr=R7tnDpZ" :size="338"/> -->
            <qriously :value="payObj.code_url" :size="338"/>
            <div class="saosao">
              <p>Please scan with WeChat</p>
              <p>Scan the QR code to pay</p>
            </div>
          </div>
        </div>
        <div class="clearfix"></div>
        <!-- <p><a href="pay.html" target="_blank">> 其他支付方式</a></p> -->
      </div>
    </div>
  </div>
</template>

<script>
import orderApi from '@/api/order'

export default {
  data() {
    return {
      payObj: {},
      orderNo: '',
      timer: ''
    }
  },
  created() {
    this.orderNo = this.$route.params.id
    this.createCode()
  },
  mounted() {
    // check the order status every 3 seconds
    setInterval(() => {
      this.timer = this.checkOrderStatus(this.payObj.out_trade_no)
    }, 3000)
  },
  methods: {
    createCode() {
      orderApi.createCode(this.orderNo)
          .then(response => {
            this.payObj = response.data.data.payInfo
          })
    },
    checkOrderStatus() {
      orderApi.checkStatus(this.orderNo)
          .then(response => {
            if (response.data.success) {
              clearInterval(this.timer)
              this.$message({
                type: 'success',
                message: 'Order Placed!'
              })
              this.$router.push({path: '/course/' + this.payObj.course_id})
            }
          })
    }
  }
}
</script>

<style scoped>

</style>
