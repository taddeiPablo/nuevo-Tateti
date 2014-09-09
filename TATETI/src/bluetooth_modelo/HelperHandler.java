package bluetooth_modelo;

import android.os.Handler;
import android.os.Message;


public class HelperHandler extends Handler {

	public enum MessageType{
		STATE,
		READ,
		WRITE,
		DEVICE,
		NOTIFY;
	}

	public MessageType getEnum(int index){
		return MessageType.values()[index];
	}
	
	
	
	public Message obtainMessage(MessageType message, int count, Object obj){
		return obtainMessage(message.ordinal(),count,-1,obj);
		
	}
	
	public MessageType getMessageType(int ordinal){
		return MessageType.values()[ordinal];
	}
	
}
