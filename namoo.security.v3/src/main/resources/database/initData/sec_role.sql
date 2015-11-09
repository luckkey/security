insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_ADMIN','/applicant','시스템관리자','NO',1);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_DECISION_MAKER','/default','의사결정권자','NO',1);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_DEFAULT','/communication/talks/home','기본사용자','NO',1);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_IN_DEFAULT','/default','내부사용자','NO',1);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_PD_MASTER','product/{pdid}/newrequest','제품책임자','PD',2);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_PD_USER','/product/{pdid}/myrequest','제품관계자','PD',2);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_PE_CHAIRMAN','/default','제안서평가위원장','PJ',4);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_PE_COMMITTEE','/default','제안서평가위원','PJ',4);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_PG_DECISION_MAKER','/program/{pgid}/statistics/wbs','프로그램 의사결정권자','PG',3);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_PG_MASTER','/program/{pgid}/info','프로그램책임자','PG',3);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_PJ_DECISION_MAKER','/project/{pjid}/statistics','프로젝트 의사결정권자','PJ',9);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_PJ_MANAGER','/project/{pjid}/talks','프로젝트관리자','PJ',4);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_PJ_MASTER','/project/{pjid}/talks','프로젝트책임자','PJ',4);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_PJ_TEAM_LEADER','/project/{pjid}/talks','팀장','PJ',12);
insert into sec_role(rol_id,default_page,note,rol_type,priority) values ('ROLE_PJ_TEAM_MEMBER','/project/{pjid}/talks','프로젝트팀원','PJ',4);