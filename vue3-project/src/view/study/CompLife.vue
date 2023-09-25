<template>
  <div>
    {{ viewdata.title }}
    <RouterLink to="/study/comp">组件演示</RouterLink>
  </div>
</template>
<script lang="ts" setup>
import { reactive, onMounted, onUnmounted } from 'vue'

const viewdata = reactive({
  title: '生命周期演示',
})

onMounted(() => {
  // 本页面中所有元素加载完毕就会调用本钩子函数
  console.log('comp-life页面挂载完成...')
})

onUnmounted(() => {
  // 页面销毁时调用本钩子函数
  console.log('comp-life页面销毁...')
})
</script>
<style></style>
