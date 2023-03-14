package mail;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailUtils {
	public final static String MAIL_PROPERTY_FILE = "mail.properties";
	public final static String KEY_MAIL_USER = "mail.user";
	public final static String KEY_MAIL_PASSWORD = "mail.password";
	public final static String KEY_MAIL_TOUSERS = "mail.toUsers";

	public static void main(String[] args) throws ParseException {
		NumberFormat numberFormat = NumberFormat.getPercentInstance();
		numberFormat.setMaximumFractionDigits(2);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		//求前一天的数据
		String date= simpleDateFormat.format(new Date(System.currentTimeMillis()-24*60*60*1000L));
		String beginTime=String.valueOf(simpleDateFormat.parse(date).getTime());
		String endTime=String.valueOf(simpleDateFormat.parse(date).getTime()+24*60*60*1000L);


		//1、订单统计
		DBProxy dbProxy = new DBProxy();
		TotalOrder totalOrder=dbProxy.getTotalOrder(beginTime,endTime);
		List<GroupOrder> groupOrders = dbProxy.getGroupOrder(beginTime,endTime);

		//根据总的订单数据，求出单类产品的占比
		for (GroupOrder order:groupOrders){
			//直接用int类型，0%
			order.setPaySucRate(numberFormat.format(Double.valueOf(order.getPayCount())/Double.valueOf(order.getOrderCount())));
			order.setPayRate(numberFormat.format(Double.valueOf(order.getPayTotal())/Double.valueOf(totalOrder.getTotalIncome())));
			order.setPayCountRate(numberFormat.format(Double.valueOf(order.getPayCount())/Double.valueOf(totalOrder.getPayNum())));
		}

		String message="<h3>1、订单统计</h3>" +
				"<table border=\"1\">\n" +
				" <col width=\"100\">   <tr>\n" +
				"        <th>收入总计</th>\n" +
				"        <th>下单数量</th>\n" +
				"        <th>支付数量</th>\n" +
				"        <th>支付成功率</th>\n" +
				"    </tr>\n" +
				" <col width=\"100\"   <tr>\n" +
				"        <td>"+totalOrder.getTotalIncome()+"</td>\n" +
				"        <td>"+totalOrder.getOrderNum()+"</td>\n" +
				"        <td>"+totalOrder.getPayNum()+"</td>\n" +
				"        <td>"+totalOrder.getPayRate()+"</td>\n" +
				"    </tr>\n" +
				"</table>";

		message=message+"<br>\n" +
				"<table border=\"1\">\n" +
				"    <tr>\n" +
				"        <th>产品名称</th>\n" +
				"        <th>产品ID</th>\n" +
				"        <th>收入合计</th>\n" +
				"        <th>下单数量</th>\n" +
				"        <th>支付数量</th>\n" +
				"        <th>支付成功率</th>\n" +
				"        <th>总收入占比</th>\n" +
				"        <th>总支付数占比</th>\n" +
				"    </tr>\n";
		for (GroupOrder order:groupOrders){
			message=message+"<col width=\"100\"><tr><td>"+order.getProductName()+"</td><td>"+order.getProductID()+"</td><td>"+order.getPayTotal()+"</td><td>"+order.getOrderCount()+"</td>" +
					"<td>"+order.getPayCount()+"</td><td>"+order.getPaySucRate()+"</td><td>"+order.getPayRate()+"</td><td>"+order.getPayCountRate()+"</td>" +
					"</tr>";
		}
		message=message+"</table>";

		List<Order> orders = dbProxy.getOrders(beginTime,endTime);
		message=message+"<br>\n" +
				"<h3>2、订单明细</h3>\n" +
				"<table border=\"1\">\n" +
				"    <tr>\n" +
				"        <th>订单号</th>\n" +
				"        <th>用户名(优品)</th>\n" +
				"        <th>用户名(新浪)</th>\n" +
				"        <th>下单渠道</th>\n" +
				"        <th>下单时间</th>\n" +
				"		 <th>支付时间</th>"+
				"        <th>产品ID</th>\n" +
				"        <th>产品名称</th>\n" +
				"        <th>产品期限</th>\n" +
				"        <th>支付类型</th>\n" +
				"        <th>支付总价</th>\n" +
				"    </tr>\n";

		for(Order order:orders){
			message=message+"<col width=\"150\"><tr><td>"+order.getOrderID()+"</td><td>"+order.getUserName()+"</td><td>"+order.getWbID()+"</td><td>"+order.getCurrch()+"</td>" +
					"<td>"+order.getCtime()+"</td><td>"+order.getOtime()+"</td><td>"+order.getProducteID()+"</td><td>"+order.getProductName()+"</td>" +
					"<td>"+order.getTotalDay()+"</td><td>"+order.getPayType()+"</td><td>"+order.getPayTotal()+"</td></tr>";
		}
		message=message+"</table>";
		MailUtils.sendHtmlMail("新浪渠道订单详情", message);

	}

	public static Properties getMailProperties() {
		InputStream mailPropertiesStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(MAIL_PROPERTY_FILE);
		Properties properties = new Properties();
		try {
			properties.load(mailPropertiesStream);
		} catch (IOException e) {
			System.err.println("load mail propertis error ");
			e.printStackTrace(System.err);
		} finally {
			try {
				if (mailPropertiesStream != null) {
					mailPropertiesStream.close();
				}
			} catch (IOException e) {
				System.err.println("close mail propertis input stream error ");
				e.printStackTrace(System.err);
			}
		}
		return properties;
	}

	public static String getMailUser(Properties mailProperties) {
		return mailProperties.getProperty(KEY_MAIL_USER);
	}

	public static String getMailPassword(Properties mailProperties) {
		return mailProperties.getProperty(KEY_MAIL_PASSWORD);
	}

	public static String getMailToUsers(Properties mailProperties) {
		return mailProperties.getProperty(KEY_MAIL_TOUSERS);
	}

	public static void sendHtmlMail(String subject, String messageContent) {
		Properties mailProperties = getMailProperties();
		String user = getMailUser(mailProperties);
		String password = getMailPassword(mailProperties);
		String mailToUsers = getMailToUsers(mailProperties);
		Session session = Session.getDefaultInstance(mailProperties);
		// 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session);
		try {
			String [] toUsers =  mailToUsers.split(",");
			InternetAddress [] toUserAddress = new InternetAddress[toUsers.length] ;
			for(int i=0;i<toUsers.length;i++){
				toUserAddress[i] = new InternetAddress(toUsers[i]);
			}
			// 加载发件人地址
			message.setFrom(new InternetAddress(user));
			// 加载收件人地址
			message.addRecipients(Message.RecipientType.TO, toUserAddress);
			// 加载标题
			message.setSubject(subject);
			message.setContent(messageContent, "text/html;charset=utf-8");
			Transport transport = session.getTransport();
			// 连接服务器的邮箱
			transport.connect(user, password);
			// 把邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
