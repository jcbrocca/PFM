package uoc.edu.svrKpax.business;

import java.util.List;

import uoc.edu.svrKpax.vo.Comment;

public interface CommentBO {

	public List<Comment> listCommentsGame(String campusSession, int idGame);

	public Boolean addComment(String campusSession, int idComment, int idGame);

	public Boolean delComment(String campusSession, int idComment);

}
