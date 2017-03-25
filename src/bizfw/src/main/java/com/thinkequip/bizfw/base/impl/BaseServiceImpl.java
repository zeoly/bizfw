package com.thinkequip.bizfw.base.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.thinkequip.bizfw.base.BaseService;
import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.consts.ErrorCode;

@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {

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

}
