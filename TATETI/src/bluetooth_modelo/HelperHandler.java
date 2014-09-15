package bluetooth_modelo;

import android.os.Handler;
import android.os.Message;

/**
 * ============================================
 * @author pablo
 *=============================================
 */
public class HelperHandler extends Handler {

	/**
	 * 
	 * @author pablo
	 *
	 */
	public enum MessageType{
		STATE,
		READ,
		WRITE,
		DEVICE,
		NOTIFY;
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public MessageType getEnum(int index){
		return MessageType.values()[index];
	}
	
	
	/**
	 * 
	 * @param message
	 * @param count
	 * @param obj
	 * @return
	 */
	public Message obtainMessage(MessageType message, int count, Object obj){
		return obtainMessage(message.ordinal(),count,-1,obj);
		
	}
	
	/**
	 * 
	 * @param ordinal
	 * @return
	 */
	public MessageType getMessageType(int ordinal){
		return MessageType.values()[ordinal];
	}
	
}
