package me.camdenorrb.zambiegame.engine.net.base.strategy;

import java.nio.ByteBuffer;


/**
 * The basics for strategies for sending {@link me.camdenorrb.zambiegame.engine.net.base.packet.PacketBase}
 */
public interface StrategyBase {

	/**
	 * Handles the packet
	 *
	 * @param packet The packet to handle
	 */
	void handle(ByteBuffer packet);

}
