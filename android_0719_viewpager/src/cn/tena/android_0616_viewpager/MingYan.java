package cn.tena.android_0616_viewpager;

public class MingYan {
	private String id;
	private String content;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MingYan(String id, String content, String name) {
		super();
		this.id = id;
		this.content = content;
		this.name = name;
	}
	public MingYan() {
		super();
	}
	@Override
	public String toString() {
		return "MingYan [id=" + id + ", content=" + content + ", name=" + name
				+ "]";
	}
	
}
