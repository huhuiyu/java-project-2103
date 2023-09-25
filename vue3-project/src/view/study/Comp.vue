<template>
  <div>
    {{ viewdata.title }}

    <RouterLink to="/study/complife">生命周期演示</RouterLink>
  </div>
  <hr />
  <MyCompOne info="属性值传入" :dept="viewdata.dept"></MyCompOne>
  <hr />
  <MyCompOne :info="viewdata.info"></MyCompOne>

  <hr />
  <input id="myinput" type="text" v-model="viewdata.info" />
  {{ viewdata.info }}
</template>

<script lang="ts" setup>
import { reactive, onMounted, onUpdated, onUnmounted } from 'vue'
import MyCompOne from '../../components/MyCompOne.vue'
import { RouterLink } from 'vue-router'
import { Dept } from '../../ts/Manage'
const viewdata = reactive({
  title: '组件学习',
  info: '生命周期演示信息',
  dept: new Dept(),
})

viewdata.dept.deptName = '部门名称'

console.log('input元素：', document.getElementById('myinput'))

// vue生命周期钩子函数
onMounted(() => {
  // 本页面中所有元素加载完毕就会调用本钩子函数
  console.log('comp页面挂载完成...')
  console.log('input元素：', document.getElementById('myinput'))
})

onUpdated(() => {
  // 本页面中所有数据发生变化就会调用本钩子函数
  console.log('comp页面数据更新完成...')
})

onUnmounted(() => {
  // 页面销毁时调用本钩子函数
  console.log('comp页面销毁...')
})
</script>
<style></style>
