CREATE TABLE `cm_user` (
	`usr_no` INT(11) NOT NULL,
	`name` VARCHAR(100) NOT NULL,
	`tel_no` VARCHAR(50) NULL DEFAULT NULL,
	`email` VARCHAR(50) NULL DEFAULT NULL,
	`position` VARCHAR(50) NULL DEFAULT NULL,
	`reg_date` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
	`mod_date` TIMESTAMP NULL DEFAULT NULL,
	`birth_date` DATE NULL DEFAULT NULL,
	`zipcode` CHAR(7) NULL DEFAULT NULL,
	`address1` VARCHAR(100) NULL DEFAULT NULL,
	`address2` VARCHAR(100) NULL DEFAULT NULL,
	`image` LONGBLOB NULL,
	`image_thumbnail` LONGBLOB NULL,
	`img_type` VARCHAR(50) NULL DEFAULT NULL,
	`img_updated_date` TIMESTAMP NULL DEFAULT NULL,
	`engineer_rating` VARCHAR(50) NULL DEFAULT NULL,
	`work_experience_year` INT(11) NULL DEFAULT NULL,
	`work_experience_month` INT(11) NULL DEFAULT NULL,
	`skill` VARCHAR(200) NULL DEFAULT NULL,
	`company_no` INT(11) NULL DEFAULT NULL,
	`dept_no` INT(11) NULL DEFAULT NULL,
	`del_yn` CHAR(1) NOT NULL DEFAULT 'N',
	PRIMARY KEY (`usr_no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `cm_user_account` (
	`usr_no` INT(11) NOT NULL,
	`login_id` VARCHAR(20) NOT NULL,
	`passwd` VARCHAR(50) NOT NULL,
	`status` VARCHAR(50) NULL DEFAULT NULL,
	`expired_date` TIMESTAMP NULL DEFAULT NULL,
	`passwd_chg_date` TIMESTAMP NULL DEFAULT NULL,
	`lang` VARCHAR(2) NULL DEFAULT NULL,
	`timezone` VARCHAR(50) NULL DEFAULT NULL,
	`reserved` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`usr_no`),
	INDEX `idx_cm_user_account_login_id` (`login_id`) USING BTREE,
	CONSTRAINT `fk_cm_account_user` FOREIGN KEY (`usr_no`) REFERENCES `cm_user` (`usr_no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `sec_role` (
	`rol_id` VARCHAR(100) NOT NULL,
	`default_page` VARCHAR(100) NULL DEFAULT NULL,
	`note` VARCHAR(200) NULL DEFAULT NULL,
	`rol_type` CHAR(2) NULL DEFAULT NULL,
	`priority` INT(11) NULL DEFAULT '1',
	PRIMARY KEY (`rol_id`),
	UNIQUE INDEX `pk_sec_role` (`rol_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `biz_mu_role` (
	`usr_no` INT(11) NOT NULL,
	`rol_id` VARCHAR(100) NOT NULL,
	`mu_type` CHAR(2) NOT NULL,
	`mu_id` INT(11) NOT NULL,
	PRIMARY KEY (`usr_no`, `rol_id`, `mu_type`, `mu_id`),
	UNIQUE INDEX `idx_pk_biz_mu_role` (`usr_no`, `rol_id`, `mu_type`, `mu_id`),
	INDEX `idx_fk_mu_role_user` (`usr_no`),
	INDEX `idx_fk_mu_role_role` (`rol_id`),
	INDEX `idx_fk_mu_role_mu_meta` (`mu_type`, `mu_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `sec_resource` (
	`rsc_id` INT(11) NOT NULL,
	`rsc_urn` VARCHAR(50) NULL DEFAULT NULL,
	`parent_rsc_id` INT(11) NULL DEFAULT NULL,
	`kind` VARCHAR(20) NULL DEFAULT NULL,
	`name` VARCHAR(200) NULL DEFAULT NULL,
	`uri` VARCHAR(300) NULL DEFAULT NULL,
	`seq` INT(11) NULL DEFAULT NULL,
	`note` VARCHAR(200) NULL DEFAULT NULL,
	`mu_type` CHAR(2) NULL DEFAULT NULL,
	`key` INT(11) NULL DEFAULT '15',
	PRIMARY KEY (`rsc_id`),
	UNIQUE INDEX `pk_sec_resource` (`rsc_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `sec_authority` (
	`rol_id` VARCHAR(100) NOT NULL,
	`rsc_id` INT(11) NOT NULL,
	`read_yn` CHAR(1) NOT NULL DEFAULT 'N',
	`write_yn` CHAR(1) NOT NULL DEFAULT 'N',
	PRIMARY KEY (`rol_id`, `rsc_id`),
	UNIQUE INDEX `pk_sec_authority` (`rol_id`, `rsc_id`),
	INDEX `idx_sec_authority_rol` (`rol_id`),
	INDEX `idx_sec_authority_rsc` (`rsc_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

