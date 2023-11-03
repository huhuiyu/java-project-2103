-- 被管理api地址信息
truncate table tb_auth_api;

insert into tb_auth_api(url,method,info)
values('/test/userAuth','get',"测试用户授权访问");

insert into tb_auth_api(url,method,info)
values('/test/adminAuth','get',"测试管理员授权访问");

insert into tb_auth_api(url,method,info)
values('/test/auth/{info}','get',"测试带路径参数的授权访问");

select * from tb_auth_api;

-- api对应的角色授权信息
truncate table tb_auth_role_api;

-- role_id是角色，api_id是被管理的api编号
insert into tb_auth_role_api(role_id,api_id) values(2,1);
insert into tb_auth_role_api(role_id,api_id) values(1,2);
insert into tb_auth_role_api(role_id,api_id) values(1,3);
insert into tb_auth_role_api(role_id,api_id) values(2,3);

select * from tb_auth_role_api;


-- 授权信息关联查询
select a.*,b.*,c.* from tb_auth_role_api a
inner join tb_auth_role b on a.role_id = b.role_id
inner join tb_auth_api c on a.api_id = c.api_id;
