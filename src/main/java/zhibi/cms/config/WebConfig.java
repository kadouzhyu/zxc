package zhibi.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import zhibi.frame.springmvc.interceptor.AdminInterceptor;
import zhibi.frame.springmvc.interceptor.ParamInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter
{

  @Autowired
  private ParamInterceptor paramInterceptor;

  @Autowired
  private AdminInterceptor adminInterceptor;

  public void addInterceptors(InterceptorRegistry registry)
  {
	  
    registry.addInterceptor(this.paramInterceptor).addPathPatterns(new String[] { "/**" });
    this.adminInterceptor.setAdminLoginUrl("/cms/admin/login");
    registry.addInterceptor(this.adminInterceptor).addPathPatterns(new String[] { "/cms/admin/**" })
      .excludePathPatterns(new String[] { "/cms/admin/login", "/cms/admin/logout" });
    super.addInterceptors(registry);
    
  }
  /*@Override  
  public void addResourceHandlers(ResourceHandlerRegistry registry) {  
  
	//文件磁盘图片url 映射  
	//配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径  
	registry.addResourceHandler("/image/2018-05-29/**").addResourceLocations("E:\\picpath\\image\\2018-05-29\\");  
  }  */
  
}