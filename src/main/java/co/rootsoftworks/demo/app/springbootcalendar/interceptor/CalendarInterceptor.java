package co.rootsoftworks.demo.app.springbootcalendar.interceptor;

import java.util.Calendar;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("calendarInterceptor")
public class CalendarInterceptor implements HandlerInterceptor{

    @Value("#{${config.calendar.open}}")
    private int open;

    @Value("#{${config.calendar.close}}")
    private int close;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
                
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //Obteniendo la hora actual
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        //Validamos que el usuario se encuentre dentro de las horas de operación
        if (hour >= open && hour <= close) {
            String message = "Bienvenido al horario del servicio al cliente";
            //Guardamos un mensaje en el request para enviarlo al controlador
            request.setAttribute("message", message);
            return true;
        }else{
            //Construyendo el mensaje de error
            Map<String, String> json = Collections.singletonMap("error", "Fuera del horario de atención");

            //Convirtiendo a JSON
            ObjectMapper mapper = new ObjectMapper();

            //Creando la respuesta
            response.setContentType("application/json");

            //Estado 401 UNAUTHORIZED
            response.setStatus(401);

            //Mandamos el json que contiene el mensaje de error convertido a String gracias al mapper
            response.getWriter().write(mapper.writeValueAsString(json));
            return false;
        }
    }
    

    
}
