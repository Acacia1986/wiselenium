package org.wiselenium.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.DummyFieldWithWebElementInjectThroughConstructor;

@SuppressWarnings("javadoc")
public class DummyPageWithWebDriverInjectedThoughConstructor extends Page {
	
	public static final String URL = "button.html";
	
	@FindBy(id = "button")
	private DummyFieldWithWebElementInjectThroughConstructor dummy;
	
	
	public DummyPageWithWebDriverInjectedThoughConstructor(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getDummyElementWebElement() {
		return this.dummy.getWebElement();
	}
	
}
