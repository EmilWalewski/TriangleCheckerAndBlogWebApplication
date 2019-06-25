package task.pojo;

import org.springframework.stereotype.Component;

@Component
public class Comment {
	
	int id, idPost;
	String author, content;
	
	public Comment() { }

	public Comment(int id, int idPost, String author, String content) {
		super();
		this.id = id;
		this.idPost = idPost;
		this.author = author;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdpost() {
		return idPost;
	}

	public void setIdpost(int idPost) {
		this.idPost = idPost;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
