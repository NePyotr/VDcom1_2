package VDcom.VDcom1_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.locks.Lock;

public class Job implements Runnable {

	private final Lock lock;
	private final int finalValue;
	private final String filePath;
	private final PrintStream out;
	
	/**
	 * 
	 * @param finalValue - финальное значение, которого нужно настигнуть
	 * @param lock - реализация интерфейса Lock, монитор которого будем использовать для блокировок
	 */
	public Job(String filePath, int finalValue, Lock lock, PrintStream out) {
		this.lock = lock;
		this.finalValue = finalValue;
		this.filePath = filePath;
		this.out = out;
	}
	
	public void run() {
		
		int oldValue = -1;
		int newValue = -1;
		
		do {
		
			lock.lock(); // TODO вместо Lock, например, можно было бы передавать какой нибудь объект, монитор которого
						 // будем использовать и здесь применять блок синхронизации на этом объекте
			try {		
		        try (FileInputStream fis = new FileInputStream(filePath); DataInputStream dis = new DataInputStream(fis)) {
		        	oldValue = dis.readInt();
				} catch (IOException e) {
					out.println( "Произошла ошибка при чтении файла!" );
					return;
				}
		        
		        if (oldValue >= finalValue || Thread.currentThread().isInterrupted()) return;
		        
		        newValue = oldValue + 1;
		        
		        out.println("Старое значение: " + oldValue + "; Новое значение: " + newValue + "; Идентификатор потока: " + Thread.currentThread().getId() + ";");
		
		        try (FileOutputStream fos = new FileOutputStream(filePath); DataOutputStream dos = new DataOutputStream(fos)) {
		        	dos.writeInt(newValue);
		        	dos.flush();
				} catch (IOException e) {
					out.println( "Произошла ошибка при записи в файл!" );
					return;
				}
			}
	        finally {
	        	lock.unlock();
	        }
		
		} while (newValue < finalValue && !Thread.currentThread().isInterrupted());

	}

}
