package com.neurosky.thinkgear;

public class Hello {
	public static void main(String args[]){
		//1. output the version
		System.out.println("Dll Version is: "+ThinkGear.GetDriverVersion());
		
		int connectionId  = ThinkGear.GetNewConnectionId();
		System.out.print("Connection id is: "+connectionId);
		if(connectionId < 0){
			System.out.println("ERROR: TG_GetNewConnectionId() returned " + connectionId);
			return;
		}
		
		String comPortName = "\\\\.\\COM3"; //com port name depend on the dongle
		
		int result = ThinkGear.Connect(connectionId, comPortName, ThinkGear.BAUD_57600, ThinkGear.STREAM_PACKETS);
		if(result <0){
			System.out.println("ERROR: Connect() returned " + result);
			return;
		}
		
		long startTime = System.currentTimeMillis();
		int packetsRead = 0;
		while(true){

			do{
				packetsRead = ThinkGear.ReadPackets(connectionId, 1);
				
				if(packetsRead == 1){
					if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_RAW) != 0){
						System.out.println("Get a raw data: " + ThinkGear.GetValue(connectionId, ThinkGear.DATA_RAW));
						
					}
				}
			}while(packetsRead > 0);
			
		}
		
		//ThinkGear.FreeConnection(connectionId);
		
	}
	public static long getSeconds(long msBig, long msLess){
		return (msBig -  msLess)/1000;
	}
}
