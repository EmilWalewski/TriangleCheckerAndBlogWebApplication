package task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.dao.BlogRepository;
import task.pojo.Comment;
import task.pojo.Post;

@Service
public class Server {
	
	@Autowired
	BlogRepository blogRepository;
	
	public List<Post> getPosts(){ return blogRepository.getPosts(); }
	
	public void addPost(int id, String header, String content) { blogRepository.addPost(id, header, content); }
	
	public void editPost(int id, String header, String content) { blogRepository.editPost(id, header, content); }
	
	public void deletePost(int id) { blogRepository.deletePost(id); }
	
	public List<Comment> getComments(){ return blogRepository.getComments(); }
	
	public void addComment(int id, int idPost, String author, String content) { blogRepository.addComment(id, idPost, author, content); }
	
	public void editComment(int id, String content) { blogRepository.editComment(id, content);}
	
	public void deleteComment(int id) { blogRepository.deleteComment(id); }

}
