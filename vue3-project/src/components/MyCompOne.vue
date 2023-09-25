<template>
  <div>
    {{ viewdata.title }}
    info属性值：{{ info }} 对象类型的属性值：{{ dept }}
  </div>
</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import { Dept } from '../ts/Manage'
// 定义组件的属性
defineProps({
  info: {
    type: String,
    default: '默认值',
  },
  dept: {
    type: Dept,
  },
})

const viewdata = reactive({
  title: '组件一',
})
</script>
<style></style>
