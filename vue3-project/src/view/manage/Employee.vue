<template>
  <div>
    {{ viewdata.title }}
  </div>

  <!-- 查询的表单部分 -->
  <div>
    <ElForm :inline="true">
      <ElFormItem>
        <ElInput placeholder="姓名" v-model="viewdata.queryInfo.employeeName"></ElInput>
      </ElFormItem>

      <ElFormItem>
        <ElInput placeholder="电话" v-model="viewdata.queryInfo.phone"></ElInput>
      </ElFormItem>

      <ElFormItem>
        <ElSelect v-model="viewdata.queryInfo.orderBy">
          <ElOption v-for="d in viewdata.orderByList" :value="d.value" :label="d.text"></ElOption>
        </ElSelect>
      </ElFormItem>

      <ElFormItem>
        <ElButton type="primary" @click="query">查询</ElButton>
      </ElFormItem>
    </ElForm>
  </div>

  <!-- 数据呈现的部分 -->
  <div>
    <!-- {{ viewdata.page }} -->
    <ElTable :data="viewdata.list">
      <ElTableColumn label="姓名" prop="employeeName"></ElTableColumn>
      <ElTableColumn label="电话" prop="phone"></ElTableColumn>
      <ElTableColumn label="所属部门" prop="dept.deptName"></ElTableColumn>
      <ElTableColumn label="信息最后修改时间">
        <template #default="s">
          {{ Tools.formatDate(s.row.lastupdate) }}
        </template>
      </ElTableColumn>
    </ElTable>

    <PageComp :page="viewdata.page" @page-change="query"></PageComp>
  </div>
</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import { Dept, Employee } from '../../ts/Manage'
import { BaseResult, BaseListResult, PageInfo } from '../../ts/BaseResult'
import { Tools } from '../../ts/Tools'
import { ApiService } from '../../ts/ApiService'
import PageComp from '../../components/PageComp.vue'
import { ElSelect, ElOption, ElButton, ElTable, ElTableColumn, ElMessageBox, ElForm, ElFormItem, ElInput } from 'element-plus'

const viewdata = reactive({
  title: '员工管理',
  page: new PageInfo(),
  list: new Array<Employee>(),
  queryInfo: {
    deptId: -1,
    employeeName: '',
    orderBy: 2,
    phone: '',
  },
  orderByList: [
    { value: 1, text: '按照编号升序' },
    { value: 2, text: '按照编号降序' },
    { value: 3, text: '按照部门名称升序' },
    { value: 4, text: '按照部门名称降序' },
    { value: 5, text: '分部门按照姓名排序' },
  ],
})

function query() {
  ApiService.post('/manage/employee/queryAll', Tools.concatJson(viewdata.page, viewdata.queryInfo), (data: BaseListResult<Employee>) => {
    if (data.success) {
      viewdata.list = data.list
      viewdata.page = data.page
    }
  })
}

query()
</script>
<style></style>
