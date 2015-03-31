package com.srt;

import org.jboss.logging.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.srt.dao.StudentDAO;
import com.srt.model.Student;

class App {

	private static String FIRSTNAME = "abcd";
	private static String LASTNAME = "efgh";
	private static int ID = 40;
	private static String UPDATED_FIRSTNAME = "Updated FirstName";
	private static final Logger logger=Logger.getLogger(App.class);
	public static void main(String[] args) {
		
		logger.info("Loading application context...");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring.xml");
		StudentDAO studentDAO = context.getBean(StudentDAO.class);
		
		//Setting student properties.
		Student student = new Student();
		student.setFirstName(FIRSTNAME);
		student.setLastName(LASTNAME);
		
		//inserting student values to table Student.
		studentDAO.insert(student);
		logger.info("Entered Student information : " + student);
		
		//updating student.
		Student updateStudent = studentDAO.getValueforID(ID);
		logger.info("Entry values for Student before update "+updateStudent+"");
		if (updateStudent != null) {
			updateStudent.setFirstName(UPDATED_FIRSTNAME);
			studentDAO.update(updateStudent);
			logger.info("Entry values for Student after update "+updateStudent+"");
						
		} else {
			logger.info("cannot update column, as it does not exists");
		}
		
		//deleting
		logger.info("Deleting the student details : "+student);
		studentDAO.delete(student);
		logger.info("student deleted successfully");
		// closing application context.
		context.close();
	}
}