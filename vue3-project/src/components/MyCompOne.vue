<template>
  <div>
    <slot></slot>
  </div>
  <hr>
  <div>
    {{ viewdata.title }}
    info属性值：{{ info }} 对象类型的属性值：{{ dept }}
  </div>
  <div>
    <input type="text" v-model="viewdata.compinfo" />
    {{ viewdata.compinfo }}
    <button @click="changeInfo">子组件通过事件传递数据</button>
  </div>

  <div>
    <slot name="footer"></slot>
  </div>

</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import { Dept } from '../ts/Manage'

// 定义自定义事件
const emits = defineEmits(['change-info'])

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
  compinfo: '',
})

function changeInfo() {
  console.log('子组件事件：', viewdata.compinfo)
  // 通知父组件触发事件，加一个参数传递
  emits('change-info', viewdata.compinfo)
}
</script>
<style></style>
