package kr.co.softsoldesk.config;
import javax.servlet.FilterRegistration;
//web.xml
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringConfigClass implements WebApplicationInitializer{
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		// System.out.println("onStartUP"); //이곳부터 시작하는거 확인용

		// web.xml에서 <servlet> 구현부와 같음
		// 프로젝트 구현을 위한 클래스 객체 생성
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		// ServletAppContext 얘를 SpringConfigClass에 등록
		servletAppContext.register(ServletAppContext.class); // ServletAppContext클래스를 가지고 와서 로딩해주는

		// 요청 정보를 분석해서 컨트롤러를 선택하는 서블릿을 지정한다
		DispatcherServlet dispacherServlet = new DispatcherServlet(servletAppContext);

		// 매개변수로 선언된 servletContext객체를 이용하여 servlet에 추가하는것 
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispacher", dispacherServlet); // Dispatcher에 추가할라면 .addServlet

		// 부가설정
		servlet.setLoadOnStartup(1); // 그객체는 제일 먼저 읽혀야되고 //추가후 loading을 어떤 경우라도 제일 먼저 한다
		servlet.addMapping("/"); // 모든 경로에 적용하겠다
		
		//컨트롤러 등록완료
		//================================================================================================================================================
		
		// web.xml에서 <context-param> 구현부와 같음
		// bean을 정의할 xml 파일을 지원한다
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		// RootAppContext 얘를 SpringConfigClass에 등록
		servletAppContext.register(RootAppContext.class);

		// web.xml에서 <listener> 구현부와 같음
		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener); //맨위에 있는것까지
		
		// web.xml에서 <filter> 구현부와 같음
		// 파라미터 인코딩 설정
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class); //필터에 추가할라면 .Dynamic 해야됨
		filter.setInitParameter("encoding", "UTF-8");
	    //dispatcher에 의해서 추가된 Servlet에 UTF-8로 encoding하겠다는 구현부
		filter.addMappingForServletNames(null, false, "dispatcher");
		
		
		
		
		
	}

}
