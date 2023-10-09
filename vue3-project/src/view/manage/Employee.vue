<template>
  <div>
    {{ viewdata.title }}
  </div>

  <!-- 查询的表单部分 -->
  <div>
    <ElForm :inline="true">
      <ElFormItem>
        <ElButton @click="showDeptDialog(1)" type="primary">
          {{ viewdata.queryDept.deptName }}
        </ElButton>
      </ElFormItem>

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
        <ElButton type="danger" @click="reset">重置</ElButton>
        <ElButton type="success" @click="showAddDialog">添加</ElButton>
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

      <ElTableColumn label="操作">
        <template #default="scope">
          <ElButton @click="showModifyDialog(scope.row)">修改</ElButton>

          <ElButton type="danger" @click="del(scope.row)">删除</ElButton>
        </template>
      </ElTableColumn>
    </ElTable>

    <PageComp :page="viewdata.page" @page-change="query"></PageComp>
  </div>

  <!-- 部门选择的对话框 -->
  <ElDialog v-model="viewdata.dvisible" title="部门选择">
    <div>
      <DeptComp @selected="selectedDept"></DeptComp>
    </div>
  </ElDialog>

  <!-- 添加的对话框 -->
  <ElDialog v-model="viewdata.avisible" title="添加员工" @closed="query">
    <div>
      <ElForm>
        <ElFormItem>
          <ElButton @click="showDeptDialog(2)" type="primary">
            {{ viewdata.addDept.deptName }}
          </ElButton>
        </ElFormItem>

        <ElFormItem>
          <ElInput v-model="viewdata.addInfo.employeeName" placeholder="姓名"></ElInput>
        </ElFormItem>

        <ElFormItem>
          <ElInput v-model="viewdata.addInfo.phone" placeholder="手机号"></ElInput>
        </ElFormItem>
      </ElForm>
    </div>
    <template #footer>
      <div>
        <ElButton @click="add">添加</ElButton>
      </div>
    </template>
  </ElDialog>

  <!-- 修改的对话框 -->
  <ElDialog v-model="viewdata.mvisible" title="修改员工" @closed="query">
    <div>
      <ElForm>
        <ElFormItem>
          <ElButton @click="showDeptDialog(3)" type="primary">
            {{ viewdata.modifyDept.deptName }}
          </ElButton>
        </ElFormItem>

        <ElFormItem>
          <ElInput v-model="viewdata.modifyInfo.employeeName" placeholder="姓名"></ElInput>
        </ElFormItem>

        <ElFormItem>
          <ElInput v-model="viewdata.modifyInfo.phone" placeholder="手机号"></ElInput>
        </ElFormItem>
      </ElForm>
    </div>
    <template #footer>
      <div>
        <ElButton @click="modify">保存</ElButton>
      </div>
    </template>
  </ElDialog>
</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import { Dept, Employee } from '../../ts/Manage'
import { BaseResult, BaseListResult, PageInfo } from '../../ts/BaseResult'
import { Tools } from '../../ts/Tools'
import { ApiService } from '../../ts/ApiService'
import PageComp from '../../components/PageComp.vue'
import { ElSelect, ElOption, ElButton, ElTable, ElTableColumn, ElMessageBox, ElForm, ElFormItem, ElInput } from 'element-plus'
import DeptComp from '../../components/DeptComp.vue'

const viewdata = reactive({
  title: '员工管理',
  page: new PageInfo(),
  list: new Array<Employee>(),
  // 部门选择组件的模式，1:查询模式,2:添加，3:修改
  dmode: 1,
  queryDept: new Dept(),
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
  dvisible: false,
  // 添加相关变量
  addInfo: new Employee(),
  addDept: new Dept(),
  avisible: false,
  // 修改的部分
  modifyInfo: new Employee(),
  modifyDept: new Dept(),
  mvisible: false,
})

function del(info: Employee) {
  ElMessageBox.confirm(`是否删除${info.dept?.deptName}的${info.employeeName}`, '删除员工')
    .then(() => {
      ApiService.post(
        '/manage/employee/delete',
        {
          employeeId: info.employeeId,
        },
        (data: BaseResult) => {
          if (data.success) {
            ElMessageBox.alert(data.message)
            query()
          }
        },
      )
    })
    .catch(() => {})
}

function showModifyDialog(info: Employee) {
  info = Tools.concatJson(info)
  viewdata.modifyInfo = info
  viewdata.modifyDept = info.dept
  viewdata.mvisible = true
}

function modify() {
  // 删除不需要提交的联合查询字段（部门详细信息）
  delete viewdata.modifyInfo.dept

  ApiService.post('/manage/employee/update', viewdata.modifyInfo, (data: BaseResult) => {
    if (data.success) {
      ElMessageBox.alert(data.message)
    }
  })
}

function showAddDialog() {
  viewdata.addInfo = new Employee()
  viewdata.addDept.deptName = '请选择部门'
  viewdata.avisible = true
}

function add() {
  ApiService.post('/manage/employee/add', viewdata.addInfo, (data: BaseResult) => {
    if (data.success) {
      ElMessageBox.alert(data.message)
      viewdata.addInfo.employeeName = ''
      viewdata.addInfo.phone = ''
    }
  })
}

viewdata.queryDept.deptName = '请选择部门'

function reset() {
  viewdata.queryDept.deptName = '请选择部门'
  viewdata.queryInfo = {
    deptId: -1,
    employeeName: '',
    orderBy: 2,
    phone: '',
  }
  viewdata.page.pageNumber = 1
  query()
}

function showDeptDialog(mode: number) {
  // 记录部门选择的模式
  viewdata.dmode = mode
  viewdata.dvisible = true
}

function selectedDept(info: Dept) {
  if (viewdata.dmode == 1) {
    viewdata.queryDept = info
    viewdata.queryInfo.deptId = info.deptId
  } else if (viewdata.dmode == 2) {
    viewdata.addDept = info
    viewdata.addInfo.deptId = info.deptId
  } else if (viewdata.dmode == 3) {
    viewdata.modifyDept = info
    viewdata.modifyInfo.deptId = info.deptId
  }
  viewdata.dvisible = false
}

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
