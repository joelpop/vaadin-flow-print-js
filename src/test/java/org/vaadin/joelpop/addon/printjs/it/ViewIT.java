package org.vaadin.joelpop.addon.printjs.it;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.vaadin.joelpop.addon.printjs.element.ViewElement;
import org.vaadin.joelpop.util.BaseTestCase;

/**
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


    /**
     * PDF
     *
     * <pre>
     *
     *   <iframe style="visibility: hidden; height: 0; width: 0; position: absolute; border: 0" id="printJS"
     *    src="blob:http://localhost:8080/d0b3b5dc-700a-46de-8b0a-8c473c66c750">
     *     #document
     * </pre>
     */
    @Test
    public void printDefaultPdfFileNameTest() {
        viewElement.printDefaultPdfFileName();
    }

    /**
     * HTML
     *
     * <pre>
     *   <iframe style="visibility: hidden; height: 0; width: 0; position: absolute; border: 0" id="printJS"
     *    >
     *     #document
     * </pre>
     */
    @Test
    public void printDefaultHtmlElementTest() {
        viewElement.printDefaultHtmlElement();
    }

    /**
     * IMAGE
     *
     * <pre>
     *   <iframe style="visibility: hidden; height: 0; width: 0; position: absolute; border: 0" id="printJS"
     *    >
     *     #document
     * </pre>
     */
    @Test
    public void printDefaultImageFileNameTest() {
        viewElement.printDefaultImageFileName();
    }

    /**
     * JSON
     *
     * <pre>
     *   <iframe style="visibility: hidden; height: 0; width: 0; position: absolute; border: 0" id="printJS"
     *    >
     *     #document
     * </pre>
     */
    @Test
    public void printDefaultJsonTest() {
        viewElement.printDefaultJson();
    }

    /**
     * RAW_HTML
     *
     * <pre>
     *   <iframe style="visibility: hidden; height: 0; width: 0; position: absolute; border: 0" id="printJS"
     *    srcdoc="<html><head><title>Document</title></head><body></body></html>">
     *     #document
     * </pre>
     */
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
