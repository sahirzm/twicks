package in.ac.vit.twicks.search.fetchers;

import in.ac.vit.twicks.utils.Constants;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;

@WebListener
public class TwitterContextListener implements ServletContextListener {

    @Inject
    private TwitterStreamer streamer;
    private transient Logger log = Logger.getLogger(getClass());
    @Inject
    private Constants constants;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        constants.path = sce.getServletContext().getRealPath("/");
        log.info("Context initialized...starting twitter streamer");
        this.streamer.startStreaming();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Context destroyed... stopping twitter streamer");
        this.streamer.cleanUp();
    }
}