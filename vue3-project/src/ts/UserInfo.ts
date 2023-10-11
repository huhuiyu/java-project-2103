// 用户相关信息

/**
 * 用户基本信息
 */
class TbUser {
  accessKey: string | null = null
  enable: string | null = null
  lastupdate: number | null = null
  nickname: string | null = null
  password: string | null = null
  role: string | null = null
  uid: number | null = null
  username: string | null = null

  constructor() {}
}

/**
 * 用户附加信息
 */
class TbUserInfo {
  email: string | null = null
  img: string | null = null
  lastupdate: number | null = null
  info: string | null = null
  phone: string | null = null
  qq: string | null = null
  sex: string | null = null
  wechat: string | null = null
  uid: number | null = null

  constructor() {}
}

/**
 * 用户其它信息
 */
class UserOtherInfo {
  follow: number | null = null
  followMe: number | null = null
  followMine: boolean = false
  message: number | null = null
  mineFollow: boolean = false
  regDays: number | null = null
  reply: number | null = null
  supporteMessage: number | null = null
  supporteReply: number | null = null
  totalHits: number | null = null

  constructor() {}
}

export { TbUser as default, TbUser as TbUser, TbUserInfo as TbUserInfo, UserOtherInfo as UserOtherInfo }
