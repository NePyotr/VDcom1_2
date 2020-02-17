package VDcom.VDcom1_2;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;
import org.mockito.Mockito;

public class AppTest {
	
	public static final String FILE_TEST_PATH = "C:\\out.tmp";
	public static final int FINAL_TEST_VAL = 10000; // входной параметр - до какого числа считаем

	/**
	 * Проверка результата основной работы
	 * @throws IOException 
	 */
	@Test
	public void testExec() throws IOException {
		PrintStream pst = Mockito.mock(PrintStream.class);
		int result = ThreadExecutorCore.execute(FINAL_TEST_VAL, FILE_TEST_PATH, pst);
		assertEquals(FINAL_TEST_VAL, result); // TODO ради простоты, не буду анализировать весь поток вывода, а сравню только финальный результат
	}
	
	
	/**
	 * Неверные параметры (значение, до которого считаем = 0)
	 * @throws IOException 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExec_WrongPar1() throws IOException {
		PrintStream pst = Mockito.mock(PrintStream.class);
		ThreadExecutorCore.execute(0, FILE_TEST_PATH, pst);
	}
	
	/**
	 * Неверные параметры (значение, до которого считаем < 0)
	 * @throws IOException 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExec_WrongPar2() throws IOException {
		PrintStream pst = Mockito.mock(PrintStream.class);
		ThreadExecutorCore.execute(-100, FILE_TEST_PATH, pst);
	}
	
	/**
	 * Неверные параметры (значение, до которого считаем не кратно 2)
	 * @throws IOException 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExec_WrongPar3() throws IOException {
		PrintStream pst = Mockito.mock(PrintStream.class);
		ThreadExecutorCore.execute(333, FILE_TEST_PATH, pst);
	}
	
	/**
	 * Неверные параметры (не задан путь к файлу)
	 * @throws IOException 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExec_WrongPar4() throws IOException {
		PrintStream pst = Mockito.mock(PrintStream.class);
		ThreadExecutorCore.execute(FINAL_TEST_VAL, null, pst);
	}
	
	/**
	 * Неверные параметры (не задан поток вывода)
	 * @throws IOException 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExec_WrongPar5() throws IOException {
		ThreadExecutorCore.execute(FINAL_TEST_VAL, FILE_TEST_PATH, null);
	}
	
}
