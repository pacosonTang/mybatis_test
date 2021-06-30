package com.swjtu.mybatis.dao;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

// mybatis 插件类
/**
 * @Intercepts 注解完成插件签名：
 *      告诉mybatis，当前插件用来拦截哪个target（type）的哪个方法（method）
 *      ，其中方法参数为 args
 */
@Intercepts({
	@Signature(type=StatementHandler.class, method="parameterize", args=Statement.class)
})
public class MyFirstPlugin implements Interceptor{
	/* 目标对象指4个对象：
	 * 即 Executor、ParameterHandler、ResultSetHandler、StatementHandler、 */
	/**
	 * intercept方法： 拦截目标对象的目标方法的执行；
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("MyFirstPlugin.intercept(): " + invocation.getMethod());
		// 动态改变sql 运行参数，如下：以前查1号员工，实际查询3号员工。
		Object target = invocation.getTarget();
		System.out.println("当前拦截到的对象=" + target);
		// 获取 StatementHandler.parameterHandler.parameterObject的值；
		// 获取到 target 目标对象的袁术；
		MetaObject mo = SystemMetaObject.forObject(target);
		Object value = mo.getValue("parameterHandler.parameterObject");
		System.out.println("sql语句用的参数是 = " + value);
		// 修改 sql语句的参数：使得查询id=3的用户而不是id=1的用户。
		mo.setValue("parameterHandler.parameterObject", 3);
		
		Object proceed = invocation.proceed(); // 执行目标方法
		return proceed; // 返回执行后的返回值
	}
    /**
     *  plugin方法： 包装目标对象的；
     */
	@Override
	public Object plugin(Object target) {
		System.out.println("MyFirstPlugin.plugin():mybaits将要包装的对象 =  " + target);
		// 借助 Plugin.wrap() 方法使用当前 Interceptor 包装目标对象
		Object wrap = Plugin.wrap(target, this);
		// 返回为当前target 创建的动态代理
		return wrap;
	}
	/**
	 * setProperties方法： 将插件注册时的property属性设置进来；
	 */
	@Override
	public void setProperties(Properties properties) {
		System.out.println("插件配置时的信息： " + properties);
	}
}





