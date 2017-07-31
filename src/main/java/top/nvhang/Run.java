package top.nvhang;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yeyh on 2017/7/27.
 */
public class Run {

	public static void main (String args[]){
		System.out.println(System.getProperty("user.dir"));
		System.out.println("!!");
		ApplicationContext context =new ClassPathXmlApplicationContext("app.xml");
		AutoG autoG = (AutoG) context.getBean("autoG");
		autoG.start();


	}


}
