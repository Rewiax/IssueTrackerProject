package ru.test.issue_tracker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ru.test.issue_tracker.entity.Issue;

/**
 * @author maxim
 * mapper for Issue entity
 */
public interface IssueMapper {

	@Select("SELECT * FROM issue WHERE id = #{id}")
	Issue findById(@Param("id") int id);

	@Select("SELECT * from issue")
	List<Issue> findAll();

	@Insert("insert into issue(name, description, start_date, author_id, status_id) values(#{name},#{description},#{start_date},#{author_id},#{status_id})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insertIssue(Issue issue);
}
