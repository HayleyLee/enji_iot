package com.enji_iot.develop.Service.base.impl;

import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.develop.Service.base.MailService;
import com.enji_iot.util.Util.SpringApplicationContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
//import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * 
 * 邮件服务
 *
 */

@Service(value = "MailService")
public class MailServiceImpl implements MailService {
//	@Resource
//	private JavaMailSender javaMailSender;

//	@Resource
//	private VelocityEngine velocityEngine;

	/**
	 * 把模板换算成字符串
	 * @return
	 */
	public String mergeTemplateIntoString(String tpl, Map<String, Object> param) {
//		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tpl, "utf-8", param);
		return null;
	}

	private class MailSend implements Runnable {
		private String mailTo;
		private String mailSubject;
		private String templateLocation;
		private Map<String, Object> param;

		public MailSend(String mailTo, String mailSubject, String templateLocation, Map<String, Object> param) {
			this.mailTo = mailTo;
			this.mailSubject = mailSubject;
			this.templateLocation = templateLocation;
			this.param = param;
		}

		@Override
		public void run() {
			JavaMailSender javaMailSender = (JavaMailSender)SpringApplicationContext.getBean("JavaMailSender");
			MimeMessage mailMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper;
			try {
				param.put("webAddress", ProConfig.LOCAL_DOMAIN);
				messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
				messageHelper.setTo(mailTo);
				messageHelper.setFrom(ProConfig.Mail.USERNAME);
				messageHelper.setSubject(mailSubject);
				String mailText = mergeTemplateIntoString(templateLocation, param);
				messageHelper.setText(mailText, true);
				javaMailSender.send(mailMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * 发送邮件
	 * 
	 * @param mailTo
	 * @param mailSubject
	 * @param templateLocation
	 * @param param
	 */
	public void send(String mailTo, String mailSubject, String templateLocation, Map<String, Object> param) {
		new Thread(new MailSend(mailTo, mailSubject, templateLocation, param)).start();
	}
}
