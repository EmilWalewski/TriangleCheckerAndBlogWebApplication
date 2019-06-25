package task.pojo;

import org.springframework.stereotype.Component;

@Component
public class Post {
	
	private int id;
	private String postHeader, postContent;
	
	
	public Post() { }
	
	public Post(int id, String postHeader, String postContent) {
		this.id = id;
		this.postHeader = postHeader;
		this.postContent = postContent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPostHeader() {
		return postHeader;
	}

	public void setPostHeader(String postHeader) {
		this.postHeader = postHeader;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	
	
	

}
