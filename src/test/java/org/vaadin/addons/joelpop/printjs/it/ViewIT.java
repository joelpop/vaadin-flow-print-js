package org.vaadin.addons.joelpop.printjs.it;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.vaadin.addons.joelpop.printjs.element.ViewElement;
import org.vaadin.addons.joelpop.util.BaseTestCase;

/**
 * For Safari:
 *
 * When the "This webpage is trying to print. Do you want to print this webpage?" dialog
 * appears in response to attempting to print,
 * <ul>
 *     <li>to dismiss the dialog - either send an escape or send a space</li>
 *     <li>to confirm the dialog - either send a return or send a tab, then send a space</li>
 * </ul>
 *
 *
 */
public class ViewIT extends BaseTestCase {

    private ViewElement viewElement;

    @BeforeClass
    @Override
    protected void initWebDriverManager() {
        super.initWebDriverManager();
    }

    @BeforeMethod
    @Override
    protected void setup() {
        super.setup();
        viewElement = $(ViewElement.class).onPage().waitForFirst();
    }

    @Test
    public void printDefaultPdfFileNameTest() {
        var frameSource = viewElement.printDefaultPdfFileName();
        System.out.printf("Frame Source: %s%n", frameSource);
    }

    @Test
    public void printDefaultHtmlElementTest() {
        viewElement.printDefaultHtmlElement();
    }

    @Test
    public void printDefaultImageFileNameTest() {
        viewElement.printDefaultImageFileName();
    }

    @Test
    public void printDefaultJsonTest() {
        viewElement.printDefaultJson();
    }

    @Test
    public void printDefaultRawHtmlTest() {
        viewElement.printDefaultRawHtml();
    }


    @AfterMethod
    @Override
    protected void teardown() {
        super.teardown();
    }

}
