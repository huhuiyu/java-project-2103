let tools = {
  concatJson: (...jsons: any) => {
    let json: any = {}
    for (let index = 0; index < jsons.length; index++) {
      let element = jsons[index]
      for (let key in element) {
        json[key] = element[key]
      }
    }
    return json
  },
  formatDate(value: Date | Number, format = 'yyyy-MM-dd hh:mm:ss') {
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
  },
  async copyText(text: string) {
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
  },
}

export { tools as default, tools as tools }
