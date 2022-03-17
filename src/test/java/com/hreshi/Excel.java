package com.hreshi;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
// import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;
import java.io.File;
import java.io.FileOutputStream;

public class Excel {

	public static File make (List<Problem> problemList) {
		File result = null;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Codeforces Problem list");
		for (int i = 0;i < problemList.size();i++) {
			inserProblemtIntoRow(sheet.createRow(i), problemList.get(i),workbook);
		}
		try {
			result = new File("problem.xlsx");
			FileOutputStream stream = new FileOutputStream(result);
			workbook.write(stream);
			stream.close();
		} catch (Exception e) {
			System.out.println("Did not work" + e.toString());
		}
		return result;
	}

	private static void inserProblemtIntoRow (Row row, Problem problem, XSSFWorkbook book) {
		Cell indexCell = row.createCell(0);
		indexCell.setCellValue(row.getRowNum()-1);

		Cell codeCell = row.createCell(1);
		codeCell.setCellValue(problem.code);
		// codeCell.setHyperlink(getHyperLink(problem.link, problem.code, book));

		Cell nameCell = row.createCell(2);
		nameCell.setCellValue(problem.name);
		// nameCell.setHyperlink(getHyperLink(problem.link, problem.name, book));

		Cell tagCell = row.createCell(3);
		tagCell.setCellValue(problem.tags);

		Cell ratingCell = row.createCell(4);
		ratingCell.setCellValue(problem.rating);

		Cell solvedByCell = row.createCell(5);
		solvedByCell.setCellValue(problem.solvedBy); 
	}

	// private static XSSFHyperlink getHyperLink (String link, String label, XSSFWorkbook book) {
	// 	XSSFHyperlink hyperlink = new XSSFCreationHelper.getCreationHelper().createHyperlink(HyperlinkType.URL);
	// 	hyperlink.setAddress(link);
	// 	hyperlink.setLabel(label);
	// 	return hyperlink;
	// }
}