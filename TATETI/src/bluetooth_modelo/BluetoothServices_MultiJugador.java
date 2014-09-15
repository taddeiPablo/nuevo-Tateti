package bluetooth_modelo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 *==========================================
 *CLASE POR LA CUAL SE MANEJA, LAS CONEXIONES BLUETOOTH
 *ENTRE DISPOSITIVOS.
 *ESTA CLASE MANEJA LA CONEXION, Y EL ENVIO DE MENSAJES POR BLUETOOTH.
 * @author pablo
 *==========================================
 */
public class BluetoothServices_MultiJugador {
	
	private static final String TAG = "BluetoothServicesChat";
	private static final boolean D = true;
	private static final String NAME =  "BluetoothMultiChat";
	private BluetoothAdapter btAdapter = null;
	private Handler manejador = null;
	private AcceptThread mAcceptThread;
	private ConnectThread mConnectThread;
	private ConnectedThread mConnectedThread;
	private int state = 0;
	
	private ArrayList<String> Device_Addresses;
	private ArrayList<ConnectedThread> ConnThreads;
	private ArrayList<BluetoothSocket> Sockets;
	
	private ArrayList<UUID> Uuids;
	
	public static final int STATE_NONE = 0;
	public static final int STATE_LISTEN = 1;
	public static final int STATE_CONNECTING = 2;
	public static final int STATE_CONNECTED = 3;
	
	/**
	 * constructor de la clase, resive el activity, y un manejador
	 * de mensajes(handler)
	 * @param context (activity)
	 * @param manejador (desde aqui se manejan los mensajes resividos)
	 */
	public BluetoothServices_MultiJugador(Context context, Handler manejador){
		this.btAdapter = BluetoothAdapter.getDefaultAdapter();
		this.state = STATE_NONE;
		this.manejador = manejador;
		this.Device_Addresses = new ArrayList<String>();
		this.ConnThreads = new ArrayList<ConnectedThread>();
		this.Sockets = new ArrayList<BluetoothSocket>();
		this.Uuids = new ArrayList<UUID>();
		this.Uuids.add(UUID.fromString("b7746a40-c758-4868-aa19-7ac6b3475dfc"));
		this.Uuids.add(UUID.fromString("2d64189d-5a2c-4511-a074-77f199fd0834"));
		this.Uuids.add(UUID.fromString("e442e09a-51f3-4a7b-91cb-f638491d1412"));
		this.Uuids.add(UUID.fromString("a81d6504-4536-49ee-a475-7d96d09439e4"));
		this.Uuids.add(UUID.fromString("aa91eab1-d8ad-448e-abdb-95ebba4a9b55"));
		this.Uuids.add(UUID.fromString("4d34da73-d0a4-4f40-ac38-917e0a9dee97"));
		this.Uuids.add(UUID.fromString("5e14d4df-9c8a-4db7-81e4-c937564c86e0"));
	}

	

	/**
	 * METODO QUE RECIBE EL ETADO DE LA CONEXION
	 * @param state
	 */
	private synchronized void setState(int state){
		this.state = state;
		this.manejador.obtainMessage(1,this.state,-1).sendToTarget();
	}
	
	/**
	 * 
	 * @return
	 */
	public synchronized int getState(){
		return this.state;
	}
	
	/**
	 * METODO POR EL CUAL SE ESTABLECE EL CANAL
	 * DE COMUNICACION ENTRE DISPOSITIVOS
	 */
	public synchronized void start(){
		if(this.mConnectThread != null){
			this.mConnectThread.cancel();
			this.mConnectThread = null;
		}
		
		if(this.mConnectedThread != null){
			this.mConnectedThread.cancel();
			this.mConnectedThread = null;
		}
		
		if(this.mAcceptThread == null){
			this.mAcceptThread = new AcceptThread();
			this.mAcceptThread.start();
		}
		
		setState(STATE_LISTEN);
	}
	
	/**
	 * 
	 * @param device
	 */
	public synchronized void connect(BluetoothDevice device){
		if(this.state == STATE_CONNECTING){
			if(this.mConnectThread != null){
				this.mConnectThread.cancel();
				this.mConnectThread = null;
			}
		}
		
		if(this.mConnectedThread != null){
			this.mConnectedThread.cancel();
			this.mConnectedThread = null;
		}
		
	    for (int i = 0; i < 7; i++) {
	        try {
	            this.mConnectThread = new ConnectThread(device, this.Uuids.get(i));
	            this.mConnectThread.start();
	            setState(STATE_CONNECTING);
	        } catch (Exception e) {
	        	
	        }
	    }
	}
	
	/**
	 * 
	 * @param socket
	 * @param device
	 */
	public synchronized void connected(BluetoothSocket socket, BluetoothDevice device){
		this.mConnectedThread = new ConnectedThread(socket);
		this.mConnectedThread.start();
		this.ConnThreads.add(this.mConnectedThread);
		Message msg = this.manejador.obtainMessage(4);
        Bundle bundle = new Bundle();
        bundle.putString("device_name", device.getName());
        msg.setData(bundle);
        this.manejador.sendMessage(msg);
        setState(STATE_CONNECTED);
	}
	
	/**
	 * 
	 */
	public synchronized void stop() {
        if (D) Log.d(TAG, "stop");
        if (this.mConnectThread != null) {
        	this.mConnectThread.cancel(); 
        	this.mConnectThread = null;
        }
        if (this.mConnectedThread != null) {
        	this.mConnectedThread.cancel(); 
        	this.mConnectedThread = null;
        }
        if (this.mAcceptThread != null) {
        	this.mAcceptThread.cancel(); 
        	this.mAcceptThread = null;
        }
        setState(STATE_NONE);
    }
	
	/**
	 * 
	 * @param out
	 */
	public void write(byte[] out){
		for(int i = 0; i < this.ConnThreads.size(); i++){
			try{
				ConnectedThread r;
				synchronized(this){
					if(this.state != STATE_CONNECTED) return;
					r = this.ConnThreads.get(i);
					r.write(out);
				}
			}catch(Exception ex){
				
			}
		}
	}
	
	/**
	 * 
	 */
	private void connectionFailed() {
	     setState(STATE_LISTEN);
	}

	/**
	 * 
	 */
	private void connectionLost() {
	     setState(STATE_LISTEN);
	     Message msg = this.manejador.obtainMessage(5);
	     Bundle bundle = new Bundle();
	     bundle.putString("toast", "Device connection was lost");
	     msg.setData(bundle);
	     this.manejador.sendMessage(msg);
	}
	
	/*********************************************************
	 * AQUI VAN LAS CLASES QUE ESTAN AGREGADAS A ESTA CLASE
	 *  
	 *********************************************************/
	 
	/**
	 * ================================================
	 * @author pablo
	 *=================================================
	 */
	private class AcceptThread extends Thread {
    	BluetoothServerSocket serverSocket = null;
        
        public AcceptThread() {
        }

        public void run() {
            if (D) Log.d(TAG, "BEGIN mAcceptThread" + this);
            setName("AcceptThread");
            BluetoothSocket socket = null;
            try {
            	// Listen for all 7 UUIDs
            	for (int i = 0; i < 7; i++) {
            		serverSocket = btAdapter.listenUsingRfcommWithServiceRecord(NAME, Uuids.get(i));
                    socket = serverSocket.accept();
                    if (socket != null) {
                    	String address = socket.getRemoteDevice().getAddress();
	                    Sockets.add(socket);
	                    Device_Addresses.add(address);
	                    connected(socket, socket.getRemoteDevice());
                    }	                    
            	}
            } catch (IOException e) {
                Log.e(TAG, "accept() failed", e);
            }
            if (D) Log.i(TAG, "END mAcceptThread");
        }

        public void cancel() {
            if (D) Log.d(TAG, "cancel " + this);
            try {
                serverSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of server failed", e);
            }
        }
    }
	
	
	/**
	 * ============================================
	 * @author pablo
	 *=============================================
	 */
	private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private UUID tempUuid;

        public ConnectThread(BluetoothDevice device, UUID uuidToTry) {
            mmDevice = device;
            BluetoothSocket tmp = null;
            tempUuid = uuidToTry;

            // Get a BluetoothSocket for a connection with the
            // given BluetoothDevice
            try {
                tmp = device.createRfcommSocketToServiceRecord(uuidToTry);        	
            } catch (IOException e) {
                Log.e(TAG, "create() failed", e);
            }
            mmSocket = tmp;
        }

        public void run() {
            Log.i(TAG, "BEGIN mConnectThread");
            setName("ConnectThread");

            // Always cancel discovery because it will slow down a connection
            btAdapter.cancelDiscovery();

            // Make a connection to the BluetoothSocket
            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                mmSocket.connect();
            } catch (IOException e) {
            	if (tempUuid.toString().contentEquals(Uuids.get(6).toString())) {
                    connectionFailed();
            	}
                // Close the socket
                try {
                    mmSocket.close();
                } catch (IOException e2) {
                    Log.e(TAG, "unable to close() socket during connection failure", e2);
                }
                // Start the service over to restart listening mode
                BluetoothServices_MultiJugador.this.start();
                return;
            }

            // Reset the ConnectThread because we're done
            /*synchronized (BluetoothChatService.this) {
                mConnectThread = null;
            }*/

            // Start the connected thread
            connected(mmSocket, mmDevice);
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }
	 
	
	/**
	 *==================================================
	 * @author pablo
	 *==================================================
	 */
	private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            Log.d(TAG, "create ConnectedThread");
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "temp sockets not created", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            Log.i(TAG, "BEGIN mConnectedThread");
            byte[] buffer = new byte[1024];
            int bytes;

            // Keep listening to the InputStream while connected
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);

                    // Send the obtained bytes to the UI Activity
                    manejador.obtainMessage(2, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    Log.e(TAG, "disconnected", e);
                    connectionLost();
                    break;
                }
            }
        }

        /**
         * Write to the connected OutStream.
         * @param buffer  The bytes to write
         */
        public void write(byte[] buffer) {
            try {
                mmOutStream.write(buffer);

                // Share the sent message back to the UI Activity
                manejador.obtainMessage(3, -1, -1, buffer)
                        .sendToTarget();
            } catch (IOException e) {
                Log.e(TAG, "Exception during write", e);
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }
	
}//fin clase BluetoothServices
