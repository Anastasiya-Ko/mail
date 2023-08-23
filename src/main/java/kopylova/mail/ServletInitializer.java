package kopylova.mail;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;


public class ServletInitializer extends SpringBootServletInitializer implements WebApplicationInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MailApplication.class);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return customizerBuilder(builder);
//	}
//
//	public static SpringApplicationBuilder customizerBuilder(SpringApplicationBuilder builder) {
//		return builder.sources(ServletInitializer.class).bannerMode(Banner.Mode.OFF);
//	}

}
