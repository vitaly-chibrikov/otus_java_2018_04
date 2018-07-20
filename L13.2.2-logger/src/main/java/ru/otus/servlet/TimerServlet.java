package ru.otus.servlet;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;

public class TimerServlet extends HttpServlet {
    private static final String REFRESH_VARIABLE_NAME = "refreshPeriod";
    private static final String TIME_VARIABLE_NAME = "time";
    private static final String CACHE_VARIABLE_NAME = "name";
    private static final String TIMER_PAGE_TEMPLATE = "timer.html";
    private static final int PERIOD_MS = 1000;
    private static Logger logger = Logger.getLogger(TimerServlet.class.getName());
    // HW13
    @Autowired
    private TimeService timeService;

    @Autowired
    private FactoryBean<CacheManager> cacheManagerFactory;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        //logger.setLevel(Level.SEVERE);

        //customConsoleLoggerExample();
        fileLoggerExample();

        logger.info("Servlet initialized");
        if(timeService == null) {
            logger.log(Level.SEVERE, "Timer service is null");
        }

    }

    private void customConsoleLoggerExample() {
        logger.setUseParentHandlers(false);

        Handler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLoggerName() + " "
                        + record.getMillis() + ": "
                        + record.getLevel() + " "
                        + record.getMessage() + "\n";
            }
        });

        logger.addHandler(handler);
        logger.info("Custom console logger changed");
        logger.info("Current thread: " + Thread.currentThread().getName());

    }

    private void fileLoggerExample() {
        try {
            Handler handler = new FileHandler("c:\\tmp\\logger-simple-2.log");
            //handler.setFormatter(new SimpleFormatter());
            handler.setFormatter(new XMLFormatter());
            /*
            handler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return record.getLoggerName() + " "
                            + record.getMillis() + ": "
                            + record.getLevel() + " "
                            + record.getMessage() + "\n";
                }
            });
            //*/
            logger.addHandler(handler);

        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        logger.info("File logger added");
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(REFRESH_VARIABLE_NAME, String.valueOf(PERIOD_MS));
        pageVariables.put(TIME_VARIABLE_NAME, timeService.getTime());

        //HW13: ehcache
        try {
            if (cacheManagerFactory != null) {
                CacheManager cacheManager = cacheManagerFactory.getObject();
                pageVariables.put(CACHE_VARIABLE_NAME, cacheManager.getName());
            } else {
                logger.log(Level.WARNING, "Can't find cacheManagerFactory");
                pageVariables.put(CACHE_VARIABLE_NAME, "Not found");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.getWriter().println(TemplateProcessor.instance().getPage(TIMER_PAGE_TEMPLATE, pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
