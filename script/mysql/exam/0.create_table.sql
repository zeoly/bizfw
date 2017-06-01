drop table bf_subject;
drop table bf_question;

CREATE TABLE `bf_subject` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_subject` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '科目名',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父科目id',
  PRIMARY KEY (`id_bf_subject`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='科目表';

CREATE TABLE `bf_question` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_question` varchar(32) NOT NULL COMMENT '主键',
  `id_bf_subject` varchar(32) NOT NULL COMMENT '所属科目id',
  `question_body` varchar(1024) NOT NULL COMMENT '问题体',
  `answer` varchar(128) NOT NULL COMMENT '正确答案',
  `type` varchar(2) NOT NULL COMMENT '问题类型',
  `difficulty` varchar(2) NOT NULL COMMENT '问题难度',
  PRIMARY KEY (`id_bf_question`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题表';
