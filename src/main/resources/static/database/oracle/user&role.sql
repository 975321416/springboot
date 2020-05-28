-- oracle 数据库建表语句
-- 注意：需要根据实际表空间修改表空间，字段需要调整自行添加。
-- 用户表 ，sys_user
-- Create table
create table SYS_USER
(
  user_id        VARCHAR2(100) not null,
  username       VARCHAR2(100) not null,
  cnname         VARCHAR2(100),
  password       VARCHAR2(100) not null,
  email          VARCHAR2(100),
  mobile         VARCHAR2(100),
  status         VARCHAR2(32) not null,
  create_user_id VARCHAR2(100) not null,
  create_time    VARCHAR2(100) not null,
  update_time    VARCHAR2(100)
)
tablespace USERS
pctfree 10
initrans 1
maxtrans 255
storage
(
initial 64K
next 1M
minextents 1
maxextents unlimited
);
-- Add comments to the columns
comment on column SYS_USER.user_id
is 'ID';
comment on column SYS_USER.username
is '用户名';
comment on column SYS_USER.cnname
is '中文名';
comment on column SYS_USER.password
is '密码';
comment on column SYS_USER.email
is '邮箱';
comment on column SYS_USER.mobile
is '手机号';
comment on column SYS_USER.status
is '状态  0：禁用   1：正常';
comment on column SYS_USER.create_user_id
is '创建者ID';
comment on column SYS_USER.create_time
is '创建时间';
comment on column SYS_USER.update_time
is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table SYS_USER
  add primary key (USER_ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
  initial 64K
  next 1M
  minextents 1
  maxextents unlimited
  );
alter table SYS_USER
  add unique (USERNAME)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
  initial 64K
  next 1M
  minextents 1
  maxextents unlimited
  );


-- 角色表，sys_role
-- Create table
create table SYS_ROLE
(
  role_id        VARCHAR2(100) not null,
  role_name      VARCHAR2(100) not null,
  remark         VARCHAR2(100),
  create_user_id VARCHAR2(100),
  create_time    VARCHAR2(100)
)
tablespace USERS
pctfree 10
initrans 1
maxtrans 255
storage
(
initial 64K
next 1M
minextents 1
maxextents unlimited
);
-- Add comments to the columns
comment on column SYS_ROLE.role_id
is '角色ID';
comment on column SYS_ROLE.role_name
is '角色名称';
comment on column SYS_ROLE.remark
is '备注';
comment on column SYS_ROLE.create_user_id
is '创建用户id';
comment on column SYS_ROLE.create_time
is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table SYS_ROLE
  add primary key (ROLE_ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
  initial 64K
  next 1M
  minextents 1
  maxextents unlimited
  );


-- 用户和角色对应表 SYS_USER_ROLE
-- Create table
create table SYS_USER_ROLE
(
  id      VARCHAR2(100) not null,
  user_id VARCHAR2(100) not null,
  role_id VARCHAR2(100) not null
)
tablespace USERS
pctfree 10
initrans 1
maxtrans 255
storage
(
initial 64K
next 1M
minextents 1
maxextents unlimited
);
-- Add comments to the columns
comment on column SYS_USER_ROLE.id
is 'ID';
comment on column SYS_USER_ROLE.user_id
is '用户ID';
comment on column SYS_USER_ROLE.role_id
is '角色ID';
-- Create/Recreate primary, unique and foreign key constraints
alter table SYS_USER_ROLE
  add primary key (ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
  initial 64K
  next 1M
  minextents 1
  maxextents unlimited
  );
