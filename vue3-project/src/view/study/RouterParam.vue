<template>
  <div>获取路由参数的页面</div>
  <div>路径参数：{{ info }}</div>
  <div>查询参数：{{ query }}</div>
</template>

<script lang="ts" setup>
import { useRoute } from 'vue-router'
const route = useRoute()
 // 带路径参数的路由配置：'/study/routerparam/:info',
 // 获取路径中的指定名称的参数就是route.params.{:后面的名称}
const info = route.params.info
const query = route.query

</script>

<style lang="scss" scoped></style>
