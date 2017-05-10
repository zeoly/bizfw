package com.thinkequip.bizfw.base.consts;

/**
 * 异常码常量定义
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月18日
 */
public class ErrorCode {

	/** 系统默认异常 */
	public static final String DEFAULT_ERROR = "000000";

	/** 参数为空异常--{0}为空，{1}失败 */
	public static final String NULL_PARAM = "000001";

	public static final String TEST = "999999";

	/** 机构人员模块前缀 */
	public static final String PO_PREFIX = "01";

	/** 权限模块前缀 */
	public static final String AUTH_PREFIX = "02";

	/** 文档模块前缀 */
	public static final String DOC_PREFIX = "03";

	/**
	 * 机构人员模块
	 * 
	 * @author zengyongli
	 * @date 2016年11月10日
	 */
	public class PeopleDept {

		/** 人员模块前缀 */
		public static final String PEOPLE_PREFIX = PO_PREFIX + "00";

		/** 机构模块前缀 */
		public static final String DEPT_PREFIX = PO_PREFIX + "01";

		/** 人员模块 */
		public class People {

			/** 非锁定状态，解锁失败 */
			public static final String UNLOCK_FAIL_STATUS_ERR = PEOPLE_PREFIX + "00";

			/** 已存在相同code人员，添加人员失败--人员[{0}]已存在，新增人员失败 */
			public static final String ADD_FAIL_EXISTED = PEOPLE_PREFIX + "01";

			/** 设置人员角色失败，角色不存在--设置人员角色失败，未知角色[{0}] */
			public static final String SET_ROLE_REL_FAIL_NOT_FOUND = PEOPLE_PREFIX + "02";

			/** 未找到机构，添加人员失败 */
			public static final String ADD_FAIL_WITHOUT_DEPT = PEOPLE_PREFIX + "03";

			/** 人员code重复，查询人员失败 */
			public static final String QUERY_FAIL_DUPLICATED = PEOPLE_PREFIX + "04";

			/** 密码错误，修改密码失败 */
			public static final String UPDATE_FAIL_PWD_ERR = PEOPLE_PREFIX + "05";

			/** 删除自己 */
			public static final String DEL_FAIL_SELF = PEOPLE_PREFIX + "06";
		}

		/** 机构模块 */
		public class Dept {

			/** 包含子机构，删除失败 */
			public static final String DEL_FAIL_WITH_CHILD = DEPT_PREFIX + "00";

			/** 机构下有人员，删除失败 */
			public static final String DEL_FAIL_WITH_PEOPLE = DEPT_PREFIX + "01";
		}
	}

	/**
	 * 权限模块
	 * 
	 * @author zengyongli
	 * @date 2016年11月12日
	 */
	public class Auth {

		/** 菜单模块前缀 */
		public static final String MENU_PREFIX = AUTH_PREFIX + "00";

		/** 角色模块前缀 */
		public static final String ROLE_PREFIX = AUTH_PREFIX + "01";

		/**
		 * 菜单模块
		 * 
		 * @author zengyongli
		 * @date 2016年11月13日
		 */
		public class Menu {

			/** 未指定父菜单，添加失败 */
			public static final String ADD_FAIL_WITHOUT_PARENT = MENU_PREFIX + "00";

			/** 包含子菜单，删除失败 */
			public static final String DEL_FAIL_WITH_CHILD = MENU_PREFIX + "01";
		}

		/**
		 * 角色模块
		 * 
		 * @author zengyongli
		 * @date 2016年11月13日
		 */
		public class Role {

			/** 已有重名角色，添加失败 */
			public static final String ADD_FAIL_EXISTED = ROLE_PREFIX + "00";

			/** 角色有绑定人员，删除失败 */
			public static final String DEL_FAIL_WITH_PEOPLE = ROLE_PREFIX + "01";

			/** 未找到菜单，角色菜单绑定失败--设置角色菜单失败，未知菜单[{0}] */
			public static final String SET_MENU_REL_FAIL_MENU_NOT_FOUND = ROLE_PREFIX + "02";

			/** 已有重名角色，修改失败 */
			public static final String MOD_FAIL_EXISTED = ROLE_PREFIX + "03";
		}
	}

	/**
	 * 文档模块
	 * 
	 * @author zengyongli
	 * @date 2016年12月12日
	 */
	public class Doc {

		/** 文件夹模块前缀 */
		public static final String FOLDER_PREFIX = DOC_PREFIX + "00";

		/** 文件模块前缀 */
		public static final String FILE_PREFIX = DOC_PREFIX + "01";

		/**
		 * 文件夹模块
		 * 
		 * @author zengyongli
		 * @date 2016年12月12日
		 */
		public class Folder {

			/** 有子文件夹或文件，删除文件夹失败 */
			public static final String DEL_FAIL_WITH_CHILD = FOLDER_PREFIX + "00";
		}

		public class File {

			/** 无权访问文件夹下内容 */
			public static final String ACCESS_FAIL_NO_AUTH = FILE_PREFIX + "00";
		}

	}
}
