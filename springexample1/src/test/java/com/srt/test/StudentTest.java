package com.srt.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.srt.dao.StudentDAO;
import com.srt.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class StudentTest {

	private StudentDAO studentDAO;

	@Before
	public void init() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring.xml");
		studentDAO = (StudentDAO) context.getBean(StudentDAO.class);
	}

	//checks whether the bean is loaded or not.
	@Test
	public void testBeanNotNull() {
		assertNotNull(studentDAO);
	}
	//check for inserting values into table
	@Test
	public void insertTest() {
		Student student = new Student();
		student.setFirstName("firstName");
		student.setLastName("lastName");
		studentDAO.insert(student);
		int id = student.getId();
		String firstName = studentDAO.getValueforID(id).getFirstName();
		String lastName = studentDAO.getValueforID(id).getLastName();
		assertEquals("firstName", firstName);
		assertEquals("lastName", lastName);
	}
	//check for deleting values into table
	@Test(expected = NullPointerException.class)
	public void deleteTest() {
		Student student = new Student();
		student.setFirstName("deleteFirstName");
		student.setLastName("deleteLastName");
		studentDAO.insert(student);
		studentDAO.delete(student);
		int id = student.getId();
		assertNull(studentDAO.getValueforID(id).getFirstName());
	}
	//check for updating values into table
	@Test
	public void updateTest() {
		Student student = new Student();
		student.setFirstName("firstName");
		student.setLastName("lastName");
		studentDAO.insert(student);
		int id = student.getId();
		student.setFirstName("updatedFirstName");
		student.setLastName("updatedLastName");
		studentDAO.update(student);
		String actualFirstName = studentDAO.getValueforID(id).getFirstName();
		String actualLastName = studentDAO.getValueforID(id).getLastName();
		String expectedFirstName = "updatedFirstName";
		String expectedLastName = "updatedLastName";
		assertEquals(expectedFirstName, actualFirstName);
		assertEquals(expectedLastName, actualLastName);
	}
}
