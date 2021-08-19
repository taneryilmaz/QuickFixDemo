package quickfixdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;

public class FixApplication extends MessageCracker implements Application {
    private static final Logger LOG = LoggerFactory.getLogger(FixApplication.class);

    public FixApplication() {
    }

    @Override
    public void onCreate(SessionID sessionId) {
        LOG.info("[{}] - Session created", sessionId);
    }

    @Override
    public void onLogon(SessionID sessionId) {
        LOG.info("[{}] - Session logged in", sessionId);
    }

    @Override
    public void onLogout(SessionID sessionId) {
        LOG.info("[{}] - Session logged out", sessionId);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        LOG.info("[{}] - Message sent to admin: {}", sessionId, message);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, RejectLogon {
        LOG.info("[{}] - Message received from admin: {}", sessionId, message);
    }

    @Override
    public void toApp(Message message, SessionID sessionId) {
        LOG.info("[{}] - Message sent to app: {}", sessionId, message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectTagValue, UnsupportedMessageType {
        LOG.info("[{}] - Message received from app: {} - class [{}]", sessionId, message, message.getClass().getCanonicalName());

        // TODO onMessage() is not called after crack() operation!

        crack(message, sessionId);
    }

    public void onMessage(quickfix.fix50sp2.NewOrderSingle order, SessionID sessionId) throws FieldNotFound, IncorrectTagValue {
        LOG.info("[{}] - onMessage-fix50sp2", sessionId);
    }
}
