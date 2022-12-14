package org.vaadin.joelpop.addon.printjs.element;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.annotations.Attribute;
import com.vaadin.testbench.elementsbase.Element;

import static org.vaadin.joelpop.addon.printjs.view.View.*;

@Element("vaadin-vertical-layout")
@Attribute(name = "id", value = VIEW_ID)
public class ViewElement extends TestBenchElement {

    public void printDefaultPdfFileName() {
        printPdfFileName(null);
    }

    public void printPdfFileName(String pdfFileName) {
        var pdfFileNameTextFieldElement = $(TextFieldElement.class).id(textFieldIdFor(PDF_URL));
        var printPdfFileNameButtonElement = $(ButtonElement.class).id(buttonIdFor(PDF_URL));

        if (pdfFileName != null) {
            pdfFileNameTextFieldElement.setValue(pdfFileName);
        }
        else {
            printPdfFileNameButtonElement.clear();
        }
        printPdfFileNameButtonElement.click();
    }

    public void printDefaultHtmlElement() {
        printHtmlElement(null);
    }

    public void printHtmlElement(String htmlElementId) {
        var htmlElementTextFieldElement = $(TextFieldElement.class).id(textFieldIdFor(HTML_ELEMENT));
        var printHtmlElementButtonElement = $(ButtonElement.class).id(buttonIdFor(HTML_ELEMENT));

        if (htmlElementId != null) {
            htmlElementTextFieldElement.setValue(htmlElementId);
        }
        else {
            htmlElementTextFieldElement.clear();
        }
        printHtmlElementButtonElement.click();
    }

    public void printDefaultImageFileName() {
        printImageFileName(null);
    }

    public void printImageFileName(String imageFileName) {
        var imageFileNameTextFieldElement = $(TextFieldElement.class).id(textFieldIdFor(IMAGE_URL));
        var printImageFileNameButtonElement = $(ButtonElement.class).id(buttonIdFor(IMAGE_URL));

        if (imageFileName != null) {
            imageFileNameTextFieldElement.setValue(imageFileName);
        }
        else {
            imageFileNameTextFieldElement.clear();
        }
        printImageFileNameButtonElement.click();
    }

    public void printDefaultJson() {
        printJson(null);
    }

    public void printJson(String jsonData) {
        var jsonTextFieldElement = $(TextFieldElement.class).id(textFieldIdFor(JSON_DATA));
        var printJsonButtonElement = $(ButtonElement.class).id(buttonIdFor(JSON_DATA));

        if (jsonData != null) {
            jsonTextFieldElement.setValue(jsonData);
        }
        else {
            jsonTextFieldElement.clear();
        }
        printJsonButtonElement.click();
    }

    public void printDefaultRawHtml() {
        printRawHtml(null);
    }

    public void printRawHtml(String rawHtml) {
        var rawHtmlTextFieldElement = $(TextFieldElement.class).id(textFieldIdFor(RAW_HTML));
        var printRawHtmlButtonElement = $(ButtonElement.class).id(buttonIdFor(RAW_HTML));

        if (rawHtml != null) {
            rawHtmlTextFieldElement.setValue(rawHtml);
        }
        else {
            rawHtmlTextFieldElement.clear();
        }
        printRawHtmlButtonElement.click();
    }
}
