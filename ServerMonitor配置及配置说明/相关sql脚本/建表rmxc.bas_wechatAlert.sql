-- Create table
create table RMXC.BAS_WECHATALERT
(
  alertid      NUMBER(10) primary key,
  alerttype    VARCHAR2(50) not null,
  alertdetail  VARCHAR2(500),
  alertvalue   VARCHAR2(128),
  value        VARCHAR2(128),
  alertstatus  NUMBER(10) default 0 not null,
  alertdate    DATE default sysdate,
  remark       VARCHAR2(2000),
  weixinstatus NUMBER(10) default 0,
  weixindate   DATE
);

-- Add comments to the columns 
comment on column RMXC.BAS_WECHATALERT.alertid
  is '告警ID';
comment on column RMXC.BAS_WECHATALERT.alerttype
  is '告警类型';
comment on column RMXC.BAS_WECHATALERT.alertdetail
  is '故障描述';
comment on column RMXC.BAS_WECHATALERT.alertvalue
  is '监控数值';
comment on column RMXC.BAS_WECHATALERT.value
  is '监控阀值';
comment on column RMXC.BAS_WECHATALERT.alertstatus
  is '告警状态值(0：业务正常；
1：业务异常；)';
comment on column RMXC.BAS_WECHATALERT.alertdate
  is '告警时间';
comment on column RMXC.BAS_WECHATALERT.remark
  is '备注';
comment on column RMXC.BAS_WECHATALERT.weixinstatus
  is '微信监控状态值';
comment on column RMXC.BAS_WECHATALERT.weixindate
  is '微信记录告警时间';
-- Create/Recreate primary, unique and foreign key constraints 

