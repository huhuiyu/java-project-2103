/*=============================================================================*/
drop table if exists tb_info;
create table tb_info
(
  id integer auto_increment primary key comment '编号',
  title varchar(255) not null comment '标题',
  info varchar(255) not null comment '信息',
  hits integer not null default 0 comment '点击数',
  deleted enum('y','n') not null default 'n' comment '是否删除',
  lastupdate timestamp on update now() default now() not null comment '信息最后修改时间'
)comment '带逻辑删除的信息表';

select * from tb_info;


select a.*,b.*
from tb_auth_user a
inner join tb_auth_user_info b on a.user_id = b.user_id;
