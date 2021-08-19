package quickfixdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.ConfigError;

public class FixServerApplication {
    private static final Logger LOG = LoggerFactory.getLogger(FixServerApplication.class);

    public static void main(String[] args) {
        FixApplication fixApplication = new FixApplication();

        FixAcceptor fixAcceptor = new FixAcceptor(fixApplication);

        try {
            fixAcceptor.init();
            fixAcceptor.start();
        } catch (ConfigError e) {
            LOG.error("FIX Server starting failed: {}", e.getMessage(), e);
            System.exit(-1);
        }
    }
}
