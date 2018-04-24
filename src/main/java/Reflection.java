import java.lang.reflect.Constructor;

import com.is.entity.TUser;

public class Reflection {
	public static void main(String[] args) {
		TUser user = new TUser();
		
		try{
			String className = "com.is.entity.TUser";
			Class clazz = Class.forName(className);
			Constructor c = clazz.getConstructor();
			TUser tUser = (TUser) c.newInstance();
			tUser.setUsername("ymm");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
