package tool;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.Properties;

/**
 * �ʼ�������
 */
public class MailUtil {
    /**
     * �����ʼ�
     * @param to ��˭��
     * @param text ��������
     */
	
	public static boolean send(String emailaddress,String message ) {
	    // ����email
	    HtmlEmail email = new HtmlEmail();
	    try {
	      // ������SMTP���ͷ����������֣�163�����£�"smtp.163.com"
	      email.setHostName("smtp.qq.com");
	      // �ַ����뼯������
	      email.setCharset("UTF-8");
	      // �ռ��˵�����
	      email.addTo(emailaddress);
	      // �����˵�����
	      email.setFrom("756674532@qq.com", "yzc");
	      // �����Ҫ��֤��Ϣ�Ļ���������֤���û���-���롣�ֱ�Ϊ���������ʼ��������ϵ�ע�����ƺ�����
	      email.setAuthentication("756674532@qq.com", "rlaclfofkxsdbcib");
	      // Ҫ���͵��ʼ�����
	      email.setSubject("С�ղ�������ϵͳ");
	      // Ҫ���͵���Ϣ������ʹ����HtmlEmail���������ʼ�������ʹ��HTML��ǩ
	      email.setMsg("�����ε���֤���ǣ�"+message);
	      // ����
	      email.send();
	      return true;
	    } catch (EmailException e) {
	      e.printStackTrace();
	      return false;
	    }
	  }
	
}