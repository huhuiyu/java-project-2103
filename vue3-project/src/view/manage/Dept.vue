<template>
  <div>
    {{ title }}
  </div>
  <div>
    <ElTable :data="list" stripe border>
      <ElTableColumn prop="deptName" label="部门名称"></ElTableColumn>
      <ElTableColumn  prop="deptInfo" label="部门描述"></ElTableColumn>
    </ElTable>
  </div>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import ApiService from '../../ts/ApiService'
import Tools from '../../ts/Tools'
import { BaseListResult, PageInfo } from '../../ts/BaseResult'
import { Dept } from '../../ts/Manage'
import { ElMessageBox,ElTable,ElTableColumn } from 'element-plus'

const title = ref('部门管理')
const page = ref(new PageInfo())
const list = ref(new Array<Dept>())
const queryInfo = ref({
  deptName: '',
})

function query() {
  ApiService.post('/manage/dept/queryAll'
  , Tools.concatJson(page.value, queryInfo.value)
  , (data: BaseListResult<Dept>) => {
    if (data.success) {
      page.value = data.page
      list.value = data.list
      return
    }
    ElMessageBox.alert(data.message, '部门管理')
  })
}

query()
</script>
<style></style>
