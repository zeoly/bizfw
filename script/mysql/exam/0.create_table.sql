drop table bf_subject;
drop table bf_question;

CREATE TABLE `bf_subject` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_subject` varchar(32) NOT NULL COMMENT '����',
  `name` varchar(128) NOT NULL COMMENT '��Ŀ��',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '����Ŀid',
  PRIMARY KEY (`id_bf_subject`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ŀ��';

CREATE TABLE `bf_question` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_question` varchar(32) NOT NULL COMMENT '����',
  `id_bf_subject` varchar(32) NOT NULL COMMENT '������Ŀid',
  `question_body` varchar(1024) NOT NULL COMMENT '������',
  `answer` varchar(128) NOT NULL COMMENT '��ȷ��',
  `type` varchar(2) NOT NULL COMMENT '��������',
  `difficulty` varchar(2) NOT NULL COMMENT '�����Ѷ�',
  PRIMARY KEY (`id_bf_question`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�����';
