package co.rootsoftworks.demo.app.springbootcalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class AppConfig implements WebMvcConfigurer{
    
    //Se inyecta el interceptor con su nombre calificador (Porque es del tipo genérico)
    @Autowired
    @Qualifier("calendarInterceptor")
    private HandlerInterceptor calendarInterceptor;

    //Método para registrar el interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(calendarInterceptor);
    }

    



}
