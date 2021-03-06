package auctionsniper;

import auctionsniper.util.Defect;

public enum SniperState {
	JOINING {
		@Override
		public SniperState whenAuctionClosed() {
			return LOST;
		}
	},
	BIDDING {
		@Override
		public SniperState whenAuctionClosed() {
			return LOST;
		}
	},
	WINNING {
		@Override
		public SniperState whenAuctionClosed() {
			return WON;
		}
	},
	LOSING {
		@Override
		public SniperState whenAuctionClosed() {
			return LOST;
		}
	},
	WON,
	LOST, 
	FAILED;

	public SniperState whenAuctionClosed() {
		throw new Defect("Auction is already closed");
	}
}
