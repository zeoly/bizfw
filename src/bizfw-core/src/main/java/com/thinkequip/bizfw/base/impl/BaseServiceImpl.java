package com.thinkequip.bizfw.base.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.consts.ErrorCode;
import com.thinkequip.bizfw.base.model.BaseModel;

@Transactional
public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<T> list() {
		return getBaseDao().list();
	}

	@Override
	public String save(T t) {
		return getBaseDao().save(t);
	}

	@Override
	public void delete(String id) {
		getBaseDao().delete(id);
	}

	@Override
	public void update(T t) {
		getBaseDao().update(t);
	}

	@Override
	public T queryById(String id) {
		return getBaseDao().queryById(id);
	}

	@Override
	public List<T> queryByFieldAndValue(String field, Object value) {
		return getBaseDao().queryByFieldAndValue(field, value);
	}

	@Override
	public void checkObjectNotNull(Object object, String target, String operation) throws BizfwServiceException {
		if (object == null) {
			throw new BizfwServiceException(ErrorCode.NULL_PARAM, target, operation);
		}
	}

	@Override
	public String saveWithCache(T t) {
		String id = save(t);
		try {
			getBaseDao().redisSet(id, t);
		} catch (Exception e) {
			logger.error("error save[{}][{}] in cache", getBaseDao().getClazz().getName(), id);
		}
		return id;
	}

	@Override
	public T queryByIdWithCache(String id) {
		T t = null;
		try {
			t = getBaseDao().redisGet(id);
		} catch (Exception e) {
			logger.error("error load[{}][{}] in cache", getBaseDao().getClazz().getName(), id);
		}
		if (t != null) {
			logger.info("load[{}][{}] in cache", getBaseDao().getClazz().getName(), id);
			return t;
		}
		logger.info("load[{}][{}] in cache fail", getBaseDao().getClazz().getName(), id);
		t = queryById(id);
		if (t != null) {
			try {
				getBaseDao().redisSet(id, t);
			} catch (Exception e) {
				logger.error("error save[{}][{}] in cache", getBaseDao().getClazz().getName(), id);
			}
		}
		return t;
	}
}
