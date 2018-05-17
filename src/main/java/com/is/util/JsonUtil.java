package com.is.util;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.is.entity.TUser;
import com.is.json.entty.UserVO;

public class JsonUtil {

	@Test
	public void test() {
		TUser user = new TUser();
		user.setUid(1);
		user.setAccount("吴丛明");
		UserVO userVO = new UserVO();
		PackageUtil.packageObject(userVO, user);
		String tUser = JSON.toJSONString(userVO);
		System.out.println(tUser);
	}
}
