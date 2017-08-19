package com.david.disshappserver.web.controller.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.david.disshappserver.common.bean.ResponseInfo;
import com.david.disshappserver.common.constants.AdminConstant;
import com.david.disshappserver.common.enums.ResponseCodeNum;
import com.david.disshappserver.utils.LogUtils;
import com.david.disshappserver.utils.VerifyCodeUtils;
import org.apache.log4j.Logger;
import org.jaxen.function.IdFunction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 验证码控制器
 */
@Controller
public class SafeCodeController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	//以这个字符串，随机取出四个做验证码
	public String str = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
	//产生随机颜色
	Color getRandColor(int fc,int bc){ //给定范围获得随机颜色
		Random random = new Random();
		if(fc>255)
			fc = 255;
		if(bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r,g,b);
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/code")
	public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("获取验证码");
		//generateCode(request,response);

		HttpSession session = request.getSession();

		//设置页面不缓冲
	 	response.setHeader("Pragma","No-cache");
	 	response.setHeader("Cache-Control","no-cache");
	 	response.setDateHeader("Expires",0);
	 	//在内存中创建图像
	 	int width = 60;
	 	int height = 20;
	 	BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

	 	//取得图形上下文
	 	Graphics g = image.getGraphics();

	 	//生成随机类
	 	Random random = new Random();

	 	//设定背景色
	 	g.setColor(getRandColor(200,250));
	 	g.fillRect(0,0,width-1,height-1);

	 	//设定字体
	 	g.setFont(new Font("Times New Roman",Font.PLAIN,18));

	 	//随机产生155条干扰线，使图像中的认证码不易被其他程序探测到
	 	g.setColor(getRandColor(160,200));
	 	for(int i=0;i<155;i++){
	 		int x = random.nextInt(width);
	 		int y = random.nextInt(height);
	 		int x1 = random.nextInt(12);
	 		int y1 = random.nextInt(12);
	 		g.drawLine(x,y,x+x1,y+y1);
	 	}
	 	//随机产生4位认证码
	 	String sRand = "";
	 	for(int i=0;i<4;i++){
	 		String rand = String.valueOf(str.charAt(random.nextInt(55))) ;
	 		sRand = sRand + rand;
	 		//将认证码显示到图像中
	 		g.setColor(new Color(20+random.nextInt(55),20+random.nextInt(55),20+random.nextInt(55)));
	 		g.drawString(rand,13*i+6,16);
	 	}
	 	//将认证码存入session中
	 	session.setAttribute(AdminConstant.KEY_SAFE_CODE, sRand);
		LogUtils.e("sessionCode:"+sRand);
	 	//图像生效
	 	g.dispose();
	 	response.reset();
	 	OutputStream out = response.getOutputStream();
	 	//输出图像到页面
	 	ImageIO.write(image, "JPEG", out);
	 	out.close();
	}

	/**
	 * 利用工具类，生成验证码
	 */
	private void generateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 1，生成验证码图片，然后保存到session域中,为了以后和用户输入的进行对比。2,发送给客户端
		 */
		VerifyCodeUtils verficaoCodeUtils = new VerifyCodeUtils();
		BufferedImage BufferedImage = verficaoCodeUtils.getImage();
		// 将验证码的文字保存到session域中
		request.getSession().setAttribute(AdminConstant.KEY_SAFE_CODE, verficaoCodeUtils.getText());
		// 将图片输入到浏览器
		VerifyCodeUtils.output(BufferedImage, response.getOutputStream());
	}

	/**
	 * 返回一个json对象 ，检测验证码是否正确
	 */
	@ResponseBody
	@RequestMapping(value="/checkcode")
	public ResponseInfo checkCode(@RequestParam(value = "code", required = true) String code, HttpSession session) {
		ResponseInfo responseInfo = new ResponseInfo();
		//session域中取出验证码
		String safeCode = (String) session.getAttribute(AdminConstant.KEY_SAFE_CODE);
		LogUtils.e("sessionCodeCheck:"+safeCode);

		if(safeCode != null && safeCode.equalsIgnoreCase(code)) {
			//忽略大小写验证通过
			responseInfo.setCode(ResponseCodeNum.CODE_SUCCESS.getCode());
		} else {
			responseInfo.setCode(ResponseCodeNum.CODE_PARAM_INVALID.getCode());
		}
		return responseInfo;
	}
	
}
