/* 新闻表 */
/*=============================================================================*/
drop table if exists tb_news;
create table tb_news
(
  nid integer auto_increment primary key comment '新闻编号',
  title varchar(255) not null comment '标题',
  info text comment '内容',
  source varchar(255) comment '来源',
  lastupdate timestamp on update now() default now() not null comment '信息最后修改时间'
)comment '新闻表';

select * from tb_news;
/*=============================================================================*/
