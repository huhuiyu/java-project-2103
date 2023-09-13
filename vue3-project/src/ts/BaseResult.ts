// 基础应答结果的对象定义
class PageInfo {
  pageNumber: number = 1
  pageSize: number = 5
  total: number = 0
  pageCount: number = 0
}

class BaseResult {
  code: number = 500
  success: boolean = false
  message: string = ''
  token: string = ''
}

export { PageInfo as PageInfo, BaseResult as BaseResult }
