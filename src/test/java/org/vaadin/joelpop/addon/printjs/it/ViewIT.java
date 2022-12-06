package org.vaadin.joelpop.addon.printjs.it;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.vaadin.joelpop.addon.printjs.element.ViewElement;
import org.vaadin.joelpop.util.BaseTestCase;

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
        viewElement.printDefaultPdfFileName();
    }


    @AfterMethod
    @Override
    protected void teardown() {
        super.teardown();
    }

}
