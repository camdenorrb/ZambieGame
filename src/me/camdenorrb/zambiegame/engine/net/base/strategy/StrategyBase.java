package me.camdenorrb.zambiegame.engine.net.base.strategy;

import java.nio.ByteBuffer;


/**
 * The base for strategies for sending {@link me.camdenorrb.zambiegame.engine.net.base.packet.PacketBase}
 */
public interface StrategyBase {

	/**
	 *
	 * @param packet
	 */
	void handle(ByteBuffer packet);

}
