<template>
  <div class="Page Confirm">
    <div class="Title">
      <h1 class="fl f18">Order Confirmation</h1>
      <img src="~/assets/img/cart_setp2.png" class="fr">
      <div class="clear"></div>
    </div>
    <form name="flowForm" id="flowForm" method="post" action="">
      <table class="GoodList">
        <tbody>
        <tr>
          <th class="name">Item</th>
          <th class="price">Price</th>
          <th class="priceNew">Price Now</th>
        </tr>
        </tbody>
        <tbody>
        <!-- <tr>
          <td colspan="3" class="Title red f18 fb"><p>限时折扣</p></td>
        </tr> -->
        <tr>
          <td colspan="3" class="teacher">Teacher: {{order.teacherName}}</td>
        </tr>
        <tr class="good">
          <td class="name First">
            <a target="_blank" :href="'https://localhost:3000/course/'+order.courseId">
              <img :src="order.courseCover"></a>
            <div class="goodInfo">
              <input type="hidden" class="ids ids_14502" value="14502">
              <a target="_blank" :href="'https://localhost:3000/course/'+ order.courseId">{{order.courseTitle}}</a>
            </div>
          </td>
          <td class="price">
            <p>$<strong>{{order.totalFee}}</strong></p>
            <!-- <span class="discName red">限时8折</span> -->
          </td>
          <td class="red priceNew Last">$<strong>{{order.totalFee}}</strong></td>
        </tr>
        <tr>
          <td class="Billing tr" colspan="3">
            <div class="tr">
              <p>Total: <strong class="red">1</strong> items</p>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <div class="Finish">
        <div class="fr" id="AgreeDiv">
          <label for="Agree"><p class="on"><input type="checkbox" checked="checked">I agree with the terms<a href="javascript:" target="_blank"> Agreement </a></p></label>
        </div>
        <div class="clear"></div>
        <div class="Main fl">
          <div class="fl">
            <a :href="'/course/'+order.courseId">Go back to course page</a>
          </div>
          <div class="fr">
            <p><span class="red f20">$ <strong id="AllPrice">{{order.totalFee}}</strong></span></p>
          </div>
        </div>
        <input name="score" value="0" type="hidden" id="usedScore">
        <button class="fr redb" type="button" id="submitPay" @click="toPay()">Pay</button>
        <div class="clear"></div>
      </div>
    </form>
  </div>
</template>
<script>
import orderApi from '@/api/order'
export default {
  data() {
    return {
      orderNo: '',
      order: {}
    }
  },
  created() {
    this.orderNo = this.$route.params.id
    this.getOrderInfo()
  },
  methods: {
    getOrderInfo() {
      orderApi.getOrderInfo(this.orderNo)
        .then(response => {
          this.order = response.data.data.order
        })
    },
    toPay() {
      this.$router.push({path: '/pay/' + this.orderNo})
    }
  }
}
</script>

<style scoped>

</style>
