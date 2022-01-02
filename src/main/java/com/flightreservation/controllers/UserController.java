package com.flightreservation.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightreservation.entities.Student;
import com.flightreservation.entities.User;
import com.flightreservation.repos.UserRepository;
//import com.flightreservation.services.SecurityService;

import javassist.bytecode.ByteArray;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private SecurityService securityService;
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
//
//	@Autowired
//	private BCryptPasswordEncoder encoder;

	@RequestMapping("/showReg")
	public String showRegistrationPage() {
//		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
//		return "test";
	}
    // dang ky thong tin login
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
//		LOGGER.info("Inside register()" + user);
//		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";

	}
	// hien thi form login
	@RequestMapping("/showLogin")
	public String showLoginPage() {
//		LOGGER.info("Inside showLoginPage()");
		return "login/login";
	}
    // kiem tra login 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap) {
		System.out.println("login1:  "+ email);
		User user = userRepository.findByEmail(email);
		System.out.println("user:  "+ user);
		if (user.getPassword().equals(password)) {
			// login thanh cong
			return "findFlights";
		} else {
			// login khong thanh cong
			modelMap.addAttribute("msg", "Invalid user name or password .Please try again.");
		}
		System.out.println("login:  " + email);
		return "login/login";

	}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
//			ModelMap modelMap) {
//		boolean loginResponse = securityService.login(email, password);
//		LOGGER.info("Inside login() and the email is: " + email);
//		if (loginResponse) {
//			return "findFlights";
//		} else {
//			modelMap.addAttribute("msg", "Invalid user name or password .Please try again.");
//		}
//
//		return "login/login";
//
//	}
	
	@GetMapping("/dpf")
	public ResponseEntity<byte[]> showJasperReport() throws JRException, FileNotFoundException {
//
		String filePath1 = "E:\\2.My\\5.JAVA\\11.NhaSachTinHoc\\2.8441_report\\"
		+ "\\workspace\\flightreservation\\flightreservation\\src\\main\\resources\\FirstReport.jrxml";
		
		FileInputStream filePath = new FileInputStream("src\\main\\resources\\FirstReport.jrxml");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("studentName", "John");
		
		Student student1 = new Student(1L, "Raj", "Joshi", "Happy Street",
				"Delhi");
		
		Student student2 = new Student(2L, "Peter", "Smith", "Any Street",
				"Mumbai");
		
		List<Student> list = new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		
		JRBeanCollectionDataSource dataSource = 
				new JRBeanCollectionDataSource(list);
		
		JasperReport report = JasperCompileManager.compileReport(filePath);
		
		JasperPrint print = 
				JasperFillManager.fillReport(report, parameters, dataSource);
		
//		JasperExportManager.exportReportToPdfFile(print, "E:\\2.My\\5.JAVA\\11.NhaSachTinHoc\\2.8441_report\\workspace\\flightreservation\\flightreservation\\src\\main\\resources\\FirstReport.pdf");
		byte[] data= JasperExportManager.exportReportToPdf(print);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=FirstReport.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	

	@GetMapping("/dpfparam")
	public ResponseEntity<byte[]> showJasperReportParam(@RequestParam("name") String name) throws JRException, FileNotFoundException {
//
		String filePath1 = "E:\\2.My\\5.JAVA\\11.NhaSachTinHoc\\2.8441_report\\"
		+ "\\workspace\\flightreservation\\flightreservation\\src\\main\\resources\\FirstReport.jrxml";
		
		FileInputStream filePath = new FileInputStream("src\\main\\resources\\FirstReport.jrxml");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
//		parameters.put("studentName", "John");
		parameters.put("studentName", name);
		
		Student student1 = new Student(1L, "Raj", "Joshi", "Happy Street",
				"Delhi");
		
		Student student2 = new Student(2L, "Peter", "Smith", "Any Street",
				"Mumbai");
		Student student3 = new Student(2L, "Peter", "Smith", "Any Street",
				"Mumbai");
		
		List<Student> list = new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		list.add(student3);
		
		JRBeanCollectionDataSource dataSource = 
				new JRBeanCollectionDataSource(list);
		
		JasperReport report = JasperCompileManager.compileReport(filePath);
		
		JasperPrint print = 
				JasperFillManager.fillReport(report, parameters, dataSource);
		
//		JasperExportManager.exportReportToPdfFile(print, "E:\\2.My\\5.JAVA\\11.NhaSachTinHoc\\2.8441_report\\workspace\\flightreservation\\flightreservation\\src\\main\\resources\\FirstReport.pdf");
		byte[] data= JasperExportManager.exportReportToPdf(print);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=FirstReport.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	
	
	@GetMapping("/showReg2")
	public String showRegistrationPage2() {
//		LOGGER.info("Inside showRegistrationPage()");
		return "test";
	}
	@GetMapping()
	public String showRegistrationPage3() {
//		LOGGER.info("Inside showRegistrationPage()");
		return "test";
	}
}