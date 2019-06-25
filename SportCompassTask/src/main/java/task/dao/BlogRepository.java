package task.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import task.pojo.Comment;
import task.pojo.Post;

import org.springframework.jdbc.core.RowMapper;

@Repository
public class BlogRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Post> getPosts(){
		
		List<Post> list = jdbcTemplate.query("select * from posts", new PostMapper());
		
		return list;
	}
	
	public void addPost(int id, String header, String content) {
		
		jdbcTemplate.update("insert into posts values(?,?,?)", id, header, content);
	}
	
	
	public void editPost(int id, String header, String content) {
		
		jdbcTemplate.update("update posts set postheader=?, postcontent=? where id=?", header, content, id);
	}
	
	public void deletePost(int id) {
		
		jdbcTemplate.update("delete from posts where id=?", id);
		
		chanageTablesIDafterDelete("posts");
		chanageTablesIDafterDelete("comments");
			
	}
	
	public List<Comment> getComments(){
		
		List<Comment> list = jdbcTemplate.query("select * from comments order by id asc", new CommentMapper());
		
		return list;
	}
	
	public void addComment(int id, int idPost, String author, String content) {
		
		jdbcTemplate.update("insert into comments values(?,?,?,?)",id , idPost, author, content);
	}
	
	public void editComment(int id, String content) {
		
		jdbcTemplate.update("update comments set content=? where id=?", content, id);
	}
	
	public void deleteComment(int id) {
		
		jdbcTemplate.update("delete from comments where id=?", id); 
		
		chanageTablesIDafterDelete("comments");
		
	}
	
	private void chanageTablesIDafterDelete(String tableName) {

		List<Integer> intList = jdbcTemplate.query("select id from "+tableName+"", new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}});
		
		
		for (int i = 0; i < intList.size(); i++) 
			if(intList.get(i) != i+1) jdbcTemplate.update("update "+tableName+" set id=? where id=?", i+1, intList.get(i));
	}
	
	
	private static class PostMapper implements RowMapper<Post>{

		@Override
		public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
			Post post = new Post(
					rs.getInt("ID"),
					rs.getString("POSTHEADER"),
					rs.getString("POSTCONTENT"));
			return post;
		}
	}
	
	
	private static class CommentMapper implements RowMapper<Comment>{

		@Override
		public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Comment comment = new Comment(
					rs.getInt("ID"),
					rs.getInt("IDPOST"),
					rs.getString("AUTHOR"),
					rs.getString("CONTENT"));
			return comment;
		}
		
		
	}
}
