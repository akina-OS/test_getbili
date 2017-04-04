package getBilibiliImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.bean.Bilivideo;

public class tasrt {

	/****
	 * 传入B站视频AV号，保存封面图
	 *
	 * @param str_AV
	 * @return
	 */
	public Bilivideo getBiliBiliImage(String str_AV) {
		String str_http_link = "http://www.bilibili.com/video/av" + str_AV + "/";
		String image_path = null;
		Bilivideo mp4 = new Bilivideo();
		try {
			Parser parser = new Parser((HttpURLConnection) (new URL(str_http_link)).openConnection());
			AndFilter filter = new AndFilter(new TagNameFilter("img"), new HasAttributeFilter("class", "cover_image"));
			NodeList nodes = parser.parse(filter);
			ImageTag img = (ImageTag) nodes.elementAt(0);
			image_path = img.getAttribute("src");
			// 设置封面图网络地址
			mp4.setVideo_image(image_path);
			// mp4.setVideo_tiele(htmlpage.getTitle());
		} catch (Exception e) {
			// System.out.println("Exception:" + e);
			e.getMessage();
		}
		return mp4;
	}

	/**
	 * 根据url 下载图片流
	 *
	 * @param image
	 * @return
	 */
	public InputStream download(String image) {
		InputStream is = null;
		try {
			// 构造URL
			System.out.println("构造URL正常...");
			URL url = new URL(image);
			// 打开连接
			System.out.println("打开连接正常...");
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			is = con.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("返回输入流正常...");
		return is;
	}

	/****
	 * @param streamass
	 *            文件
	 * @param path
	 *            保存路径
	 * @param filename
	 *            文件名称
	 * @throws IOException
	 */
	 public void SaveFileFromInputStream(InputStream stream, String filename, String path) throws IOException {
 		FileOutputStream fs = new FileOutputStream(path + File.separatorChar + filename);
 		byte[] buffer = new byte[1024 * 1024];
 		int byteread = 0;
 		while ((byteread = stream.read(buffer)) != -1) {
 			fs.write(buffer, 0, byteread);
 			fs.flush();
 		}
 		fs.close();
 		stream.close();
 	}

	/****
	 *
	 * @param str_AV
	 *            视频番号
	 * @param path
	 *            保存路径
	 */
	public void getBiliBiliImage_main(String str_AV, String path) {
		Format format = new SimpleDateFormat("yyyyMMddHHmmss");
		// 文件名
		String file_name = format.format(new Date()) + ".jpg";
		try {

			Bilivideo mp4 = this.getBiliBiliImage(str_AV);
			// this.SaveFileFromInputStream(this.download(mp4.getVideo_image()),
			// mp4.getVideo_tiele() + ".jpg", path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		 new tasrt().getBiliBiliImage_main("7000550", "D:\\code\\testDown");
	}

}
