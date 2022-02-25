package p3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class AdTest {

	@Test
	void test() {
		User a = new User("DNiculae","1234",1);
		assertEquals(1,a.verifyLogin("DNiculae","1234"),"Login nereusit");
	}
	
	@Test
	void test2() {
		User a = new User("DNiculae","1234",1);
		assertEquals(0,a.verifyLogin("DNiculae","123"),"Login nereusit");
	}
	
	@Test
	void test3() {
		User a = new User("DNiculae","1234",1);
		assertEquals(0,a.verifyLogin("DNicula","1234"),"Login nereusit");
	}
	
	@Test
	void test4() {
		User a = new User("DNiculae","1234",1);
		assertEquals(0,a.verifyLogin("DNicula","123"),"Login nereusit");
	}
	
	@Test
	void test5() {
		User a = new User("DNiculae","1234",2);
		assertEquals(2,a.verifyLogin("DNiculae","1234"),"Login nereusit");
	}
	
	@Test
	void test6() {
		Storehouse a = new Storehouse(201,200,200,200,2000,2000,2000,2000);
		assertFalse(a.verifyInk(),"Verificare invalida");
	}
	@Test
	void test7() {
		Storehouse a = new Storehouse(2,200,200,200,2000,2000,2000,2000);
		assertTrue(a.verifyInk(),"Verificare invalida");
	}
	@Test
	void test8() {
		Storehouse a = new Storehouse(201,20,200,200,2000,2000,2000,2000);
		assertFalse(a.verifyInk(),"Verificare invalida");
	}
	@Test
	void test9() {
		Storehouse a = new Storehouse(2,20,200,200,2000,2000,2000,2000);
		assertTrue(a.verifyInk(),"Verificare invalida");
	}
	@Test
	void test10() {
		List<Dept> a = new ArrayList<Dept>();
		a.add(new Dept(10,"Workers",5));
		a.add(new Dept(20,"Workers",1));
		assertSame(a.get(1),LoginFrame.getmindept(a),"Departament invalid");
	}
}
