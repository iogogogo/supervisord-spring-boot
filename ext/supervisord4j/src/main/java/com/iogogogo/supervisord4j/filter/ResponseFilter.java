package com.iogogogo.supervisord4j.filter;

import com.google.common.collect.Maps;
import com.iogogogo.supervisord.util.JsonParse;
import com.iogogogo.supervisord4j.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.GitProperties;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by tao.zeng on 2021/7/11.
 */
@Slf4j
@Component
public class ResponseFilter implements Filter {

    private final GitProperties gitProperties;

    public ResponseFilter(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> gitMap = Maps.newHashMap();
        gitProperties.forEach(x -> gitMap.put(x.getKey(), x.getValue()));

        response.setHeader("build-version", Version.VERSION);
        response.setHeader("build-time", Version.TIMESTAMP);
        response.setHeader("build-description", Version.DESCRIPTION);
        response.setHeader("build-artifactid", Version.ARTIFACTID);

        response.setHeader("build-git", new String(JsonParse.toJson(gitMap).getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));

        filterChain.doFilter(servletRequest, response);
    }
}
