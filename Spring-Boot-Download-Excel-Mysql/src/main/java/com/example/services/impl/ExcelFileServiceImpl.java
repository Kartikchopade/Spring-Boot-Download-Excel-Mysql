package com.example.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.entity.Contact;
import com.example.services.ExcelFileService;

@Service
public class ExcelFileServiceImpl implements ExcelFileService 
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ByteArrayInputStream export(List<Contact> contacts) {
		try(Workbook workbook = new XSSFWorkbook())
		{
			Sheet sheet=workbook.createSheet("Contacts");
			
			Row row=sheet.createRow(0);
			
			//Define Header Style 
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
			headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			//creating header cell
			Cell cell = row.createCell(0);
			cell.setCellValue("ID");
			cell.setCellStyle(headerCellStyle);
			
			cell = row.createCell(1);
			cell.setCellValue("First Name");
			cell.setCellStyle(headerCellStyle);
			
			cell = row.createCell(2);
			cell.setCellValue("Middle Name");
			cell.setCellStyle(headerCellStyle);
			
			cell = row.createCell(3);
			cell.setCellValue("Last Name");
			cell.setCellStyle(headerCellStyle);
			
			// Creating data rows for each contact
			for(int i = 0; i < contacts.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(contacts.get(i).getId());
	        	dataRow.createCell(1).setCellValue(contacts.get(i).getFirstname());
	        	dataRow.createCell(2).setCellValue(contacts.get(i).getMiddlename());
	        	dataRow.createCell(3).setCellValue(contacts.get(i).getLastname());
			}
			
			// Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
			
		}
		catch (IOException ex) {
			logger.error("Error during export Excel file", ex);
			return null;
		}
		
	}
	
}
