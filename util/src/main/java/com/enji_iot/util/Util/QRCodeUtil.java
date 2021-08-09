package com.enji_iot.util.Util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码工具类 Created by fuli.shen on 2017/3/31.
 */
public class QRCodeUtil {

	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "JPG";
	// 二维码尺寸
	private static final int QRCODE_SIZE = 300;
	// LOGO宽度
	private static final int WIDTH = 60;
	// LOGO高度
	private static final int HEIGHT = 60;

	/**
	 * 生成二维码的方法
	 *
	 * @param content
	 *            目标URL
	 * @param imgPath
	 *            LOGO图片地址
	 * @param needCompress
	 *            是否压缩LOGO
	 * @return 二维码图片
	 * @throws Exception
	 */
	private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
				hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		if (imgPath == null || "".equals(imgPath)) {
			return image;
		}
		// 插入图片
		QRCodeUtil.insertImage(image, imgPath, needCompress);
		return image;
	}

	/**
	 * 插入LOGO
	 *
	 * @param source
	 *            二维码图片
	 * @param imgPath
	 *            LOGO图片地址
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 */
	private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
		File file = new File(imgPath);
		if (!file.exists()) {
			System.err.println("" + imgPath + "   该文件不存在！");
			return;
		}
		Image src = ImageIO.read(new File(imgPath));
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 *
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param destPath
	 *            存放目录
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, String imgPath, String destPath, boolean needCompress) throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
		mkdirs(destPath);
		String file = new Random().nextInt(99999999) + ".jpg";
		ImageIO.write(image, FORMAT_NAME, new File(destPath + "/" + file));
	}
	
	public static void encode(String content, String imgPath, String destPath, boolean needCompress,String fileName) throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
		mkdirs(destPath);
		String file =fileName + ".jpg";
		ImageIO.write(image, FORMAT_NAME, new File(destPath + "/" + file));
	}

	/**
	 * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
	 *
	 * @param destPath
	 *            存放目录
	 */
	public static void mkdirs(String destPath) {
		File file = new File(destPath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 *
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param destPath
	 *            存储地址
	 * @throws Exception
	 */
	public static void encode(String content, String imgPath, String destPath) throws Exception {
		QRCodeUtil.encode(content, imgPath, destPath, false);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            内容
	 * @param destPath
	 *            存储地址
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, String destPath, boolean needCompress) throws Exception {
		QRCodeUtil.encode(content, null, destPath, needCompress);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            内容
	 * @param destPath
	 *            存储地址
	 * @throws Exception
	 */
	public static void encode(String content, String destPath) throws Exception {
		QRCodeUtil.encode(content, null, destPath, false);
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 *
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param output
	 *            输出流
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, String imgPath, OutputStream output, boolean needCompress)
			throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
		ImageIO.write(image, FORMAT_NAME, output);
	}

	/**
	 * 生成二维码
	 *
	 * @param content
	 *            内容
	 * @param output
	 *            输出流
	 * @throws Exception
	 */
	public static void encode(String content, OutputStream output) throws Exception {
		QRCodeUtil.encode(content, null, output, false);
	}

	/**
	 * 解析二维码
	 *
	 * @param file
	 *            二维码图片
	 * @return
	 * @throws Exception
	 */
	public static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable hints = new Hashtable();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		return resultStr;
	}

	/**
	 * 解析二维码
	 *
	 * @param path
	 *            二维码图片地址
	 * @return 不是二维码的内容返回null,是二维码直接返回识别的结果
	 * @throws Exception
	 */
	public static String decode(String path) throws Exception {
		return QRCodeUtil.decode(new File(path));
	}

	 /**给图片增加文字
     * @param str
     *            生产的图片文字
     * @param oldPath
     *            原图片保存路径
     * @param newPath
     *            新图片保存路径
     * @param width
     *            定义生成图片宽度
     * @param height
     *            定义生成图片高度
     * @return
     * @throws IOException
     */
    public static void addImgText(String str, String oldPath, String newPath, int width, int height){
        try {
            File oldFile = new File(oldPath);
            Image image = ImageIO.read(oldFile);

            //获取图像的高度，宽度
            height=image.getHeight(null);
            width =image.getWidth(null);
            int text_height = 1 * 20;
            height = height+text_height;
            
            File file = new File(newPath);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //构建图片流
            BufferedImage tag = new BufferedImage(width / 2, height / 2, BufferedImage.TYPE_INT_RGB);
            //绘制改变尺寸后的图
            tag.getGraphics().drawImage(bi, 0, 0,width / 2, height / 2, null);  
            Graphics2D g2 = bi.createGraphics();
            g2.setBackground(Color.WHITE);
            g2.clearRect(0, 0, width, height);
            g2.drawImage(image, 0, 0, width, height-text_height, null); //这里减去25是为了防止字和图重合
            /** 设置生成图片的文字样式 * */
            Font font = new Font("黑体", Font.BOLD, 20);
            g2.setFont(font);
            g2.setPaint(Color.BLACK);

            /** 设置字体在图片中的位置 在这里是居中* */
            FontRenderContext context = g2.getFontRenderContext();
            

            /** 防止生成的文字带有锯齿 * */
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            Rectangle2D bounds = font.getStringBounds(str, context);
            double ascent = -bounds.getY();
            double x = 25.0;
            double y = height - text_height- bounds.getHeight()+5;
            double baseY = 0.0;
            /** 在图片上生成文字 * */
            while(str.length()>13){
            	int length = 13;
            	String temp = str.substring(0,length);
            	
            	bounds = font.getStringBounds(temp, context);
            	while(bounds.getWidth()<width-10 && length<str.length()){ 
            		length++;
            		temp = str.substring(0,length);
            		bounds = font.getStringBounds(temp, context);
            	}
            	if(length+1<str.length()){
            		length--;
            	}
            	temp = str.substring(0,length);
                x = (width - bounds.getWidth()) / 2;
                
                baseY = y + ascent;
            	g2.drawString(temp, (int) x, (int) baseY);
            	str = str.substring(length);
            	y = y+24;
            }
            baseY = y + ascent;
            g2.drawString(str, (int) x, (int) baseY);

            ImageIO.write(bi, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
	
	public static void main(String[] args) {

		// 生成二维码
		// String text = "https://www.baidu.com/";
		// String imagePath = System.getProperty("user.dir") + "/data/1.jpg";
		String destPath =  "D:/data/output/";
		 try {
//			QRCodeUtil.encode("http://www.ixiashan.cn/webadmin/activity/group!get?id=594&pagenum=1",null, destPath, false);
			encode("http://www.ixiashan.cn/webadmin/activity/group!get?id=594&pagenum=1", null, "D:/data/output/22", false,"333") ;
			addImgText( "设备SN:s1001" ,"D:/data/output/22/333.jpg","D:/data/output/22/333.jpg",455,455  );
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 验证图片是否含有二维码
//		String destPath1 =  "D:/data/3.jpg";
//		try {
//			String result = decode(destPath1);
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(destPath1 + "不是二维码");
//		}
	}
}
