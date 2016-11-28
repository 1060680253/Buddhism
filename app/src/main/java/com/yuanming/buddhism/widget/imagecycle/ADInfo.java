package com.yuanming.buddhism.widget.imagecycle;

/**
 * 描述：广告信息</br>
 */
public class ADInfo {
	private String url = "";
	private String title;
	private String adv_url;
	public ADInfo() {
	}

	public ADInfo(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAdv_url() {
		return adv_url;
	}

	public void setAdv_url(String adv_url) {
		this.adv_url = adv_url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
