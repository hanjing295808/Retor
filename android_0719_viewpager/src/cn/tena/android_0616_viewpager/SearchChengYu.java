package cn.tena.android_0616_viewpager;

public class SearchChengYu {
	private String id;
	private String name;
	public SearchChengYu(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public SearchChengYu() {
		super();
	}
	@Override
	public String toString() {
		return "SearchChengYu [id=" + id + ", name=" + name + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
