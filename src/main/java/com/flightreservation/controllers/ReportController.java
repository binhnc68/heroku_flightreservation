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

import com.flightreservation.entities.Department;
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
public class ReportController {

	@Autowired
	private UserRepository userRepository;


	@GetMapping("/department")
	public ResponseEntity<byte[]> showJasperReportParams(@RequestParam("code") String code) throws JRException, FileNotFoundException {
//
//		\src\main\webapp\WEB-INF\report
		FileInputStream filePath = new FileInputStream("src\\main\\webapp\\WEB-INF\\report\\Department.jrxml");

		Department department1 = new Department(1L, "BP01", "Lập Trình", "Kỹ thuật",
				10L);
		
		Department department2 = new Department(2L, "BP02", "Kinh Doanh", "Kinh doanh",
				20L);
		Department department3= new Department(3L, "BP03", "Van phong", "van phong",
				30L);
		
		List<Department> list = new ArrayList<Department>();
		list.add(department1);
		list.add(department2);
		list.add(department3);
		
		JRBeanCollectionDataSource dataSource = 
				new JRBeanCollectionDataSource(list);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("departmentCode", code);
		parameters.put("tableData", dataSource);
		
		JasperReport report = JasperCompileManager.compileReport(filePath);
		
		JasperPrint print = 
				JasperFillManager.fillReport(report, parameters, dataSource);
		
//		JasperExportManager.exportReportToPdfFile(print, "E:\\2.My\\5.JAVA\\11.NhaSachTinHoc\\2.8441_report\\workspace\\flightreservation\\flightreservation\\src\\main\\resources\\FirstReport.pdf");
		byte[] data= JasperExportManager.exportReportToPdf(print);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=Department.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	
	
}