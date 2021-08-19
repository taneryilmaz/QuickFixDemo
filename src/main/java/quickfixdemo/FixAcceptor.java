package quickfixdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;

public class FixAcceptor {
    private static final Logger LOG = LoggerFactory.getLogger(FixAcceptor.class);
    private final FixApplication fixApplication;
    private SocketAcceptor socketAcceptor;

    public FixAcceptor(final FixApplication fixApplication) {
        this.fixApplication = fixApplication;
    }

    public void init() throws ConfigError {
        SessionSettings settings = new SessionSettings(FixAcceptor.class.getClassLoader().getResourceAsStream("config/FixServer.cfg"));
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        socketAcceptor = new SocketAcceptor(fixApplication, storeFactory, settings, logFactory, messageFactory);
    }

    public void start() throws ConfigError {
        LOG.info("Starting acceptor...");
        socketAcceptor.start();
    }

    public void stop() {
        LOG.info("Stopping acceptor...");
        socketAcceptor.stop();
    }
}
