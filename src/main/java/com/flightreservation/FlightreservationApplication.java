package com.flightreservation;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.translate.NumericEntityEscaper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flightreservation.entities.Student;
import com.flightreservation.entities.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SpringBootApplication
public class FlightreservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightreservationApplication.class, args);
		

		try {
//			String filePath = "E:\\2.My\\5.JAVA\\11.NhaSachTinHoc\\2.8441_report\\"
//					+ "\\workspace\\flightreservation\\flightreservation\\src\\main\\resources\\FirstReport.jrxml";
//			
//			Map<String, Object> parameters = new HashMap<String, Object>();
//			parameters.put("studentName", "John");
//			
//			Student student1 = new Student(1L, "Raj", "Joshi", "Happy Street",
//					"Delhi");
//			
//			Student student2 = new Student(2L, "Peter", "Smith", "Any Street",
//					"Mumbai");
//			
//			List<Student> list = new ArrayList<Student>();
//			list.add(student1);
//			list.add(student2);
//			
//			JRBeanCollectionDataSource dataSource = 
//					new JRBeanCollectionDataSource(list);
//			
//			JasperReport report = JasperCompileManager.compileReport(filePath);
//			
//			JasperPrint print = 
//					JasperFillManager.fillReport(report, parameters, dataSource);
//			
//			JasperExportManager.exportReportToPdfFile(print, "E:\\2.My\\5.JAVA\\11.NhaSachTinHoc\\2.8441_report\\workspace\\flightreservation\\flightreservation\\src\\main\\resources\\FirstReport.pdf");
//			
			// subject
			
			String filePath = "E:\\2.My\\5.JAVA\\11.NhaSachTinHoc\\2.8441_report"
					+ "\\workspace\\flightreservation\\flightreservation\\src\\main\\resources\\Student1.jrxml";
			
			Subject subject1 = new Subject("Java1", 10);
			Subject subject2 = new Subject("MySQL", 70);
			Subject subject3 = new Subject("PHP", 50);
			Subject subject4 = new Subject("MongoDB", 40);
			Subject subject5 = new Subject("C++", 60);
			
			List<Subject> list = new ArrayList<Subject>();
			list.add(subject1);
			list.add(subject2);
			list.add(subject3);
			list.add(subject4);
			list.add(subject5);
			
			JRBeanCollectionDataSource dataSource = 
					new JRBeanCollectionDataSource(list);
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("studentName", "John");
			parameters.put("tableData", dataSource);
			
			JasperReport report = JasperCompileManager.compileReport(filePath);
			
			JasperPrint print = 
					JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

			JasperExportManager.exportReportToPdfFile(print,
			"E:\\2.My\\5.JAVA\\11.NhaSachTinHoc\\2.8441_report\\workspace\\flightreservation\\flightreservation\\src\\main\\resources\\Student.pdf");
			
			System.out.println("Report Created...");
			
//			String str1 ="<>&& test 123<>%";
//			System.out.println("escapeXml..." + NumericEntityEscaper.below(0x20).translate(StringEscapeUtils.escapeXml(str1)));
			
		} catch(Exception e) {
			System.out.println("Exception while creating report");
		}
	}

}
