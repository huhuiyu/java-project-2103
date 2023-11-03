<template>
  计数器的值：{{ count }}
  <button @click="increment">修改计数器的值</button>
</template>

<script lang="ts" setup>
import { storeToRefs } from 'pinia'
import store from '../store/index'
// 获取pinia状态管理中的count值
const { count } = storeToRefs(store())
// 获取pinia状态管理中的increment方法
const { increment } = store()
</script>