package org.wiselenium.core.pagefactory;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Class responsible for decorating WebElements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class WiseDecorator implements ExtendedSeleniumDecorator {
	
	private final ExtendedSeleniumDecoratorChain decoratorChain;
	
	
	/**
	 * @param factory The factory of the locator of the elements.
	 */
	public WiseDecorator(ElementLocatorFactory factory) {
		ExtendedSeleniumDecoratorChain extendedDefaultSeleniumDecorator = new DefaultExtendedSeleniumDecoratorChain(
			factory);
		ExtendedSeleniumDecoratorChain wiseFrameDecorator = new WiseFrameDecoratorChain(factory)
			.setNext(extendedDefaultSeleniumDecorator);
		ExtendedSeleniumDecoratorChain wiseContainerDecorator = new WiseContainerDecoratorChain(
			factory).setNext(wiseFrameDecorator);
		ExtendedSeleniumDecoratorChain wiseFieldDecorator = new WiseFieldDecoratorChain(factory)
			.setNext(wiseContainerDecorator);
		
		this.decoratorChain = wiseFieldDecorator;
	}
	
	@Override
	public <E> List<E> decorate(Class<E> clazz, List<WebElement> webElements) {
		return this.decoratorChain.decorate(clazz, webElements);
	}
	
	@Override
	public <E> E decorate(Class<E> clazz, WebElement webElement) {
		return this.decoratorChain.decorate(clazz, webElement);
	}
	
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		return this.decoratorChain.decorate(loader, field);
	}
	
}
