package ru.test.issue_tracker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.test.issue_tracker.entity.Comment;

/**
 * @author maxim
 * mapper for Comment entity
 */
public interface CommentMapper {

	@Select("SELECT * FROM issue_comment WHERE id = #{id}")
	Comment findById(@Param("id") int id);

	@Select("SELECT * from issue_comment")
	List<Comment> findAll();

	@Insert("insert into issue_comment(issue_id, author_id, update_time, status_id, comment_text) values(#{issue_id},#{author_id},#{update_time},#{status_id},#{comment_text})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insertComment(Comment comment);
}
