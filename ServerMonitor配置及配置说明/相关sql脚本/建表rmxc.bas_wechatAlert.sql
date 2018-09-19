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
  is '�澯ID';
comment on column RMXC.BAS_WECHATALERT.alerttype
  is '�澯����';
comment on column RMXC.BAS_WECHATALERT.alertdetail
  is '��������';
comment on column RMXC.BAS_WECHATALERT.alertvalue
  is '�����ֵ';
comment on column RMXC.BAS_WECHATALERT.value
  is '��ط�ֵ';
comment on column RMXC.BAS_WECHATALERT.alertstatus
  is '�澯״ֵ̬(0��ҵ��������
1��ҵ���쳣��)';
comment on column RMXC.BAS_WECHATALERT.alertdate
  is '�澯ʱ��';
comment on column RMXC.BAS_WECHATALERT.remark
  is '��ע';
comment on column RMXC.BAS_WECHATALERT.weixinstatus
  is '΢�ż��״ֵ̬';
comment on column RMXC.BAS_WECHATALERT.weixindate
  is '΢�ż�¼�澯ʱ��';
-- Create/Recreate primary, unique and foreign key constraints 

