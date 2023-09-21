<template>
  <div>
    {{ viewdata.title }}
  </div>

  <div>
    {{ viewdata.page }}
    <input type="text" v-model="viewdata.page.pageNumber" />
    <input type="text" v-model="viewdata.page.pageSize" />
  </div>

  <div>
    {{ viewdata.result }}
  </div>

  <hr />

  <input type="checkbox" v-model="visible" />
  {{ visible }}

  <hr />

  <input type="text" v-model="refuser.username" />
  <input type="text" v-model="refuser.password" />
  refuser: {{ refuser }}
  <button @click="changeRef">修改</button>
  <button @click="refuser.password = 'a1'">页面修改</button>

  <hr />

  <input type="text" v-model="user.username" />
  <input type="text" v-model="user.password" />
  user: {{ user }}
  <button @click="changeReactive">修改</button>
</template>
<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { PageInfo, BaseResult } from '../ts/BaseResult'
import ApiService from '../ts/ApiService'

const viewdata = reactive({
  title: '测试页面',
  page: new PageInfo(),
  result: new BaseResult(),
})

viewdata.result.message = '服务器信息'

ApiService.get('/', { echo: '黑暗骑士' }, (data: BaseResult) => {
  console.log(data.message)
})

// ref 和 reactive
// 区别一：ref支持值类型和简单数据类型，reactive只支持对象类型
const visible = ref(false)

const refuser = ref({
  username: '',
  password: '',
})

const user = reactive({
  username: '',
  password: '',
})

// 差别二：ref对象的值修改要通过value属性进行，reactive可以直接修改
function changeRef() {
  refuser.value.username = '代码修改'
}

function changeReactive() {
  user.username = '代码修改'
}
</script>
<style></style>
