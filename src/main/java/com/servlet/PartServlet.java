package com.servlet;

import com.entity.Part;
import com.repository.PartRepository;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class PartServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(PartServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        log.info("data request");
        List<Part> list = PartRepository.getAll(req.getParameterMap());
        log.info("data transform to json");
        String json = mapper.writeValueAsString(list);
        resp.getWriter().print(json);
    }
}