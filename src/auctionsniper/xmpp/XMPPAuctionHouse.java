package auctionsniper.xmpp;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FilenameUtils;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import xmpp.auctionsniper.LoggingXMPPFailureReporter;

import auctionsniper.Auction;
import auctionsniper.AuctionHouse;
import auctionsniper.Item;



public class XMPPAuctionHouse implements AuctionHouse {

	private XMPPConnection connection;
	private XMPPFailureReporter failureReporter;
	public static final String ITEM_ID_AS_LOGIN = "auction-%s";
	public static final String AUCTION_RESOURCE = "Auction";
	public static final String AUCTION_ID_FORMAT = ITEM_ID_AS_LOGIN + "@%s/" + AUCTION_RESOURCE;
	
	public static final String LOG_FILE_NAME = "loggingInfo.log";

	private static final String LOGGER_NAME = "fileLogger";

	@Override
	public Auction auctionFor(Item item) {
		return new XMPPAuction(connection, item, failureReporter);
	}
	
	public XMPPAuctionHouse(XMPPConnection connection) throws SecurityException, XMPPAuctionException {
		this.connection = connection;
		this.failureReporter = new LoggingXMPPFailureReporter(makeLogger());
	}

	public static XMPPAuctionHouse connect(String hostname, String username,
			String password) throws XMPPException, SecurityException, XMPPAuctionException {
	    XMPPConnection connection = new XMPPConnection(hostname); 
	    try {
	    	connection.connect(); 
	    	connection.login(username, password, AUCTION_RESOURCE); 
	    	return new XMPPAuctionHouse(connection);
	    } catch (XMPPException xmppe) {
	        throw new XMPPAuctionException("Could not connect to auction: " + connection, xmppe);
	    }
	}

	public void disconnect() {
		connection.disconnect();
		
	}
	
	private Logger makeLogger() throws SecurityException, XMPPAuctionException {
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.setUseParentHandlers(false);
		logger.addHandler(simpleFileHandler());
		return logger;
	}

	private Handler simpleFileHandler() throws XMPPAuctionException {
		try {
			FileHandler handler = new FileHandler(LOG_FILE_NAME);
			handler.setFormatter(new SimpleFormatter());
			return handler;
		} catch (Exception e) {
				throw new XMPPAuctionException("Could not create logger FileHandler" + FilenameUtils.getFullPath(LOG_FILE_NAME), e);
		}
	}

 
}
