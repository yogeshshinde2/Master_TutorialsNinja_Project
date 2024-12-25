package com.framework.factories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.constants.Constants;
//import com.pim.driver.DriverManager;
import com.framework.enums.WaitLogic;
import com.framework.utils.*;

public final class ExplicitWaitFactory {
	
	

		   private ExplicitWaitFactory() {
		   }


		   public static WebElement performExplicitWait(WaitLogic waitstrategy, By by) {
		      WebElement element = null;
		      if (waitstrategy == WaitLogic.CLICKABLE) {
		         element = new WebDriverWait(Utilities.getDriver(), Constants.getExplicitWait())
		               .until(ExpectedConditions.elementToBeClickable(by));
		      } else if (waitstrategy == WaitLogic.PRESENCE) {
		         element = new WebDriverWait(Utilities.getDriver(), Constants.getExplicitWait())
		               .until(ExpectedConditions.presenceOfElementLocated(by));
		      } else if (waitstrategy == WaitLogic.VISIBLE) {
		         element = new WebDriverWait(Utilities.getDriver(), Constants.getExplicitWait())
		               .until(ExpectedConditions.visibilityOfElementLocated(by));
		      } else if (waitstrategy == WaitLogic.NONE) {
		         element = Utilities.getDriver().findElement(by);
		      }
		      else if (waitstrategy == WaitLogic.INVISIBLE) {
				  new WebDriverWait(Utilities.getDriver(), Constants.getExplicitWait())
						.until(ExpectedConditions.invisibilityOf(Utilities.getDriver().findElement(by)));

			}
		      return element;
		   }
}


