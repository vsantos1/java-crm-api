package com.vsantos1.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vsantos1.event.CreatedResourceEvent;

@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent> {

    @Override
    public void onApplicationEvent(CreatedResourceEvent event) {

        HttpServletResponse response = event.getResponse();
        Long code = event.getCode();

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{code}")
        .buildAndExpand(code).toUri();
        response.setHeader("Location", uri.toASCIIString());

    }


    
}
