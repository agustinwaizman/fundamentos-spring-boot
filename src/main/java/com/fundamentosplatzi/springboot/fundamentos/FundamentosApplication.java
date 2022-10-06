package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	public FundamentosApplication(@Qualifier("componentToImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("Usuario encontrado: " + userRepository.findByUserEmail("Micaela@example.com").orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("J", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("Usuarios ordenados por id " + user));
	}

	private void saveUsersInDataBase(){
		User user1 = new User("John", "john@example.com", LocalDate.of(2022, 6, 20));
		User user2 = new User("Julia", "julia@example.com", LocalDate.of(2022, 7, 22));
		User user3 = new User("Francisco", "Francisco@example.com", LocalDate.of(2022, 12, 2));
		User user4 = new User("Pablo", "Pablo@example.com", LocalDate.of(2021, 10, 4));
		User user5 = new User("Federico", "Federico@example.com", LocalDate.of(2020, 9, 16));
		User user6 = new User("Micaela", "Micaela@example.com", LocalDate.of(2013, 3, 27));
		User user7 = new User("Damian", "Damian@example.com", LocalDate.of(2019, 2, 13));
		User user8 = new User("Agustin", "Agustin@example.com", LocalDate.of(2014, 1, 27));
		User user9 = new User("Franco", "Franco@example.com", LocalDate.of(2018, 6, 30));
		User user10 = new User("Matias", "Matias@example.com", LocalDate.of(2016, 4, 15));
		User user11 = new User("Pedro", "Pedro@example.com", LocalDate.of(2016, 8, 5));
		User user12 = new User("Juan", "Juan@example.com", LocalDate.of(2016, 5, 25));
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
		list.stream().forEach(userRepository::save);
	}
	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " - " + userPojo.getPassword() + " - " + userPojo.getAge());
		try{
			int value = 10/0;
			LOGGER.debug("Mi valor: "+value);
		}catch (Exception e){
			LOGGER.error("Esto es un error al dividir por cero" + e);
		}
	}
}
