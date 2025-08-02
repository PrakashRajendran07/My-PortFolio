package org.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
		public static WebDriver driver;
		// Singleton driver getter
		public static WebDriver getDriver() {
			if (driver == null) {
				driver = new SafariDriver();
			}
			return driver;
		}
		//String screenShotFile;
		public static void chromeLaunch(String URL) {
			driver = new ChromeDriver();
			driver.get(URL);
			}
		public static void safariLaunch(String URL) {
			driver = new SafariDriver();
			driver.get(URL);
			
		}
		public static void click(WebElement e) {
			e.click();
		}
		 public static void closeBrowser() { 
		        if (driver != null) {
		            driver.quit();
		            driver = null;
		        }
		    }
		 public void waitUntilVisible(WebElement element, int timeoutSeconds) {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		        wait.until(ExpectedConditions.visibilityOf(element));
		    }
		 public void waitForVisibilityOfAllElements(List<WebElement> elements, int timeoutSeconds) {
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
			    wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			}
		 public boolean isElementVisible(WebElement element, int timeoutSeconds) {
		        try {
		            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		            wait.until(ExpectedConditions.visibilityOf(element));
		            return true;
		        } catch (Exception e) {
		            return false;
		        }
		    }
		 public void waitForVisibilityOfElement(By locator, int timeoutInSeconds) {
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			}
		 public static WebElement waitForPresence(By locator, int timeoutInSeconds) {
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			}
		 
		 public void waitForPageToLoad() {
			    new WebDriverWait(driver, Duration.ofSeconds(10)).until(
			        webDriver -> ((JavascriptExecutor) webDriver)
			            .executeScript("return document.readyState")
			            .equals("complete")
			    );
		 }
			    
			    
		public static void scriptClick(WebElement clickPoint) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click", clickPoint);
		}
		public static void typeValueJS(WebElement sendValueElement, String valueToType) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].setAttribute('value', arguments[1]);", sendValueElement, valueToType);
		}
		
		public static void typeValue(WebElement e, String value) {
			e.sendKeys(value);
		}
		public static void hover(WebElement hoverPoint) {
			Actions ac = new Actions(driver);
			ac.moveToElement(hoverPoint).perform();
		}
		public static void dragAndDrop(WebElement dragElement, WebElement dropElement) {
			Actions ac = new Actions(driver);
			ac.dragAndDrop(dragElement, dropElement).perform();
		}
		public static void doubleClick(WebElement clickPoint) {
			Actions ac = new Actions(driver);
			ac.doubleClick(clickPoint).perform();
		}
		public static void rightClick(WebElement clickPoint) {
			Actions ac = new Actions(driver);
			ac.contextClick(clickPoint).perform();
		}
		public static void acceptAlert() {
			Alert al = driver.switchTo().alert();
			al.accept();
		}
		public static void dismissAlert() {
			Alert al = driver.switchTo().alert();
			al.dismiss();
		}
		public static void textAlert(String alertText) {
			Alert al= driver.switchTo().alert();
			al.sendKeys(alertText);
			al.accept();
		}
		public static void scrollDown(WebElement scrollDownPoint) {
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true)", scrollDownPoint);
		}
		public static void scrollUp(WebElement scrollUpPoint) {
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(false)", scrollUpPoint);
		}
		public static void selectDropdownOption(WebElement dropdownElement, String selectionType, String value) {
	        Select dropdown = new Select(dropdownElement);
	        switch (selectionType.toLowerCase()) {
	            case "index":
	                try {
	                    int index = Integer.parseInt(value);
	                    dropdown.selectByIndex(index);
	                } catch (NumberFormatException e) {
	                    System.out.println("Invalid index value provided: " + value);
	                }
	                break;

	            case "value":
	                dropdown.selectByValue(value); // Select by the value attribute
	                break;

	            case "visibletext":
	                dropdown.selectByVisibleText(value); // Select by visible text
	                break;

	            default:
	                System.out.println("Invalid selection type: " + selectionType);
	                break;
	        }
	    }
		public static void switchWindow(int windowID) {
			Set<String> allIds = driver.getWindowHandles();
			LinkedList<String> listIds = new LinkedList<String>();
			listIds.addAll(allIds);
			driver.switchTo().window(listIds.get(windowID));
		}
		public String getText(WebElement element) {
	        return element.getText();
	    }
		
		public double parseCurrency(String balanceText) {
		    return Double.parseDouble(balanceText.replace("$", "").replace(",", "").trim());
		}


		
		public String captureScreen(String tname) throws IOException{
			String timeStamp=new SimpleDateFormat("yyyyMMddHHss").format(new Date());
			TakesScreenshot ts= (TakesScreenshot)driver;
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);
			String targetFilePath=System.getProperty("user.dir")+ "/screenshots/"+ tname + "_" + timeStamp + ".png";
			File targetFile=new File(targetFilePath);
			sourceFile.renameTo(targetFile);
			return targetFilePath;
			};
			
			public String captureScreen1(String tname, String folder) throws IOException {
			    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			    TakesScreenshot ts = (TakesScreenshot) driver;
			    File sourceFile = ts.getScreenshotAs(OutputType.FILE);

			    // Create folder path dynamically based on user input
			    String folderPath = System.getProperty("user.dir") + "/screenshots/" + folder;
			    File folderDir = new File(folderPath);
			    if (!folderDir.exists()) {
			        folderDir.mkdirs(); // Create folder if it doesn't exist
			    }

			    // Build target file path inside specified folder
			    String targetFilePath = folderPath + "/" + tname + "_" + timeStamp + ".png";
			    File targetFile = new File(targetFilePath);

			    // Move screenshot file
			    sourceFile.renameTo(targetFile);
			    return targetFilePath;
			}
			public String getElementText(WebElement element) {
			    String text = "";
			    try {
			        text = element.getText().trim();
			    } catch (Exception e) {
			        System.out.println("Unable to retrieve text: " + e.getMessage());
			    }
			    return text;
			}
		public String readExcelFile(String filename, String sheetNo, int rowNum, int cellNum) throws IOException {
			String filePath = System.getProperty("user.dir") + File.separator + "ExternalFiles" + File.separator + filename + ".xlsx";
		    File file = new File(filePath);

		    if (!file.exists()) {
		        throw new FileNotFoundException("Excel file not found at: " + filePath);
		    }
			FileInputStream fin= new FileInputStream(file);
			XSSFWorkbook book = new XSSFWorkbook(fin);
			XSSFSheet sheet = book.getSheet(sheetNo);
			XSSFRow row = sheet.getRow(rowNum);
			XSSFCell cell = row.getCell(cellNum);
			int cellType = cell.getCellType().getCode();
			String data = "";
			if (cellType==1) {
				data = cell.getStringCellValue();
			}
			else if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
				data = simple.format(date);
			}
			else {
				double d = cell.getNumericCellValue();
				long l=(long)d;
				data=String.valueOf(l);
			}
			book.close();
			return data;

		}
		public void writeIntoExcel(String filename,String sheetName,int rowNum, int cellNum, String value) throws IOException {
			File file = new File(System.getProperty("user.dir")+"/ExternalFiles/"+filename+".xlsx");
			Workbook book =new XSSFWorkbook();
			Sheet sheet = book.createSheet(sheetName);
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			cell.setCellValue(value);
			FileOutputStream fo=new FileOutputStream(file);
			book.write(fo);
		}
		public void writeIntoExcel1(String filename, String sheetName,int rowNum, int cellNum, Object value) throws IOException {
	        String filePath = System.getProperty("user.dir") + "/ExternalFiles/" + filename + ".xlsx";
	        File file = new File(filePath);

	        Workbook book;

	        // If file exists, open it; else create a new workbook
	        if (file.exists()) {
	            FileInputStream fis = new FileInputStream(file);
	            book = WorkbookFactory.create(fis);
	            fis.close();
	        } else {
	            book = new XSSFWorkbook();
	        }

	        Sheet sheet;
	        if (book.getNumberOfSheets() > 0) {
	            sheet = book.getSheetAt(0); // Use first sheet
	        } else {
	            sheet = book.createSheet(sheetName);
	        }

	        // Get or create the row
	        Row row = sheet.getRow(rowNum);
	        if (row == null) {
	            row = sheet.createRow(rowNum);
	        }

	        // Get or create the cell
	        Cell cell = row.getCell(cellNum);
	        if (cell == null) {
	            cell = row.createCell(cellNum);
	        }

	        // Set value based on type
	        if (value instanceof String) {
	            cell.setCellValue((String) value);
	        } else if (value instanceof Integer) {
	            cell.setCellValue((Integer) value);
	        } else if (value instanceof Date) {
	            CellStyle dateStyle = book.createCellStyle();
	            CreationHelper createHelper = book.getCreationHelper();
	            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
	            cell.setCellStyle(dateStyle);
	            cell.setCellValue((Date) value);
	        } else {
	            throw new IllegalArgumentException("Unsupported data type: " + value.getClass().getSimpleName());
	        }

	        // Save changes
	        FileOutputStream fos = new FileOutputStream(file);
	        book.write(fos);
	        book.close();
	        fos.close();

	        System.out.println("Written value (" + value + ") to row " + rowNum + ", cell " + cellNum);
	    }
		

}
