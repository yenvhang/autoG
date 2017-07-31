import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.nvhang.AutoG;

/**
 * Created by yeyh on 2017/7/27.
 */
public class Run {

	public static void main (String args[]){
		System.out.println(System.getProperty("user.dir"));

		ApplicationContext context =new ClassPathXmlApplicationContext("application-config.xml");
		AutoG autoG = (AutoG) context.getBean("autoG");
		autoG.start();


	}


}
