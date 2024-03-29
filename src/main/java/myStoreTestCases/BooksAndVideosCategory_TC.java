package myStoreTestCases;

import myStoreTestObjects.CategoryObjects;
import commonActions.CommonFunctions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class BooksAndVideosCategory_TC extends CommonFunctions {

    String categorized;

    public void categorize()
    {
        test.info("Clicking the books and videos category");
        CategoryObjects.booksAndVideosCategory.click();

        test.info("Getting the categorized result");
        categorized= CategoryObjects.categorizedProducts.getText();
    }

    public void validateCategorizedProducts()
    {
        test.info("Expected result is : Books and Videos");
        test.info("Actual result is : "+categorized);

        if (categorized.equals("Books and Videos"))
        {
            test.pass("The books and videos has been categorized");
        }
        else
        {
            test.fail("The books and videos hasn't been categorized");

            TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
            File screenShot=takesScreenshot.getScreenshotAs(OutputType.FILE);
            File file=new File("books&Videos.png");
            try {
                FileHandler.copy(screenShot,file);
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
            }

            test.addScreenCaptureFromPath("books&Videos.png");

        }
        Assert.assertEquals(categorized,"Books and Videos");
    }

    @Test
    public void booksAndVideos()
    {
        test=reports.createTest("Search books and videos category Test Case");
        PageFactory.initElements(driver,CategoryObjects.class);
        categorize();
        validateCategorizedProducts();
        CategoryObjects.homePage.click();
    }
}
