package com.ccg.io.waterprint;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 
 * @Description
 * @author LIQING
 * @date 2016-9-20 上午10:56:09
 */

public class WaterPrint {
	// 水印透明度
	private static float alpha = 0.7f;
	// 水印横向位置
	private static int positionWidth = 200;
	// 水印纵向位置
	private static int positionHeight = 148;
	// 水印文字字体
	private static Font font = new Font("宋体", Font.BOLD, 12);
	// 水印文字颜色
	private static Color color = Color.gray;


	/**
	 * 给图片添加水印文字、可设置水印文字的旋转角度
	 * 
	 * @param logoText
	 * @param srcImgPath
	 * @param targerPath
	 * @param degree
	 */
	public static BufferedImage markImageByText(String logoText, File srcFile,Integer degree) {
		InputStream is = null;
		OutputStream os = null;
		BufferedImage image = null;
		try {
			// 1、源图片
			Image srcImg = ImageIO.read(srcFile);
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			// 2、得到画笔对象
			Graphics2D g = buffImg.createGraphics();
			// 3、设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(
					srcImg.getScaledInstance(srcImg.getWidth(null),
							srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
					null);
			// 4、设置水印旋转
			if (null != degree) {
				g.rotate(Math.toRadians(-45),(double) buffImg.getWidth() / 2.1,(double) buffImg.getHeight() /0.8);
			}
			// 5、设置水印文字颜色
			g.setColor(color);
			// 6、设置水印文字Font
			g.setFont(font);
			// 7、设置水印文字透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
			g.drawString(logoText, positionWidth, positionHeight);
			// g.drawString(logoText, positionWidth-50, positionHeight-260);
			// 9、释放资源
			g.dispose();
			// 10、生成图片 转透明
			byte[] buf = transferAlpha(buffImg);
			ByteArrayInputStream in = new ByteArrayInputStream(buf); // 将b作为输入流；
			image = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return image;

	}

	/**
	 * 背景色为白色的图片转为透明色
	 * 
	 * @param image
	 * @return
	 */
	public static byte[] transferAlpha(Image image) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			ImageIcon imageIcon = new ImageIcon(image);
			BufferedImage bufferedImage = new BufferedImage(
					imageIcon.getIconWidth(), imageIcon.getIconHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
			g2D.drawImage(imageIcon.getImage(), 0, 0,
					imageIcon.getImageObserver());
			int alpha = 0;
			for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage
					.getHeight(); j1++) {
				for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage
						.getWidth(); j2++) {
					int rgb = bufferedImage.getRGB(j2, j1);

					int R = (rgb & 0xff0000) >> 16;
					int G = (rgb & 0xff00) >> 8;
					int B = (rgb & 0xff);
					if (((255 - R) < 30) && ((255 - G) < 30)
							&& ((255 - B) < 30)) {
						rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
					}

					bufferedImage.setRGB(j2, j1, rgb);

				}
			}

			g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
			ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return byteArrayOutputStream.toByteArray();

	}

	/**
	 * excel 文件
	 * 
	 * @param excelFile
	 *            需要水印的excel文件
	 * @param logoText
	 *            水印文字
	 * @param imgFile
	 *            水印原图片
	 * @param degree
	 *            水印文字的旋转角度
	 * @return
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 */
	public static XSSFWorkbook excel2007(File excelFile, String logoText,File imgFile, Integer degree) {
		if (imgFile == null) {
			imgFile = getImgFile();
		}
		if (degree == null) {
			degree = 45;
		}
		InputStream input = null;
		XSSFWorkbook wb = null;
		XSSFSheet sheet = null;
		try {
			input = new FileInputStream(excelFile);
			wb = (XSSFWorkbook) WorkbookFactory.create(input);
			int sheetNumbers = wb.getNumberOfSheets();
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			BufferedImage bufferImg = markImageByText(logoText, imgFile, degree);
			ImageIO.write(bufferImg, "png", byteArrayOut);
			for (int i = 0; i < sheetNumbers; i++) {
				sheet = wb.getSheetAt(i);
				XSSFDrawing dp = sheet.createDrawingPatriarch();
				XSSFClientAnchor anchor = new XSSFClientAnchor(1, 1, 510, 255,(short)5, 1, (short) 6, 25);
				anchor.setAnchorType(AnchorType.DONT_MOVE_AND_RESIZE);
				dp.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(),XSSFWorkbook.PICTURE_TYPE_PNG));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}
	/**
	 * excel 文件
	 * 
	 * @param srcBook
	 *            需要水印的XSSFWorkbook
	 * @param logoText
	 *            水印文字
	 * @param imgFile
	 *            水印原图片
	 * @param degree
	 *            水印文字的旋转角度
	 * @return
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 */
	public static XSSFWorkbook getWorkBook(XSSFWorkbook srcBook, String logoText,File imgFile, Integer degree) {
		if (imgFile == null) {
			imgFile = getImgFile();
		}
		if (degree == null) {
			degree = -45;
		}
		XSSFSheet sheet = null;
		try {
			int sheetNumbers = srcBook.getNumberOfSheets();
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			BufferedImage bufferImg = markImageByText(logoText, imgFile, degree);
			ImageIO.write(bufferImg, "png", byteArrayOut);
			for (int i = 0; i < sheetNumbers; i++) {
				sheet = srcBook.getSheetAt(i);
				XSSFDrawing dp = sheet.createDrawingPatriarch();
				XSSFClientAnchor anchor = new XSSFClientAnchor(1, 1, 510, 255,(short) 1, 1, (short) 15, 18);
				anchor.setAnchorType(AnchorType.DONT_MOVE_AND_RESIZE);
				dp.createPicture(anchor, srcBook.addPicture(byteArrayOut.toByteArray(),XSSFWorkbook.PICTURE_TYPE_PNG));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return srcBook;
	}
	/**
	 * 获取水印原图片文件
	 * @return
	 */
	public static File getImgFile(){
		String rootPath = "pages"+File.separator+"ahcw"+File.separator+"img"+File.separator+"waterPrint.png";
		return new File(rootPath);
	}

	public static void getWaterPic(String logoText, File imgFile,Integer degree,String targerPath){
		if (imgFile == null) {
			imgFile = getImgFile();
		}
		if (degree == null) {
			degree = -45;
		}
		BufferedImage bufferImg = markImageByText(logoText,imgFile,degree);
		try {
			ImageIO.write(bufferImg, "png", new File(targerPath));
			System.out.println("写出水印文件结束。。。");
		} catch (IOException e) {
			e.printStackTrace();
		} //将文件写到指定文件夹
	}
	public static void main(String[] args) {
		File imgFile = new File("F:/apache-tomcat-6.0.45/webapps/ahcw/pages/ahcw/img/waterPrint.png");
		getWaterPic("admin A34 系统管理员 2016-09-22 16:22:51 127.255.255.255", imgFile, null,"E://test.png");
	}

}
