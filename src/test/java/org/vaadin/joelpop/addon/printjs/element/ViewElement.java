package org.vaadin.joelpop.addon.printjs.element;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.annotations.Attribute;
import com.vaadin.testbench.elementsbase.Element;
import org.vaadin.joelpop.addon.printjs.view.View;

@Element("vaadin-vertical-layout")
@Attribute(name = "id", value = View.VIEW_ID)
public class ViewElement extends TestBenchElement {

    public void printDefaultPdfFileName() {
        printPdfFileName(null);
    }

    public void printPdfFileName(String pdfFileName) {
        var pdfFileNameTextFieldElement = $(TextFieldElement.class).id(View.PDF_FILE_NAME_TEXT_FIELD_ID);
        var printPdfFileNameButtonElement = $(ButtonElement.class).id(View.PRINT_PDF_FILE_NAME_BUTTON_ID);

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
        var htmlElementTextFieldElement = $(TextFieldElement.class).id(View.HTML_ELEMENT_TEXT_FIELD_ID);
        var printHtmlElementButtonElement = $(ButtonElement.class).id(View.PRINT_HTML_ELEMENT_BUTTON_ID);

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
        var imageFileNameTextFieldElement = $(TextFieldElement.class).id(View.IMAGE_FILE_NAME_TEXT_FIELD_ID);
        var printImageFileNameButtonElement = $(ButtonElement.class).id(View.PRINT_IMAGE_FILE_NAME_BUTTON_ID);

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
        var jsonTextFieldElement = $(TextFieldElement.class).id(View.JSON_TEXT_FIELD_ID);
        var printJsonButtonElement = $(ButtonElement.class).id(View.PRINT_JSON_BUTTON_ID);

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
        var rawHtmlTextFieldElement = $(TextFieldElement.class).id(View.RAW_HTML_TEXT_FIELD_ID);
        var printRawHtmlButtonElement = $(ButtonElement.class).id(View.PRINT_RAW_HTML_BUTTON_ID);

        if (rawHtml != null) {
            rawHtmlTextFieldElement.setValue(rawHtml);
        }
        else {
            rawHtmlTextFieldElement.clear();
        }
        printRawHtmlButtonElement.click();
    }
}
