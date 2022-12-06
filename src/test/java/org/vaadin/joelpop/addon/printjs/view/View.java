package org.vaadin.joelpop.addon.printjs.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.joelpop.addon.printjs.PrintJs;

import java.util.Optional;
import java.util.function.Predicate;

@Route("")
public class View extends Composite<VerticalLayout> {

    public static final String VIEW_ID = "view";

    public static final String PDF_FILE_NAME_TEXT_FIELD_ID = "pdf-file-name-id";
    public static final String HTML_ELEMENT_TEXT_FIELD_ID = "html-element-id";
    public static final String IMAGE_FILE_NAME_TEXT_FIELD_ID = "image-file-name-id";
    public static final String JSON_TEXT_FIELD_ID = "json-id";
    public static final String RAW_HTML_TEXT_FIELD_ID = "raw-html-id";

    public static final String PRINT_PDF_FILE_NAME_BUTTON_ID = "print-pdf-file-name-id";
    public static final String PRINT_HTML_ELEMENT_BUTTON_ID = "print-html-element-id";
    public static final String PRINT_IMAGE_FILE_NAME_BUTTON_ID = "print-image-file-name-id";
    public static final String PRINT_JSON_BUTTON_ID = "print-json-id";
    public static final String PRINT_RAW_HTML_BUTTON_ID = "print-raw-html-id";

    public static final String DEFAULT_PDF_FILE_NAME = "doc/default.pdf";
    public static final String DEFAULT_HTML_ELEMENT = VIEW_ID;
    public static final String DEFAULT_IMAGE_FILE_NAME = "doc/default.png";
    public static final String DEFAULT_JSON = "{}";
    public static final String DEFAULT_RAW_HTML = "<div>Default Raw HTML</div>";

    private final TextField pdfFileNameTextField;
    private final TextField htmlElementTextField;
    private final TextField imageFileNameTextField;
    private final TextField jsonTextField;
    private final TextField rawHtmlTextField;

    private final PrintJs printJs;

    public View() {
        setId(VIEW_ID);

        // pdf
        pdfFileNameTextField = new TextField("Name of PDF File on Server");
        pdfFileNameTextField.setId(PDF_FILE_NAME_TEXT_FIELD_ID);
        pdfFileNameTextField.setPlaceholder("Default: " + DEFAULT_PDF_FILE_NAME);

        var printPdfFileNameButton = new Button("Print PDF from file on server");
        printPdfFileNameButton.setId(PRINT_PDF_FILE_NAME_BUTTON_ID);
        printPdfFileNameButton.addClickListener(this::onPrintPdfClick);

        // html element
        htmlElementTextField = new TextField("Element ID of HTML");
        htmlElementTextField.setId(HTML_ELEMENT_TEXT_FIELD_ID);
        htmlElementTextField.setPlaceholder("Default: " + DEFAULT_HTML_ELEMENT);

        var printHtmlElementButton = new Button("Print HTML from element ID");
        printHtmlElementButton.setId(PRINT_HTML_ELEMENT_BUTTON_ID);
        printHtmlElementButton.addClickListener(this::onPrintHtmlElementClick);

        // image
        imageFileNameTextField = new TextField("Name of Image File on Server");
        imageFileNameTextField.setId(IMAGE_FILE_NAME_TEXT_FIELD_ID);
        imageFileNameTextField.setPlaceholder("Default: " + DEFAULT_IMAGE_FILE_NAME);

        var printImageFileNameButton = new Button("Print Image from file on server");
        printImageFileNameButton.setId(PRINT_IMAGE_FILE_NAME_BUTTON_ID);
        printImageFileNameButton.addClickListener(this::onPrintImageClick);

        // json
        jsonTextField = new TextField("JSON data");
        jsonTextField.setId(JSON_TEXT_FIELD_ID);
        jsonTextField.setPlaceholder("Default: " + DEFAULT_JSON);

        var printJsonButton = new Button("Print JSON data");
        printJsonButton.setId(PRINT_JSON_BUTTON_ID);
        printJsonButton.addClickListener(this::onPrintJsonClick);

        // raw html
        rawHtmlTextField = new TextField("Raw HTML");
        rawHtmlTextField.setId(RAW_HTML_TEXT_FIELD_ID);
        rawHtmlTextField.setPlaceholder("Default: " + DEFAULT_RAW_HTML);

        var printRawHtmlButton = new Button("Print Raw HTML");
        printRawHtmlButton.setId(PRINT_RAW_HTML_BUTTON_ID);
        printRawHtmlButton.addClickListener(this::onPrintRawHtmlClick);

        // content
        var content = getContent();
        content.add(new HorizontalLayout(pdfFileNameTextField, printPdfFileNameButton));
        content.add(new HorizontalLayout(htmlElementTextField, printHtmlElementButton));
        content.add(new HorizontalLayout(imageFileNameTextField, printImageFileNameButton));
        content.add(new HorizontalLayout(jsonTextField, printJsonButton));
        content.add(new HorizontalLayout(rawHtmlTextField, printRawHtmlButton));

        printJs = new PrintJs();
    }

    private void onPrintPdfClick(ClickEvent<Button> buttonClickEvent) {
        printJs.reset();
        printJs.setPrintableType(PrintJs.PrintableType.PDF);
        var value = Optional.ofNullable(pdfFileNameTextField.getValue())
                .filter(Predicate.not(String::isBlank))
                .orElse(DEFAULT_PDF_FILE_NAME);
        printJs.setPrintable(value);
        getUI().ifPresent(printJs::print);
    }

    private void onPrintHtmlElementClick(ClickEvent<Button> buttonClickEvent) {
        printJs.reset();
        printJs.setPrintableType(PrintJs.PrintableType.HTML);
        var value = Optional.ofNullable(htmlElementTextField.getValue())
                .filter(Predicate.not(String::isBlank))
                .orElse(DEFAULT_HTML_ELEMENT);
        printJs.setPrintable(value);
        getUI().ifPresent(printJs::print);
    }

    private void onPrintImageClick(ClickEvent<Button> buttonClickEvent) {
        printJs.reset();
        printJs.setPrintableType(PrintJs.PrintableType.IMAGE);
        var value = Optional.ofNullable(imageFileNameTextField.getValue())
                .filter(Predicate.not(String::isBlank))
                .orElse(DEFAULT_IMAGE_FILE_NAME);
        printJs.setPrintable(value);
        getUI().ifPresent(printJs::print);
    }

    private void onPrintJsonClick(ClickEvent<Button> buttonClickEvent) {
        printJs.reset();
        printJs.setPrintableType(PrintJs.PrintableType.JSON);
        var value = Optional.ofNullable(jsonTextField.getValue())
                .filter(Predicate.not(String::isBlank))
                .orElse(DEFAULT_JSON);
        printJs.setPrintable(value);
        getUI().ifPresent(printJs::print);
    }

    private void onPrintRawHtmlClick(ClickEvent<Button> buttonClickEvent) {
        printJs.reset();
        printJs.setPrintableType(PrintJs.PrintableType.RAW_HTML);
        var value = Optional.ofNullable(rawHtmlTextField.getValue())
                .filter(Predicate.not(String::isBlank))
                .orElse(DEFAULT_RAW_HTML);
        printJs.setPrintable(value);
        getUI().ifPresent(printJs::print);
    }

}
