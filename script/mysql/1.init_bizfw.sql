-- root机构
INSERT INTO `bf_department` (`create_by`,`create_date`,`update_by`,`update_date`,`id_bf_department`,`code`,`name`,`level`) VALUES ('admin',sysdate(),'admin',sysdate(),'8a808087583fa7b701583faadf300000','root','root',0);
-- admin用户
INSERT INTO `bf_people` (`create_date`,`create_by`,`update_date`,`update_by`,`id_bf_people`,`code`,`name`,`password`,`id_bf_department`,`status`,`error_count`) VALUES (sysdate(),'admin',sysdate(),'admin','8a808088579f5d5501579f5d99790000','admin','超级管理员','21218CCA77804D2BA1922C33E0151105','8a808087583fa7b701583faadf300000','1',0);
-- 超管角色
INSERT INTO `bf_role` (`create_by`,`create_date`,`update_by`,`update_date`,`id_bf_role`,`name`,`description`) VALUES ('admin',sysdate(),'admin',sysdate(),'8a8080875825fe41015825ff02d60002','超级管理员','超级管理员');
-- 用户角色关系
INSERT INTO `bf_people_role_rel` (`create_by`,`create_date`,`update_by`,`update_date`,`id_bf_people_role_rel`,`id_bf_people`,`id_bf_role`) VALUES ('admin',sysdate(),'admin',sysdate(),'8a8080875825fe41015825ff07eb0003','8a808088579f5d5501579f5d99790000','8a8080875825fe41015825ff02d60002');
-- 所有菜单
INSERT INTO `bf_menu` (`create_by`,`create_date`,`update_by`,`update_date`,`id_bf_menu`,`name`,`url`,`orders`,`parent_menu_id`) VALUES ('admin',sysdate(),'admin',sysdate(),'8a80808758354eb20158354ee4240000','root',null,'000000',null);
-- 各种系统菜单，角色对应各种菜单

-- 跟文件夹
INSERT INTO `bf_document` (`create_by`,`create_date`,`update_by`,`update_date`,`id_bf_document`,`name`,`type`,`path`,`url`,`memo`,`owner_document_id`,`revision`,`download_count`) VALUES ('admin','2016-12-29 00:48:17','admin','2016-12-29 00:48:17','8a808087583fa7b701583faadf300001','/','0','/',NULL,NULL,NULL,NULL,NULL);
