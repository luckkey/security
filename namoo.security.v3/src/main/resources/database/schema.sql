-- User, User Account start

create table cm_user(
    usr_no                integer 		not null,
    name                  varchar(100)	not null,
    tel_no                varchar(50) 	null,
    email                 varchar(50) 	null,
    position              varchar(50) 	null,
    reg_date              timestamp 	default 0 not null,
    mod_date              timestamp 	null,
    birth_date            date 			null,
    zipcode               char(7) 		null,
    address1              varchar(100) 	null,
    address2              varchar(100) 	null,
    image                 longblob 		null,
    image_thumbnail       longblob 		null,
    img_type              varchar(50) 	null,
    img_updated_date      timestamp 	null,
    engineer_rating       varchar(50) 	null,
    work_experience_year  integer 		null,
    work_experience_month integer 		null,
    skill                 varchar(200) 	null,
    company_no            integer        null,
    dept_no               integer        null,
    del_yn                char(1)       default 'N' not null
);

alter table cm_user
    add  primary key (usr_no);


create table cm_user_account(
    usr_no                integer 		not null,
    login_id              varchar(20) 	not null,
    passwd                varchar(50) 	not null,
    status                varchar(50) 	null,
    expired_date          timestamp 	null,
    passwd_chg_date       timestamp 	null,
    lang				  varchar(2)   null,
    timezone              varchar(50)  null,
    reserved              varchar(50)  null
);

alter table cm_user_account
    add  primary key (usr_no);

alter table cm_user_account
    add foreign key fk_cm_account_user (usr_no) references cm_user(usr_no);

create index `idx_cm_user_account_login_id` using btree on cm_user_account (login_id);

-- Role

create table sec_role
(
	rol_id                varchar(100) not null,
	default_page          varchar(100) null,
	note                  varchar(200) null,
	rol_type              char(2),
	priority 			  integer null default 1
)
;

create unique index pk_sec_role on sec_role
(
	rol_id
)
;

alter table sec_role
	add  primary key (rol_id)
;

create table sec_authority
(
	rol_id                varchar(100) not null,
	rsc_id                integer not null,
	read_yn               char(1) not null default 'N',
	write_yn 			  char(1) not null default 'N',
	f1 					  char(1) not null default 'N',
	f2 					  char(1) not null default 'N',
	f3 					  char(1) not null default 'N',
	f4 					  char(1) not null default 'N',
	f5 					  char(1) not null default 'N',
	f6 					  char(1) not null default 'N',
	f7 					  char(1) not null default 'N',
	f8 					  char(1) not null default 'N',
	f9 					  char(1) not null default 'N',
	f10 				  char(1) not null default 'N'
)
;



create unique index pk_sec_authority on sec_authority
(
	rol_id,
	rsc_id
)
;



alter table sec_authority
	add  primary key (rol_id,rsc_id)
;



create index idx_sec_authority_rol on sec_authority
(
	rol_id
)
;



create index idx_sec_authority_rsc on sec_authority
(
	rsc_id
)
;

create table sec_resource
(
	rsc_id                integer not null,
	rsc_urn               varchar(50) null,
	parent_rsc_id         integer null,
	kind                  varchar(20) not null,
	name                  varchar(200) not null,
	uri                   varchar(300) not null,
	seq                   integer not null,
	note                  varchar(200) null,
	mu_type 			  char(2) not null,
	f1 					  varchar(100) null,
	f2 					  varchar(100) null,
	f3 					  varchar(100) null,
	f4 					  varchar(100) null,
	f5 					  varchar(100) null,
	f6 					  varchar(100) null,
	f7 					  varchar(100) null,
	f8 					  varchar(100) null,
	f9 					  varchar(100) null,
	f10 				  varchar(100) null,
	`key` 				  integer DEFAULT '15'
)
;



create unique index pk_sec_resource on sec_resource
(
	rsc_id
)
;



alter table sec_resource
	add  primary key (rsc_id)
;

create table biz_mu_role
(
	usr_no                integer not null,
	rol_id                varchar(100) not null,
	mu_type               char(2) not null,
	mu_id                 integer not null
);

create unique index idx_pk_biz_mu_role on biz_mu_role(	usr_no,	rol_id,	mu_type,	mu_id);

alter table biz_mu_role add  primary key (usr_no,rol_id,mu_type,mu_id);

create index idx_fk_mu_role_user on biz_mu_role(	usr_no);

create index idx_fk_mu_role_role on biz_mu_role(	rol_id);

create index idx_fk_mu_role_mu_meta on biz_mu_role(	mu_type,	mu_id);

