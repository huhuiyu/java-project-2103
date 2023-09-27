// 工具类
import SparkMd5 from 'spark-md5'

/**
 * 静态方法工具类
 */
class Tools {
  /**
   * md5加密
   * @param info 要md5加密的字符
   * @returns md5加密后的结果
   */
  static md5(info: string) {
    return info && info.trim().length > 0 ? SparkMd5.hash(info) : ''
  }

  /**
   * 合并多个json对象
   * @param jsons 要合并的多个json
   * @returns 合并后的json对象
   */
  static concatJson(...jsons: any) {
    let json: any = {}
    for (let index = 0; index < jsons.length; index++) {
      let element = jsons[index]
      for (let key in element) {
        json[key] = element[key]
      }
    }
    return json
  }

  /**
   * 格式化日期
   * @param value 时间对象，可以时Date或者时间戳
   * @param format 格式化字符串
   * @returns 格式化日期之后的结果
   */
  static formatDate(value: Date | Number, format = 'yyyy-MM-dd hh:mm:ss') {
    try {
      let time = new Date()
      if (typeof value === 'number') {
        time.setTime(parseInt(value.toString()))
      } else if (value instanceof Date) {
        time = value
      }
      let year = time.getFullYear() + ''
      let month = time.getMonth() + 1 + ''
      let day = time.getDate() + ''
      let hour = time.getHours() + ''
      let minute = time.getMinutes() + ''
      let second = time.getSeconds() + ''
      let ms = time.getMilliseconds() + ''
      month = month.padStart(2, '0')
      day = day.padStart(2, '0')
      hour = hour.padStart(2, '0')
      minute = minute.padStart(2, '0')
      second = second.padStart(2, '0')
      ms = ms.padStart(3, '0')

      format = format.replace(/yyyy/g, year)
      format = format.replace(/MM/g, month)
      format = format.replace(/dd/g, day)
      format = format.replace(/hh/g, hour)
      format = format.replace(/mm/g, minute)
      format = format.replace(/ss/g, second)
      format = format.replace(/ms/g, ms)
      return format
    } catch (ex) {
      console.error(ex)
      return ''
    }
  }

  /**
   * 将文字信息复制到剪贴板
   * @param text 要复制的文本
   */
  static async copyText(text: string) {
    // 剪贴板对象的实现方式（更新和更好的方式，但是需要浏览器授权）
    try {
      await navigator.clipboard.writeText(text)
      return
    } catch (ex) {
      console.error(ex)
    }
    // 原始的，不支持剪贴板对象的实现方式
    // 创建输入框并设置内容
    let input = document.createElement('input')
    input.value = text
    // 放到页面上
    document.body.appendChild(input)
    // 选中文本框中的内容
    input.focus()
    input.select()
    input.setSelectionRange(0, text.length)
    // 调用浏览器的复制指令
    document.execCommand('Copy')
    // 移除input
    document.body.removeChild(input)
  }

  //#region 文件相关方法

  /**
   * 最大文件上传限制
   */
  static MAX_FILE_SIZE = 2 * 1024 * 1024

  /**
   * 获取文件信息
   * @param file 文件对象
   * @param fileinfo 文件信息对象
   * @returns 文件信息
   */
  static getFileInfo(file: File, fileinfo: FileInfo = new FileInfo()): FileInfo {
    fileinfo.file = file
    fileinfo.size = file.size
    fileinfo.suffix = Tools.getFileSuffix(file.name)
    fileinfo.type = Tools.getFileType(file.type)
    fileinfo.name = file.name
    fileinfo.fulltype = file.type
    return fileinfo
  }

  /**
   * 获取文件扩展名
   * @param filename 文件名
   * @returns 文件扩展名
   */
  static getFileSuffix(filename: string) {
    let pos = filename.lastIndexOf('.')
    let suffix = ''
    if (pos != -1) {
      suffix = filename.substring(pos)
    }
    return suffix
  }

  /**
   * 获取大类型名称
   * @param type 文件类型
   * @returns 大类型名称
   */
  static getFileType(type: string) {
    let index = type.indexOf('/')
    return index > -1 ? type.substring(0, index) : type
  }

  /**
   * 读取图片信息到dataurl
   * @param file 图片文件
   * @param cb 获取文件内容的回调处理函数
   */
  static readImg(file: File, cb: Function) {
    if (Tools.getFileType(file.type) != 'image') {
      cb('')
      return
    }
    let reader = new FileReader()
    reader.addEventListener('load', () => {
      cb(reader.result)
    })

    reader.readAsDataURL(file)
  }

  /**
   * 格式化显示文件大小
   * @param filesize 文件大小
   * @returns 格式化文件大小信息
   */
  static formatFileSize(filesize: number) {
    console.log('in filesize====>', filesize)
    if (filesize === 0) {
      return '0 B'
    }
    let k = 1024
    //单位值
    let sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB']
    let i = Math.floor(Math.log(filesize) / Math.log(k))
    //.toPrecision(3)
    return (filesize / Math.pow(k, i)).toFixed(1) + '' + sizes[i]
  }

  /**
   * 浏览文件，通过回调返回文件
   * @param cb 回调函数
   * @param accept 文件类型过滤
   */
  static openFile(cb: Function, accept: string = '') {
    let file = document.createElement('input')
    file.setAttribute('type', 'file')
    file.setAttribute('accept', accept)
    file.setAttribute('multiple', 'multiple')
    // 文件改变事件
    file.addEventListener('change', () => {
      console.log('文件选中变化', file.files)
      let files = new Array<FileInfo>()
      if (file.files && file.files.length > 0) {
        for (let index = 0; index < file.files.length; index++) {
          const elefile = file.files[index]
          let result = Tools.getFileInfo(elefile)
          result.el = file
          files.push(Tools.getFileInfo(elefile))
        }
      }
      cb(files)
    })
    file.click()
  }
  //#endregion
}

/**
 * 文件信息
 */
class FileInfo {
  el: HTMLElement | null = null
  file: File | null = null
  type: string = ''
  fulltype: string = ''
  size: number = 0
  suffix: string = ''
  name: string = ''
}

export default Tools
export { Tools as Tools, FileInfo as FileInfo }
