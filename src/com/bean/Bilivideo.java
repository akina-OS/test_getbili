package com.bean;

/***
 * bilibili的视频
 *
 * @author laoFa
 *
 */
public class Bilivideo {

	/**
	 * 视频封面地址
	 */
	private String video_image;

	/***
	 * 视频标题
	 */
	private String video_tiele;

	public Bilivideo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bilivideo(String video_image, String video_tiele) {
		super();
		this.video_image = video_image;
		this.video_tiele = video_tiele;
	}

	public String getVideo_image() {
		return video_image;
	}

	public void setVideo_image(String video_image) {
		this.video_image = video_image;
	}

	public String getVideo_tiele() {
		return video_tiele;
	}

	public void setVideo_tiele(String video_tiele) {
		this.video_tiele = video_tiele;
	}

	@Override
	public String toString() {
		return "Bilivideo [video_image=" + video_image + ", video_tiele=" + video_tiele + "]";
	}



}
