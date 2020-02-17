package VDcom.VDcom1_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadExecutorCore {
	
	private ThreadExecutorCore() {
		// не создаём
	}

	public static int execute(final int finalValue, final String filePath, PrintStream out) throws IOException {
		
		if (finalValue <= 0) {
			throw new IllegalArgumentException("finalValue не может быть <= 0");
		}
		
		if (finalValue % 2 != 0) {
			throw new IllegalArgumentException("finalValue должен быть кратным 2");
		}
		
		if (filePath == null || filePath.length() == 0) {
			throw new IllegalArgumentException("Не указан filePath!");
		}
		
		if (out == null) {
			throw new IllegalArgumentException("Не указан выходной поток!");
		}
        
        try (FileOutputStream fos = new FileOutputStream(filePath); DataOutputStream dos = new DataOutputStream(fos)) {
        	dos.writeInt(0);
        	dos.flush();
		} catch (IOException e) {
			throw e;
		}
        
        Lock lock = new ReentrantLock();
        
        Thread thread1 = new Thread(new Job(filePath, finalValue, lock, out));
        Thread thread2 = new Thread(new Job(filePath, finalValue, lock, out));
        
        thread1.start();
        thread2.start();
        
        try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			thread1.interrupt();
			thread1.interrupt();
		}
        
        int res = -1;
        
        try (FileInputStream fis = new FileInputStream(filePath); DataInputStream dis = new DataInputStream(fis)) {
        	res = dis.readInt();
        	out.println("Итоговый результат: " + res);
		} catch (IOException e) {
			throw e;
		}
        
        return res;
	}
	
}
