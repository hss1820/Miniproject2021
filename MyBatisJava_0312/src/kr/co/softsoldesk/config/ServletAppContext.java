package kr.co.softsoldesk.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.softsoldesk.database.MapperInterface;

//servlet-context에서 <annotation-driven/>와 같음
@Configuration
//ControllerAnnotation이 설정되어있는 클래스를 등록하는 Annotation
@EnableWebMvc //=<annotation-driven/>
//scan할 패키지 등록
@ComponentScan("kr.co.softsoldesk.controller")
@PropertySource("/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer{
	
	// propreties에 있는 거 등록 시키기 -> 드라이버만 넣어둔 것
	@Value("${db.classname}")
	private String db_classname;
	
	@Value("${db.url}")
	private String db_url;
	
	@Value("${db.username}")
	private String db_username;
	
	@Value("${db.password}")
	private String db_password;
	
	
	//controller메소드(home())에서 반환하는 문자열 앞(경로),뒤(확장자)에 붙을 경로
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	//정적데이터(이미지,사운드,동영상,js,css) 경로 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/"); //모든경로에 다 /** 최상위 아래 resources/ -> resources아래 하나까지
	}
	
	// 데이터베이스 접속 정보 관리
	@Bean //꼭 넣어야 들어감
	public BasicDataSource dataSource() {
		
		BasicDataSource source = new BasicDataSource();
		
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		
		return source;
	}
	
	// 쿼리문과 접속을 관리하는 객체
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception{ //getCon이라고 생각하면 됨
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		
		//source에 담겨있는 것들을 factoryBean에 넘겨주는
		factoryBean.setDataSource(source); // 이래야 맵필 기능 발동
		SqlSessionFactory factory=factoryBean.getObject(); //어떤 것들이든지 흡수, 관리할 수 있게끔.getObject() 해서 factory에 넘겨주는
		
		return factory;
	}
	
	// 쿼리문 실행을 위한 객체
	@Bean
	public MapperFactoryBean<MapperInterface> test_mapper(SqlSessionFactory factory) throws Exception { // 내가 작성한 sql문은 여기있어 MapperInterface
		
		MapperFactoryBean<MapperInterface> factoryBean = new MapperFactoryBean<MapperInterface>(MapperInterface.class); // MapperInterface 클래스로 넣어줘야 돔
		factoryBean.setSqlSessionFactory(factory);
		
		return factoryBean;
	}
	
}


