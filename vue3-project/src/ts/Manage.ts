// 服务器端数据实体类型映射
// 部门和员工
class Dept {
  deptId: number | null = null
  deptInfo: string = ''
  deptName: string = ''
  lastupdate: number | null = null
}

class Employee {
  deptId: number | null = null
  employeeId: number | null = null
  employeeName: string = ''
  phone: string = ''
  lastupdate: number | null = null
  dept: Dept | null = null
}

export { Dept as Dept, Employee as Employee }
