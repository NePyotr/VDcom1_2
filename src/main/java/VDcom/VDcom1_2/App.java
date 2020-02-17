package VDcom.VDcom1_2;

import java.io.IOException;

/**
 * VDcom - Тестовое задание (Часть 1, задание 2)
 *
 */
public class App 
{
	
	public static final String FILE_PATH = "C:\\out.txt";
	
    public static void main( String[] args )
    {
        
        final int finalValue = 10000; // входной параметр - до какого числа считаем
        
        try {
			ThreadExecutorCore.execute(finalValue, FILE_PATH, System.out);
		} catch (IOException e) {
			System.out.println( "Произошла ошибка при чтении/записи файла!" );
			return;
		}
        
       /* try (FileOutputStream fos = new FileOutputStream(FILE_PATH); DataOutputStream dos = new DataOutputStream(fos)) {
        	dos.writeInt(0);
        	dos.flush();
		} catch (IOException e) {
			System.out.println( "Произошла ошибка при записи в файл!" );
			return;
		}
        
        Lock lock = new ReentrantLock();
        
        Thread thread1 = new Thread(new Job(FILE_PATH, finalValue, lock, System.out));
        Thread thread2 = new Thread(new Job(FILE_PATH, finalValue, lock, System.out));
        
        thread1.start();
        thread2.start();
        
        try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			thread1.interrupt();
			thread1.interrupt();
		}
        
        try (FileInputStream fis = new FileInputStream(App.FILE_PATH); DataInputStream dis = new DataInputStream(fis)) {
        	System.out.println("Итоговый результат: " + dis.readInt());
		} catch (IOException e) {
			System.out.println( "Произошла ошибка при чтении файла!" );
			return;
		}*/
        
        
    }
}
