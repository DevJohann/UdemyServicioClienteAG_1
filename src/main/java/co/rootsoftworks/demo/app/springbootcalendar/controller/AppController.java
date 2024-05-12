package co.rootsoftworks.demo.app.springbootcalendar.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app")
public class AppController {
    
    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request){
        Map<String, String> data = new HashMap<>();
        data.put("title", "Servicio al cliente");
        data.put("time", new Date().toString());
        //Obtenemos el atributo que guardamos en el interceptor
        data.put("message", (String) request.getAttribute("message"));
        
        //Retornamos un status 200 OK con el JSON de respuesta
        return ResponseEntity.ok(data);
    }
}
