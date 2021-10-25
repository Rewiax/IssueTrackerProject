package ru.test.issue_tracker.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.test.issue_tracker.entity.Author;

/**
 * @author maxim
 * mapper for Author entity
 */
public interface AuthorMapper {

	@Select("SELECT * FROM author WHERE id = #{id}")
	Author findById(@Param("id") int id);
	
	@Select("SELECT * FROM author WHERE name = #{name}")
	Optional<Author> findByName(@Param("name") String name);

	@Select("SELECT * from author")
	List<Author> findAll();

	@Insert("insert into author(name) values(#{name})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insertAuthor(Author author);
}
