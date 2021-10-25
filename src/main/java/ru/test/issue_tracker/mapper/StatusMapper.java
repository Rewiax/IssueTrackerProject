package ru.test.issue_tracker.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ru.test.issue_tracker.entity.Status;

/**
 * @author maxim
 * mapper for Status entity
 */
public interface StatusMapper {

	@Select("SELECT * FROM issue_status WHERE id = #{id}")
	Status findById(@Param("id") int id);
	
	@Select("SELECT * FROM issue_status WHERE name = #{name}")
	Optional<Status> findByName(@Param("name") String name);

	@Select("SELECT * from issue_status")
	List<Status> findAll();

}
