package auctionsniper;

import java.util.EventListener;

public interface AuctionEventListener extends EventListener {
	
	enum PriceSource {
		FromSniper, FromOtherBidder;
	};

	void auctionClosed();

	void currentPrice(int i, int j, PriceSource bidder);
	
	void auctionFailed();

}
