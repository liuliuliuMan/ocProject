package com.online.college.core.auth.service.impl;

import com.online.college.common.page.TailPage;

import com.online.college.core.auth.dao.AuthUserDao;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthUserServiceImpl implements IAuthUserService {

	@Autowired
	private AuthUserDao entityDao;

	public void createSelectivity(AuthUser entity){
		entityDao.createSelectivity(entity);
	}
	
	/**
	*根据username获取
	**/
	public AuthUser getByUsername(String username){
		String s = "https://oc-bucket.oss-cn-hangzhou.aliyuncs.com/";
		AuthUser authUser = entityDao.getByUsername(username);
		authUser.setHeader(s + authUser.getHeader());
		return authUser;
	}
	
	
	
	public AuthUser getById(Long id){
		return entityDao.getById(id);
	}
	
	/**
	*根据username和password获取
	**/
	public AuthUser getByUsernameAndPassword(AuthUser authUser){
		return entityDao.getByUsernameAndPassword(authUser);
	}

	@Override
	public List<AuthUser> queryRecomd() {
		List<AuthUser> recomdList = entityDao.queryRecomd();
		String s = "https://oc-bucket.oss-cn-hangzhou.aliyuncs.com/";
		if(CollectionUtils.isNotEmpty(recomdList)){
			for(AuthUser item : recomdList){
				if(StringUtils.isNotEmpty(item.getHeader())){
					//七牛云图片
					//item.setHeader(QiniuStorage.getUrl(item.getHeader()));
					//阿里云图片
					item.setHeader(s + item.getHeader());
				}
			}
		}
		return recomdList;
	}

	public TailPage<AuthUser> queryPage(AuthUser queryEntity , TailPage<AuthUser> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<AuthUser> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	

	public void update(AuthUser entity){
		entityDao.update(entity);
	}

	public void updateSelectivity(AuthUser entity){
		entityDao.updateSelectivity(entity);
	}

	public void delete(AuthUser entity){
		entityDao.delete(entity);
	}

	public void deleteLogic(AuthUser entity){
		entityDao.deleteLogic(entity);
	}



}

