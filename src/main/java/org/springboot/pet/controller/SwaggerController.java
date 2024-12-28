package org.springboot.pet.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("swaggerController")
@RequestMapping("")
@Hidden
@RequiredArgsConstructor
@Slf4j
public class SwaggerController {
    private static final String SWAGGER_UI_INDEX_HTML = "/swagger-ui/index.html";

    @Value("${server.ssl.enabled:false}")
    private boolean isSslEnabled;

    @Value("${server.addres:localhost}")
    private String serverAddress;

    @Value("${server.port:8080}")
    private int serverPort;

    @Value("$server.servlet.context-path:/")
    private String contextPath;

    @PostConstruct
    public void onInit() {
        log.info(
                "Ссылка на свагер: {}://{}:{}{}{}",
                isSslEnabled ? "https" : "http",
                serverAddress, serverPort, contextPath, SWAGGER_UI_INDEX_HTML
        );
    }

    @GetMapping
    public void swaggerUI(HttpServletResponse response) throws IOException {
        response.sendRedirect(contextPath + SWAGGER_UI_INDEX_HTML);
    }
}
