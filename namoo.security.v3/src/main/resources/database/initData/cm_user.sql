
insert into `cm_user`(`usr_no`,`name`,`tel_no`,`email`,`position`,`reg_date`,`mod_date`,
`birth_date`,`zipcode`,`address1`,`address2`,`image`,`image_thumbnail`,`img_type`,`img_updated_date`,`engineer_rating`,
`work_experience_year`,`work_experience_month`,`skill`)
values
(1,'관리자','0934',null,'-','2009-07-17 00:00:00','2009-11-26 00:00:00',
null,null,null,null,null,null,null,null,null,
null,null,null);
;

-- password: 1234
insert into `cm_user_account`(`usr_no`,`login_id`,`passwd`,`status`,
`expired_date`,`passwd_chg_date`)
values
(1,'admin','cRDtpNCeBiql5KOQsKVyrA0sAiA=','STS_RUN',
null,'2010-03-23 00:00:00');
;

INSERT INTO `cm_user_no_seq`(`category`, `curr_seq`) 
values ('CM_USER_USER_NO', 1)
;


commit;