/* 部门表 */
/*=============================================================================*/
drop table if exists tb_dept;
create table tb_dept
(
  dept_id integer auto_increment primary key comment '部门编号',
  dept_name varchar(50) unique not null comment '部门名称',
  dept_info varchar(200) comment '部门描述信息',
  enable enum('y','n') default 'y' not null comment '部门是否启用',
  lastupdate timestamp on update now() default now() not null comment '信息最后修改时间'
)comment '部门信息表';
/*=============================================================================*/

/* 员工表 */
/*=============================================================================*/
drop table if exists tb_employee;
create table tb_employee
(
  eid integer auto_increment primary key comment '编号',
  dept_id integer comment '所属部门的部门编号',
  emp_name varchar(20) not null comment '姓名',
  sex enum('m','f') not null comment '性别',
  salary decimal(10,2) not null comment '工资',
  birthday timestamp not null comment '出生日期'
)comment '员工信息表';
/*=============================================================================*/

/* 添加部门信息 */
insert into tb_dept(dept_name,dept_info) values('人事部','人员管理部门');
insert into tb_dept(dept_name,dept_info) values('开发部','最酷炫的，专门搞事情的部门');
insert into tb_dept(dept_name,dept_info) values('测试部','专门找开发部麻烦的部门');
insert into tb_dept(dept_name,dept_info) values('客服部','应付客户的部门');

/* 添加员工信息 */
insert into tb_employee(dept_id,emp_name,sex,salary,birthday) values(3,'张科望','m',5500,'1996-01-09');
insert into tb_employee(dept_id,emp_name,sex,salary,birthday) values(2,'成文明','m',3010,'1993-10-09');
insert into tb_employee(dept_id,emp_name,sex,salary,birthday) values(1,'谢慧梅','f',2100,'1992-12-07');
insert into tb_employee(dept_id,emp_name,sex,salary,birthday) values(2,'赵勇','m',4100,'1993-03-01');
insert into tb_employee(dept_id,emp_name,sex,salary,birthday) values(2,'张三疯','m',3100,'1993-01-01');
insert into tb_employee(dept_id,emp_name,sex,salary,birthday) values(3,'张丽','f',9898,'1994-01-03');
insert into tb_employee(dept_id,emp_name,sex,salary,birthday) values(3,'成芝灵','f',6666,'1995-06-07');
insert into tb_employee(dept_id,emp_name,sex,salary,birthday) values(2,'姚芳','f',7000,'2000-06-19');
insert into tb_employee(dept_id,emp_name,sex,salary,birthday) values(3,'李梅','f',6300,'1998-11-11');
insert into tb_employee(dept_id,emp_name,sex,salary,birthday) values(2,'李明旺','m',8900,'1997-11-11');

-- 查询
select * from tb_dept;

select * from tb_employee;

-- 内连接
select a.*,b.*
from tb_employee a
inner join tb_dept b on a.dept_id = b.dept_id;

select * from tb_employee
where dept_id = 1;


select * from tb_dept
where dept_id in (1,3);

select * from tb_dept
where dept_id = 1 or dept_id = 3;

select * from tb_dept
where dept_id <> 3;


select * from tb_employee
where birthday >= '1993-01-01' and birthday <= '1994-12-31';

select *, year(birthday) from tb_employee;


select * from tb_employee
where year(birthday) >= 1993 and year(birthday) <= 1994;

select * from tb_employee
where year(birthday) between 1993 and 1994;


select * from tb_employee
where emp_name like '张%';

select * from tb_employee
where emp_name like '张_';

select count(*) from tb_employee;

select max(salary),min(salary),avg(salary),sum(salary) 
from tb_employee;



-- 如果select的列出现了聚合函数，那么所有的不在聚合函数中的列
-- 就必须出现在group by中

select count(*),dept_id,sex
from tb_employee
group by dept_id,sex;


select count(*),year(birthday)
from tb_employee
group by year(birthday);





