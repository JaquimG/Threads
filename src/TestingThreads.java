
public class TestingThreads {
	static int[] buffer = new int[10];
	
	public static void main(String[] args) {
		initBuffer();
		new Thread(producer).start();
		new Thread(consumer).start();
	}
	
	private static void showBuffer() {
		for(int i=0;i<buffer.length;i++) {
			System.out.println(buffer[i]);
		}
		System.out.println("\n");
	}
	
	private static void initBuffer() {
		for(int i=0;i<buffer.length;i++) {
			buffer[i] = 0;
		}
	}
	
	private static Runnable producer = new Runnable() {
		
		@Override
		public void run() {
			try {
				while(!isBufferFull()){
					Thread.sleep(4000);
					addOnBuffer(1);
					showBuffer();
					}
				Thread.sleep(5000);
				consumer.run();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	
	private static Runnable consumer = new Runnable() {
		
		@Override
		public void run() {
			try {
				while(!isBufferVoid()) {
					Thread.sleep(5000);
					consumeOnBuffer();
					showBuffer();
				}
				Thread.sleep(5000);
				producer.run();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	};
	
	
    private static boolean isBufferFull() {
    	int count = 0;
    	for(int i=0; i<buffer.length;i++) {
    		if(buffer[i]!=0) {
    			count++;
    		}
    	}
    	if(count == buffer.length) {
    		return true;
    	}
    	return false;
    }
    
    private static boolean isBufferVoid() {
    	int count = 0;
    	for(int i=0; i<buffer.length;i++) {
    		if(buffer[i]!=0) {
    			count++;
    		}
    	}
    	if(count == 0) {
    		return true;
    	}
    	return false;
    }
	
    private static void addOnBuffer(int value) {
    	for(int i=0;i<buffer.length;i++) {
    		if(buffer[i]==0) {
    			buffer[i] = value;
    			break;
    		}
    	}
    }
    
    private static void consumeOnBuffer() {
    	for(int i=buffer.length-1;i>=0;i--) {
    		if(buffer[i]!=0) {
    			buffer[i] = 0;
    			break;
    		}
    	}
    }
}
