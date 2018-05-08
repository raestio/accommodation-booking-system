package com.rasto.accommodationbookingsystem;

import com.rasto.accommodationbookingsystem.backend.data.Role;
import com.rasto.accommodationbookingsystem.backend.data.entity.User;
import com.rasto.accommodationbookingsystem.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AccommodationBookingSystemApplication extends SpringBootServletInitializer implements CommandLineRunner, HasLogger {

	public static void main(String[] args) {
		SpringApplication.run(AccommodationBookingSystemApplication.class, args);

	}

	private UserService userService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	private static final String TEST_USER_EMAIL = "test.user@test.com";
	private static final String TEST_ADMIN_EMAIL = "test.admin@test.com";
	private static final String TEST_USER_PASSWORD = "test.user";
	private static final String TEST_ADMIN_PASSWORD = "test.admin";

	@Override
	public void run(String... args) throws Exception {
		getLogger().info("Creating test user...");

		if (userService.exists(TEST_USER_EMAIL)) {
			getLogger().info("Test user already exists: \nLogin email: " + TEST_USER_EMAIL + "\nPassword: " + TEST_USER_PASSWORD);
		} else {
			User user = userService.createNew();
			user.setEmail(TEST_USER_EMAIL);
			user.setPassword(passwordEncoder.encode(TEST_USER_PASSWORD));
			user.setName("Test");
			user.setSurname("User");
			userService.saveOrUpdate(user);
			getLogger().info("Test user created: \nLogin email: " + TEST_USER_EMAIL + "\nPassword: " + TEST_USER_PASSWORD);
		}


		getLogger().info("Creating test user admin...");
		if (userService.exists(TEST_ADMIN_EMAIL)) {
			getLogger().info("Test user admin already exists: \nLogin email: " + TEST_ADMIN_EMAIL + "\nPassword: " + TEST_ADMIN_PASSWORD);
		} else {
			User user = userService.createNew();
			user.setEmail(TEST_ADMIN_EMAIL);
			user.setPassword(passwordEncoder.encode(TEST_ADMIN_PASSWORD));
			user.setName("Admin");
			user.setSurname("Test");
			user.setRole(Role.ADMIN);
			userService.saveOrUpdate(user);
			getLogger().info("Test user admin created: \nLogin email: " + TEST_ADMIN_EMAIL + "\nPassword: " + TEST_ADMIN_PASSWORD);
		}
	}
}
