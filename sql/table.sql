use db_student;

-- 删除表
drop table if exists tb_dept;

-- 创建表
create table tb_dept
(
  dept_id integer primary key auto_increment not null comment '部门编号' ,
  dept_name varchar(20) unique not null comment '部门名称' ,
  dept_info varchar(200) not null comment '部门描述' ,
  lastupdate timestamp default now() not null comment '信息最后修改时间'
)comment '部门信息表';

insert into tb_dept(dept_name,dept_info)
  values('开发部','非常吊的部门');

insert into tb_dept(dept_name,dept_info)
  values('人事部','hr');

select * from tb_dept;







-- 删除表
drop table if exists tb_employee;

-- 创建表
create table tb_employee
(
  eid integer primary key auto_increment not null comment '员工编号' ,
  dept_id integer not null comment '所属部门的编号',
  emp_name varchar(20) not null comment '员工姓名',
  sex enum('f','m','n') default 'n' not null comment '性别',
  lastupdate timestamp default now() not null comment '信息最后修改时间'
)comment '员工信息表';

insert into tb_employee(dept_id,emp_name,sex)
  values(1,'张三','m');

insert into tb_employee(dept_id,emp_name,sex)
  values(1,'李四','f');

insert into tb_employee(dept_id,emp_name,sex)
  values(2,'王五','f');

select * from tb_employee;
