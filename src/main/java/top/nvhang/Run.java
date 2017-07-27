package top.nvhang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yeyh on 2017/7/27.
 */
public class Run {

	public static void main (String args[]){
		ApplicationContext context =new ClassPathXmlApplicationContext("app.xml");
		AutoG autoG = (AutoG) context.getBean("autoG");
		autoG.start();

	}


}
