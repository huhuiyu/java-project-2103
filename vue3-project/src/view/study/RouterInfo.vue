<template>
  <div> 传递路由参数的页面 </div>

  <div>
    <input type="text" v-model="info" />
    <input type="text" v-model="query.id" />
    <input type="text" v-model="query.name" />
    <button @click="toParamPage">发送路由信息</button>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()

const info = ref('DarkKnight')
const query = ref({
  id: 100,
  name: '黑暗骑士',
})

function toParamPage() {
  // 带路径参数的路由配置：'/study/routerparam/:info',
  // 表示路径中:info的部分是可以任意参数
  // get提交的参数用query参数就可以了
  let config = {
    path: `/study/routerparam/${info.value}`,
    query: query.value,
  }
  router.push(config)
}
</script>

<style lang="scss" scoped></style>
