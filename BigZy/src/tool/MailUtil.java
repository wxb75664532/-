package tool;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.Properties;

/**
 * 邮件工具类
 */
public class MailUtil {
    /**
     * 发送邮件
     * @param to 给谁发
     * @param text 发送内容
     */
	
	public static boolean send(String emailaddress,String message ) {
	    // 发送email
	    HtmlEmail email = new HtmlEmail();
	    try {
	      // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
	      email.setHostName("smtp.qq.com");
	      // 字符编码集的设置
	      email.setCharset("UTF-8");
	      // 收件人的邮箱
	      email.addTo(emailaddress);
	      // 发送人的邮箱
	      email.setFrom("756674532@qq.com", "yzc");
	      // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
	      email.setAuthentication("756674532@qq.com", "rlaclfofkxsdbcib");
	      // 要发送的邮件主题
	      email.setSubject("小颜餐厅订单系统");
	      // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
	      email.setMsg("您本次的验证吗是："+message);
	      // 发送
	      email.send();
	      return true;
	    } catch (EmailException e) {
	      e.printStackTrace();
	      return false;
	    }
	  }
	
}