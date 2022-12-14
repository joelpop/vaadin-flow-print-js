package org.vaadin.joelpop.addon.printjs.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
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
    public static final String PDF_URL = "PDF";
    public static final String HTML_ELEMENT = "HTML_ELEMENT";
    public static final String IMAGE_URL = "IMAGE";
    public static final String JSON_DATA = "JSON";
    public static final String RAW_HTML = "RAW_HTML";

    public static final String DEFAULT_PDF_URL = "doc/default.pdf";
    public static final String DEFAULT_IMAGE_URL = "doc/default.png";
    public static final String DEFAULT_HTML_ELEMENT = VIEW_ID;
    public static final String DEFAULT_RAW_HTML = "<div>Default Raw HTML: <em>Hello, World!</em></div>";
    public static final String DEFAULT_JSON = "[" +
            "{name:'John Doe', email:'john@doe.com', phone:'111-111-1111'}, " +
            "{name:'Barry Allen', email:'barry@flash.com', phone:'222-222-2222'}, " +
            "{name:'Cool Dude', email:'cool@dude.com', phone:'333-333-3333'} ]";

    public View() {
        setId(VIEW_ID);

        // pdf file
        var pdfPrintOption = createPrintOptionComponent(PDF_URL,
                "Name of PDF File on Server", DEFAULT_PDF_URL);

        // html element
        var htmlPrintOption = createPrintOptionComponent(HTML_ELEMENT,
                "Element ID of HTML", DEFAULT_HTML_ELEMENT);

        // image
        var imagePrintOption = createPrintOptionComponent(IMAGE_URL,
                "Name of Image File on Server", DEFAULT_IMAGE_URL);

        // json
        var jsonPrintOption = createPrintOptionComponent(JSON_DATA,
                "JSON data", DEFAULT_JSON);

        // raw html
        var rawHtmlPrintOption = createPrintOptionComponent(RAW_HTML,
                "Raw HTML", DEFAULT_RAW_HTML);

        // content
        var content = getContent();
        content.add(pdfPrintOption);
        content.add(htmlPrintOption);
        content.add(imagePrintOption);
        content.add(jsonPrintOption);
        content.add(rawHtmlPrintOption);
    }

    private Component createPrintOptionComponent(String printableTypeName,
                                                 String label, String defaultPrintable) {
        var printableTextField = new TextField(label);
        printableTextField.setId(textFieldIdFor(printableTypeName));
        printableTextField.setPlaceholder(defaultPrintable);
        printableTextField.setWidth(25, Unit.EM);

        var printButton = new Button("Print");
        printButton.setId(buttonIdFor(printableTypeName));
        printButton.addClickListener(e -> onPrintClick(printableTypeName,
                printableTextField.getValue(), defaultPrintable));

        var layout = new HorizontalLayout();
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        layout.add(printableTextField);
        layout.add(printButton);

        return layout;
    }

    private void onPrintClick(String printableTypeName,
                              String printable, String defaultPrintable) {
        var value = Optional.ofNullable(printable)
                .filter(Predicate.not(String::isBlank))
                .orElse(defaultPrintable);
//        var printJs = switch (printableTypeName) {
//            case "PDF" -> PrintJs.fromPdfUrl(value);
//            case "IMAGE" -> PrintJs.fromImageUrl(value);
//            case "HTML_ELEMENT" -> PrintJs.fromHtmlElement(value);
//            case "RAW_HTML" -> PrintJs.fromRawHtml(value);
//            case "JSON" -> PrintJs.fromJsonData(value);
//        };
        PrintJs printJs;
        switch (printableTypeName) {
            case PDF_URL:
                printJs = PrintJs.fromPdfUrl(value);
                break;
            case IMAGE_URL:
                printJs = PrintJs.fromImageUrl(value);
                break;
            case HTML_ELEMENT:
                printJs = PrintJs.fromHtmlElement(value);
                break;
            case RAW_HTML:
                printJs = PrintJs.fromRawHtml(value);
                break;
            case JSON_DATA:
                printJs = PrintJs.fromJsonData(value);
                break;
            default:
                throw new IllegalArgumentException();
        }

        getUI().ifPresent(printJs::print);
    }

    public static String textFieldIdFor(String printableTypeName) {
        var idPrefix = printableTypeName.toLowerCase().replace('_', '-');
        return idPrefix + "-text-field";
    }

    public static String buttonIdFor(String printableTypeName) {
        var idPrefix = printableTypeName.toLowerCase().replace('_', '-');
        return idPrefix + "-button";
    }

}
