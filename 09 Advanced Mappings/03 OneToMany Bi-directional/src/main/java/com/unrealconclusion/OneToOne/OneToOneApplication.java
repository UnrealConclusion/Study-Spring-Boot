package com.unrealconclusion.OneToOne;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.unrealconclusion.OneToOne.dao.AppDAO;
import com.unrealconclusion.OneToOne.entity.Course;
import com.unrealconclusion.OneToOne.entity.Instructor;
import com.unrealconclusion.OneToOne.entity.InstructorDetail;

@SpringBootApplication
public class OneToOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneToOneApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			// createInstructor(appDAO);
			// findInstructor(appDAO);
			// deleteInstructor(appDAO);
			// findInstructorDetail(appDAO);
			// deleteInstructorDetail(appDAO);
			// createInstructorWithCourses(appDAO);
			// findInstructorWithCourses(appDAO);
			// findCoursesForInstructor(appDAO);
			// findInstructorWithCoursesJoinFetch(appDAO);
			// updateInstructor(appDAO);
			// updateCourse(appDAO);
			deleteCourse(appDAO);
		};
	}

	// add 2 instructors to the database
	private void createInstructor(AppDAO appDAO) {
		// create an instructor 
		Instructor instructor = new Instructor("Spongebob", "Squarepants", "BobPants@KrustyKrabs.com");

		// create an instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/@Spongebob", "JellyFishing");
		
		// associate the objects
		instructor.setInstructorDetail(instructorDetail);

		// save the instructor (will also save instructor details due to Cascading)
		appDAO.save(instructor);

		// create an second instructor and instructor details
		instructor = new Instructor("Patrick", "Star", "NotRick@KrustyKrabs.com");
		instructorDetail = new InstructorDetail("http://www.youtube.com/@Patrick", "JellyFishing");
		instructor.setInstructorDetail(instructorDetail);
		appDAO.save(instructor);
	}

	// find the instructor with id 1
	private void findInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("instructor: " + instructor);
	}

	// delete the instructor with id 1 
	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting instructor id: " + id);
		appDAO.deleteInstructorById(id);
	}

	// find the instructor detail with id 2 and the associated instructor 
	private void findInstructorDetail(AppDAO appDAO) {
		int id = 2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		// note that instructor info is not printed out because it is not done by the instructorDetail's toString() method
		System.out.println("Instructor Detail: " + instructorDetail);
		System.out.println("Instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 3;
		System.out.println("Deleting instructor detail id: " + id);
		appDAO.deleteInstructorDetailById(id);
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create an instructor 
		Instructor instructor = new Instructor("Squidward", "Tentacles", "Squidward@KrustyKrabs.com");

		// create an instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/@Squidward", "Clarinet");
		
		// associate the instructor detail with the instructor
		instructor.setInstructorDetail(instructorDetail);

		// create some courses
		Course course1 = new Course("Intro to Clarinet");
		Course course2 = new Course("Intro to Sculpting");

		// associate the courses with the instructor 
		instructor.addCourse(course1);
		instructor.addCourse(course2);

		// save the instructor 
		// (will also save instructor details and courses due to Cascading)
		System.out.println("Saving instructor: " + instructor);
		System.out.println("the courses: " + instructor.getCourses());
		appDAO.save(instructor);
	}

	// will throw an error 
	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("instructor: " + instructor);
		System.out.println("courses: " + instructor.getCourses()); 
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("instructor: " + instructor);

		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		System.out.println("courses: " + courses); 
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with id: " + id);

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("instructor: " + instructor);
		System.out.println("courses: " + instructor.getCourses()); 
	};

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		
		// find the instructor 
		System.out.println("Finding instructor with id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		// update the instructor 
		System.out.println("Updating instructor with id: " + id);
		instructor.setEmail("SquidwardTentacles@KrustyKrabs.com");
		appDAO.update(instructor);
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;

		// find course
		System.out.println("Finding course id: " + id);
		Course course = appDAO.findCourseById(id);

		// update course
		System.out.println("Updating course id: " + id);
		course.setTitle("Intro to Painting");
		appDAO.update(course);
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting course id: " + id);
		appDAO.deleteCourseById(id);
	}
}
