drop table bf_people;
drop table bf_department;
drop table bf_department_rel;
drop table bf_role;
drop table bf_people_role_rel;
drop table bf_menu;
drop table bf_role_menu_rel;
drop table bf_document;
drop table bf_role_document_rel;

CREATE TABLE `bf_people` (
  `create_date` datetime NOT NULL,
  `create_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `id_bf_people` varchar(32) NOT NULL COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '用户代码',
  `name` varchar(32) DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `id_bf_department` varchar(32) DEFAULT NULL COMMENT '机构编码',
  `status` varchar(1) NOT NULL COMMENT '人员状态（0-无效，1-正常，2-锁定，3-待审核）',
  `error_count` int(11) DEFAULT '0' COMMENT '密码错误次数',
  PRIMARY KEY (`id_bf_people`),
  UNIQUE KEY `id_bizfw_people_info_UNIQUE` (`id_bf_people`),
  UNIQUE KEY `peopleCode_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员表';

CREATE TABLE `bf_department` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_department` varchar(45) NOT NULL COMMENT '主键',
  `code` varchar(32) DEFAULT NULL COMMENT '机构代码',
  `name` varchar(32) DEFAULT NULL COMMENT '机构名',
  `level` int(11) DEFAULT NULL COMMENT '机构等级，root为0',
  `parent_dept_id` varchar(32) DEFAULT NULL COMMENT '父机构id',
  PRIMARY KEY (`id_bf_department`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

CREATE TABLE `bf_department_rel` (
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_bf_department_rel` varchar(32) NOT NULL COMMENT '主键',
  `id_parent_department` varchar(32) DEFAULT NULL COMMENT '父机构代码',
  `id_child_department` varchar(32) DEFAULT NULL COMMENT '子机构代码',
  `parent_level` int(11) DEFAULT NULL COMMENT '父机构等级',
  PRIMARY KEY (`id_bf_department_rel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构关联关系表';

CREATE TABLE `bf_role` (
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_bf_role` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `description` varchar(1024) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id_bf_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `bf_people_role_rel` (
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_bf_people_role_rel` varchar(32) NOT NULL COMMENT '主键',
  `id_bf_people` varchar(32) DEFAULT NULL COMMENT '人员id',
  `id_bf_role` varchar(32) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id_bf_people_role_rel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员角色关联表';

CREATE TABLE `bf_menu` (
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_bf_menu` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(128) DEFAULT NULL COMMENT '菜单名',
  `url` varchar(512) DEFAULT NULL COMMENT '页面路径url',
  `orders` varchar(8) DEFAULT NULL COMMENT '顺序',
  `parent_menu_id` varchar(32) DEFAULT NULL COMMENT '父菜单id',
  PRIMARY KEY (`id_bf_menu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

CREATE TABLE `bf_role_menu_rel` (
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_bf_role_menu_rel` varchar(32) NOT NULL COMMENT '主键',
  `id_bf_role` varchar(32) DEFAULT NULL COMMENT '角色id',
  `id_bf_menu` varchar(32) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id_bf_role_menu_rel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

CREATE TABLE `bf_document` (
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_bf_document` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(128) DEFAULT NULL COMMENT '文件夹名',
  `extension` varchar(16) DEFAULT NULL COMMENT '扩展名',
  `type` varchar(2) DEFAULT NULL COMMENT '类型，1-文件夹，2-文件',
  `path` varchar(1024) DEFAULT NULL COMMENT '路径',
  `url` varchar(1024) DEFAULT NULL COMMENT '文件存储路径',
  `size` int(11) DEFAULT NULL COMMENT '文件大小',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `owner_document_id` varchar(32) DEFAULT NULL COMMENT '所属文档id',
  `revision` int(11) DEFAULT NULL COMMENT '文件版本号',
  `download_count` int(11) DEFAULT NULL COMMENT '下载次数',
  `md5` varchar(32) DEFAULT NULL COMMENT '文件md5值',
  PRIMARY KEY (`id_bf_document`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档表';

CREATE TABLE `bf_role_document_rel` (
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_bf_role_document_rel` varchar(32) NOT NULL COMMENT '主键',
  `id_bf_role` varchar(32) DEFAULT NULL COMMENT '角色id',
  `id_bf_document` varchar(32) DEFAULT NULL COMMENT '文档id',
  `auth` varchar(4) DEFAULT 'r' COMMENT '权限，r-读，w-写',
  PRIMARY KEY (`id_bf_role_document_rel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色文档关系表';

CREATE TABLE `bf_document_opt_log` (
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `id_bf_document_opt_log` varchar(45) NOT NULL COMMENT '主键',
  `operation` varchar(2) DEFAULT NULL COMMENT '操作类型，1-新增，2-修改，3-删除',
  `target_type` varchar(2) DEFAULT NULL COMMENT '操作目标类型，0-文件夹，1-文件',
  `target_id` varchar(32) DEFAULT NULL COMMENT '操作对象主键',
  `target_name` varchar(512) DEFAULT NULL COMMENT '操作对象名',
  PRIMARY KEY (`id_bf_document_opt_log`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档操作日志表';

CREATE TABLE `bf_file_deleted` (
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `id_bf_file_deleted` varchar(32) NOT NULL COMMENT '主键',
  `url` varchar(1024) DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id_bf_file_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='已删除文件表';
