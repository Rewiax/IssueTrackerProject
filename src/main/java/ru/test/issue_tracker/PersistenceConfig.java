package ru.test.issue_tracker;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * @author maxim
 * database connection configuration
 */
@Configuration
public class PersistenceConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("sql/issue_status.sql")
				.addScript("sql/author.sql")
				.addScript("sql/issue.sql")
				.addScript("sql/issue_comment.sql")
				.build();
	}

//	@Bean
//	public DataSource dataSource() {
//	    DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:h2:tcp://localhost/../database/issuesdb", "root", "root");
//	    ds.setDriverClassName("org.h2.Driver");
//	    return ds;
//	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		return factoryBean.getObject();
	}
}