use db_student;

-- 用户基本信息表
drop table if exists tb_user;

create table tb_user
(
  user_id integer primary key auto_increment not null comment '用户编号' ,
  username varchar(20) unique not null comment '登录名称' ,
  password varchar(50) not null comment '登录密码' ,
  salt varchar(10) not null comment '随机密码加密盐' ,
  enable enum('y','n') not null default 'y' comment '账号是否启用',
  lastupdate timestamp default now() not null comment '用户注册时间'
)comment '用户信息表';

select * from tb_user;

-- 用户附件信息表
drop table if exists tb_user_info;

create table tb_user_info
(
  user_id integer primary key not null comment '用户编号' ,
  nickname varchar(255) not null default '' comment '用户姓名' ,
  sex enum('m','f','n') not null default 'n' 
    comment '性别，m：男，f：女，n：保密' ,
  email varchar(255) not null default '' comment '邮箱' ,
  phone varchar(50) not null default '' comment '手机号' ,
  imgurl varchar(255) not null default '' comment '头像的url地址' ,
  lastupdate timestamp on update now() default now() not null 
    comment '信息修改时间'
)comment '用户附件信息表';

select * from tb_user_info;
