<template>
  状态管理的变量：{{ count }}
  <button @click="increment">修改状态管理的变量值</button>
  <button @click="router.push('/study/piniaother')"> 
    跳转到另一个pinia页面 
  </button>
</template>

<script lang="ts" setup>
// 路由api
import { useRouter } from 'vue-router'
const router = useRouter()

// 使用pinia定义的变量和方法
import { storeToRefs } from 'pinia'
import store from '../../store/index'

// state
const { count } = storeToRefs(store())
// actions
const { increment } = store()
</script>

<style lang="scss" scoped></style>
