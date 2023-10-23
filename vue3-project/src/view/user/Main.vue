<template>
  <UserInfoComp>
    <div>用户首页</div>
  </UserInfoComp>

  <div v-if="loginUser.login">
    <img v-if="loginUser.tbUserInfo.img" :src="loginUser.tbUserInfo.img" alt="" />
    <img v-else src="https://tse2-mm.cn.bing.net/th/id/OIP-C.nLttwNqxbFh-fdikGcOBZQAAAA?pid=ImgDet&rs=1" alt="" />
    <br />
    联系电话： {{ loginUser.tbUserInfo.phone }}
    <br />
    EMail： {{ loginUser.tbUserInfo.email }}
  </div>
</template>

<script lang="ts" setup>
import { storeToRefs } from 'pinia'
import { store } from '../../store/index'
const { loginUser } = storeToRefs(store())

import UserInfoComp from '../../components/UserInfoComp.vue'
</script>

<style lang="scss" scoped>
img {
  width: 5rem;
}
</style>
