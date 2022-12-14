package org.vaadin.joelpop.addon.printjs;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.vaadin.joelpop.addon.printjs.PrintJs.Parameter.*;

/**
 * A Java wrapper for Print.js to set its parameters and print.
 * <p>
 * References:
 * <ul>
 *   <li><a href="https://printjs.crabbly.com">Print.js</a>
 *   A tiny javascript library to help printing from the web.
 *   <li><a href="https://github.com/crabbly/print.js">GitHub repository</a>
 * </ul>
 */
@NpmPackage(value = "print-js", version = "1.6.0")
@JsModule("./imports/import-print-js.js")  // requires frontend/imports/import-print-js.js file to exist with this content: import printJS from 'print-js'
public class PrintJs {
    private final Map<Parameter, String> parameters;

    // constructors

    /**
     * Create a parameterless PrintJs object.
     */
    private PrintJs() {
        parameters = new EnumMap<>(Parameter.class);
    }

    // factory methods

    /**
     * Create a PDF PrintJs object and specify the URL of the PDF file to be printed.
     *
     * @param pdfUrl the URL of the PDF file to be printed.
     */
    public static PrintJs fromPdfUrl(String pdfUrl) {
        var printJs = new PrintJs();
        printJs.setPrintableType(PrintableType.PDF);
        printJs.setPrintable(pdfUrl);
        return printJs;
    }

    /**
     * Create a PDF PrintJs object and specify the base 64 object containing the PDF to be printed.
     *
     * @param base64Pdf the base 64 object containing the PDF to be printed.
     */
    public static PrintJs fromBase64Pdf(String base64Pdf) {
        var printJs = new PrintJs();
        printJs.setPrintableType(PrintableType.PDF);
        printJs.setBase64(true);
        printJs.setPrintableObject(base64Pdf);
        return printJs;
    }

    /**
     * Create an image PrintJs object and specify the URLs of the image files to be printed.
     *
     * @param imageUrls the URLs of the image files to be printed.
     */
    public static PrintJs fromImageUrl(String... imageUrls) {
        var printJs = new PrintJs();
        printJs.setPrintableType(PrintableType.IMAGE);
        printJs.setPrintables(imageUrls);
        return printJs;
    }

    /**
     * Create an HTML PrintJs object and specify the ID of the HTML element to be printed.
     *
     * @param htmlElement the ID of the HTML element to be printed.
     */
    public static PrintJs fromHtmlElement(String htmlElement) {
        var printJs = new PrintJs();
        printJs.setPrintableType(PrintableType.HTML_ELEMENT);
        printJs.setPrintable(htmlElement);
        return printJs;
    }

    /**
     * Create an HTML PrintJs object and specify the literal HTML to be printed.
     *
     * @param html the literal HTML to be printed.
     */
    public static PrintJs fromRawHtml(String html) {
        var printJs = new PrintJs();
        printJs.setPrintableType(PrintableType.RAW_HTML);
        printJs.setPrintable(html);
        return printJs;
    }

    /**
     * Create a JSON PrintJs object and specify the JSON data to be printed.
     *
     * @param json the JSON data to be printed.
     */
    public static PrintJs fromJsonData(String json) {
        var printJs = new PrintJs();
        printJs.setPrintableType(PrintableType.JSON);
        printJs.setPrintableObject(json);
        return printJs;
    }

    // accessors

    /**
     * Get the document source.
     *
     * @return the document source
     */
    public String getPrintable() {
        return getStringParameter(PRINTABLE);
    }

    /**
     * Set the document source.
     *
     * @param printable the document source.
     *                  Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    private PrintJs setPrintable(String printable) {
        setStringParameter(PRINTABLE, printable);
        return this;
    }

    /**
     * Get the document source.
     *
     * @return the document source
     */
    public String[] getPrintables() {
        var printableType = getPrintableType();
        if (printableType != PrintableType.IMAGE) {
            throw newIllegalArgumentException(PRINTABLE, printableType);
        }
        return getStringArrayParameter(PRINTABLE);
    }

    /**
     * Set the document sources. (Valid only for image printable type.)
     *
     * @param printables the document sources.
     *                   Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    private PrintJs setPrintables(String... printables) {
        var printableType = getPrintableType();
        if (printableType != PrintableType.IMAGE) {
            throw newIllegalArgumentException(PRINTABLE, printableType);
        }
        setStringArrayParameter(PRINTABLE, printables);
        return this;
    }

    /**
     * Get the document source object.
     *
     * @return the document source object
     */
    public String getPrintableObject() {
        return getLiteralParameter(PRINTABLE);
    }

    /**
     * Set the document source object.
     * <p>
     * If no printable type is set, the document source is assumed to be {@code PDF}.
     *
     * <p>
     * Valid printable types are:
     * <ul>
     *   <li>{@code PDF} - the URL of a PDF file on the server or the base64 encoding of a PDF</li>
     *   <li>{@code IMAGE} - the URL of an image file on the server</li>
     *   <li>{@code HTML_ELEMENT} - the ID of an HTML element</li>
     *   <li>{@code RAW_HTML} - raw HTML</li>
     *   <li>{@code JSON_DATA} - a JSON data array</li>
     * </ul>
     *
     * @param printableObject the document source object.
     *                        Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    private PrintJs setPrintableObject(String printableObject) {
        setLiteralParameter(PRINTABLE, printableObject);
        return this;
    }

    /**
     * Get the printable type.
     *
     * @return the printable type
     */
    public PrintableType getPrintableType() {
        return getPrintableTypeParameter();
    }

    /**
     * Set the printable type.
     *
     * @param printableType the printable type.
     *                      Valid printable types are:
     *                      <ul>
     *                        <li>{@code PDF} - the URL of a PDF file on the server or the base64 encoding of a PDF</li>
     *                        <li>{@code IMAGE} - the URL of an image file on the server</li>
     *                        <li>{@code HTML_ELEMENT} - the ID of an HTML element</li>
     *                        <li>{@code RAW_HTML} - raw HTML</li>
     *                        <li>{@code JSON_DATA} - a JSON data array</li>
     *                      </ul>
     *                      Default value: {@code PDF}
     * @return the PrintJs object for method chaining
     */
    private PrintJs setPrintableType(PrintableType printableType) {
        setPrintableTypeParameter(printableType);
        return this;
    }

    /**
     * Get the optional header text to be used when printing images, HTML, or JSON.
     *
     * @return the optional header text to be used when printing images, HTML, or JSON
     */
    public String getHeaderText() {
        return getStringParameter(HEADER);
    }

    /**
     * Set optional header text to be used when printing images, HTML, or JSON.
     *
     * <p>
     * It will be placed on the top of the page. This property will accept text or raw
     * HTML.
     *
     * @param headerText header text.
     *                   Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setHeaderText(String headerText) {
        setStringParameter(HEADER, headerText);
        return this;
    }

    /**
     * Get the optional header style to be applied to the header text.
     *
     * @return the optional header style to be applied to the header text
     */
    public String getHeaderStyle() {
        return getStringParameter(HEADER_STYLE);
    }

    /**
     * Set optional header style to be applied to the header text.
     *
     * @param headerStyle header style.
     *                    Default value: {@code 'font-weight: 300;'}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setHeaderStyle(String headerStyle) {
        setStringParameter(HEADER_STYLE, headerStyle);
        return this;
    }

    /**
     * Get the maximum document width in pixels.
     *
     * @return the maximum document width in pixels
     */
    public Integer getMaxWidth() {
        return getIntegerParameter(MAX_WIDTH);
    }

    /**
     * Set the maximum document width in pixels.
     *
     * <p>
     * Change this as you need. Used when printing images, HTML, or JSON.
     *
     * @param maxDocumentWidth the maximum document width in pixels.
     *                         Default value: {@code 800}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setMaxWidth(Integer maxDocumentWidth) {
        setIntegerParameter(MAX_WIDTH, maxDocumentWidth);
        return this;
    }

    /**
     * Get CSS URLs to be applied to the HTML being printed.
     *
     * @return the CSS URLs
     */
    public String[] getCssUrls() {
        return getStringArrayParameter(CSS);
    }

    /**
     * Set CSS URLs to be applied to the HTML being printed.
     *
     * @param cssUrls CSS URLs.
     *                Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setCssUrls(String... cssUrls) {
        setStringArrayParameter(CSS, cssUrls);
        return this;
    }

    /**
     * Get the custom style string to be applied to the HTML being printed.
     *
     * @return a custom style string to be applied to the HTML being printed
     */
    public String getStyle() {
        return getStringParameter(STYLE);
    }

    /**
     * Set a custom style string to be applied to the HTML being printed.
     *
     * @param style custom style string.
     *              Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setStyle(String style) {
        setStringParameter(STYLE, style);
        return this;
    }

    /**
     * Get if the library should process styles applied to the HTML being printed.
     *
     * @return
     *  {@code true} if the library should process styles applied to the HTML being printed,
     *  {@code false} if the library should not process styles applied to the HTML being printed.
     */
    public boolean isScanStyles() {
        return isBooleanParameter(SCAN_STYLES);
    }

    /**
     * Set if the library should process styles applied to the HTML being printed.
     *
     * <p>
     * Useful when using the CSS parameter.
     *
     * @param scanStyles {@code true} if the library should process styles applied to the HTML being printed,
     *                   {@code false} if the library should not process styles applied to the HTML being printed.
     *                   Default value: {@code true}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setScanStyles(boolean scanStyles) {
        setBooleanParameter(SCAN_STYLES, scanStyles);
        return this;
    }

    /**
     * Reset if the library should process styles applied to the HTML being printed.
     *
     * <p>
     * Useful when using the CSS parameter.
     * <p>
     * Default value: {@code true}
     *
     * @return the PrintJs object for method chaining
     */
    public PrintJs resetScanStyles() {
        clearParameter(SCAN_STYLES);
        return this;
    }

    /**
     * Get the array of styles to be processed when printing HTML elements.
     *
     * @return the array of styles to be processed when printing HTML elements.
     */
    public String[] getTargetStyles() {
        return getStringArrayParameter(TARGET_STYLE);
    }

    /**
     * Set an array of styles to be processed when printing HTML elements.
     *
     * <p>
     * By default, the library process some styles only.
     * This option allows you to pass an array of styles that you
     * want to be processed. Ex.: {@code ['padding-top', 'border-bottom']}
     *
     * @param targetStyles an array of styles to be processed when printing HTML elements.
     *                    Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setTargetStyles(String... targetStyles) {
        setStringArrayParameter(TARGET_STYLE, targetStyles);
        return this;
    }

    /**
     *
     * Get the array of style patterns to be processed when printing HTML elements.
     *
     * @return the array of style patterns to be processed when printing HTML elements.
     */
    public String[] getTargetStylePatterns() {
        return getStringArrayParameter(TARGET_STYLES);
    }

    /**
     * Set an array of style patterns to be processed when printing HTML elements.
     *
     * <p>
     * Same as {@see setTargetStyles}, however, this will process any a range of styles.
     * <p>
     * Ex.: {@code ['border', 'padding']}, will include
     * {@code 'border-bottom'}, {@code 'border-top'}, {@code 'border-left'}, {@code 'border-right'},
     * {@code 'padding-top'}, etc.
     * <p>
     * You can also pass {@code ['*']} to process all styles.
     *
     * @param targetStyles an array of style patterns to be processed when printing HTML elements.
     *                     Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setTargetStylePatterns(String... targetStyles) {
        setStringArrayParameter(TARGET_STYLES, targetStyles);
        return this;
    }

    /**
     * Get the HTML IDs that should be ignored when printing a parent HTML element.
     *
     * @return the HTML IDs that should be ignored when printing a parent HTML element.
     */
    public String[] getIgnoreElements() {
        return getStringArrayParameter(IGNORE_ELEMENTS);
    }

    /**
     * Set the HTML IDs that should be ignored when printing a parent HTML element.
     *
     * @param ignoreElements an array of HTML IDs that should be ignored when printing a parent HTML element.
     *                       Default value: {@code [ ]}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setIgnoreElements(String... ignoreElements) {
        setStringArrayParameter(IGNORE_ELEMENTS, ignoreElements);
        return this;
    }

    /**
     * Set the object property names to use when printing JSON.
     *
     * @return the object property names.
     */
    public String[] getJsonProperties() {
        return getStringArrayParameter(JSON_PROPERTIES);
    }

    /**
     * Set the object property names to use when printing JSON.
     *
     * @param jsonProperties the object property names.
     *                       Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setJsonProperties(String... jsonProperties) {
        setStringArrayParameter(JSON_PROPERTIES, jsonProperties);
        return this;
    }

    /**
     * Get the style for the grid header when printing JSON data.
     *
     * @return the style for the grid header.
     */
    public String getJsonGridHeaderStyle() {
        return getStringParameter(JSON_GRID_HEADER_STYLE);
    }

    /**
     * Set the style for the grid header when printing JSON data.
     *
     * @param jsonGridHeaderStyle the style for the grid header.
     *                            Default value: {@code 'font-weight: bold;'}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setJsonGridHeaderStyle(String jsonGridHeaderStyle) {
        setStringParameter(JSON_GRID_HEADER_STYLE, jsonGridHeaderStyle);
        return this;
    }

    /**
     * Get the style for grid rows when printing JSON data.
     *
     * @return the style for grid rows.
     */
    public String getJsonGridStyle() {
        return getStringParameter(JSON_GRID_STYLE);
    }

    /**
     * Set the style for grid rows when printing JSON data.
     *
     * @param jsonGridStyle the style for grid rows when printing JSON data.
     *                      Default value: {@code 'border: 1px solid lightgray; margin-bottom: -1px;'}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setJsonGridStyle(String jsonGridStyle) {
        setStringParameter(JSON_GRID_STYLE, jsonGridStyle);
        return this;
    }

    /**
     * Get if the JSON data table header should show on all pages or the first page only.
     *
     * @return
     *  {@code true} if the JSON data table header should show on all pages,
     *  {@code false} if the JSON data table header should show on the first page only.
     */
    public boolean isJsonRepeatTableHeader() {
        return isBooleanParameter(JSON_REPEAT_TABLE_HEADER);
    }

    /**
     * Set if the JSON data table header should show on all pages or the first page only.
     *
     * @param jsonRepeatTableHeader {@code true} if the JSON data table header should show on all pages,
     *                              {@code false} if the JSON data table header should show on the first page only.
     *                              Default value: {@code true}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setJsonRepeatTableHeader(boolean jsonRepeatTableHeader) {
        setBooleanParameter(JSON_REPEAT_TABLE_HEADER, jsonRepeatTableHeader);
        return this;
    }

    /**
     * Reset if the JSON data table header should show on all pages or the first page only.
     * <p>
     * Default value: {@code true}
     *
     * @return the PrintJs object for method chaining
     */
    public PrintJs resetJsonRepeatTableHeader() {
        clearParameter(JSON_REPEAT_TABLE_HEADER);
        return this;
    }

    /**
     * Get if the feedback dialog will be shown to user when retrieving or processing large PDF files.
     *
     * @return
     *  {@code true} if feedback dialog should be shown,
     *  {@code false} if no feedback dialog should be shown.
     */
    public boolean isShowModal() {
        return Boolean.TRUE.equals(isBooleanParameter(SHOW_MODAL));
    }

    /**
     * Set if a feedback dialog should be shown to user when retrieving or processing large PDF files.
     *
     * @param showModal {@code true} if feedback dialog should be shown,
     *                  {@code false} if no feedback dialog should be shown.
     *                  Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setShowModal(boolean showModal) {
        setBooleanParameter(SHOW_MODAL, showModal);
        return this;
    }

    /**
     * Reset if a feedback dialog should be shown to user when retrieving or processing large PDF files.
     * <p>
     * Default value: {@code null}
     *
     * @return the PrintJs object for method chaining
     */
    public PrintJs resetShowModal() {
        clearParameter(SHOW_MODAL);
        return this;
    }

    /**
     * Set the message displayed to users when showing feedback dialog.
     *
     * @return the message displayed to users.
     */
    public String getModalMessage() {
        return getStringParameter(MODAL_MESSAGE);
    }

    /**
     * Set the message displayed to users when showing feedback dialog.
     *
     * @param modalMessage the message displayed to users.
     *                     Default value: {@code 'Retrieving Document...'}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setModalMessage(String modalMessage) {
        setStringParameter(MODAL_MESSAGE, modalMessage);
        return this;
    }

    /**
     * Set the document title shown when printing images, HTML, or JSON.
     *
     * @return the document title.
     */
    public String getDocumentTitle() {
        return getStringParameter(DOCUMENT_TITLE);
    }

    /**
     * Set the document title shown when printing images, HTML, or JSON.
     *
     * @param documentTitle the document title.
     *                      Default value: {@code 'Document'}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setDocumentTitle(String documentTitle) {
        setStringParameter(DOCUMENT_TITLE, documentTitle);
        return this;
    }

    /**
     * Get the alternate PDF to view if the browser is not compatible for printing PDFs.
     *
     * @return fallbackPrintable the alternate PDF URL.
     */
    public String getFallbackPrintable() {
        return getStringParameter(FALLBACK_PRINTABLE);
    }

    /**
     * Set an alternate PDF to view if the browser is not compatible for printing PDFs.
     *
     * <p>
     * When printing a PDF, if the browser is not compatible (check browser
     * compatibility table), the library will open the PDF in a new tab. This
     * allows you to pass a different PDF document to be opened instead of the
     * original passed in {@see setPrintable}. This may be useful if you inject
     * Javascript in your alternate PDF file.
     *
     * @param fallbackPrintable an alternate PDF URL.
     *                          Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setFallbackPrintable(String fallbackPrintable) {
        setStringParameter(FALLBACK_PRINTABLE, fallbackPrintable);
        return this;
    }

    /**
     * Get if PDF document is encoded as base64 data.
     *
     * @return
     *  {@code true} if PDF document is encoded as base64 data,
     *  {@code false} if PDF document is not encoded.
     */
    public boolean isBase64() {
        return isBooleanParameter(BASE64);
    }

    /**
     * Set if PDF document is encoded as base64 data.
     *
     * @param base64 {@code true} if PDF document is encoded as base64 data,
     *               {@code false} if PDF document is not encoded.
     *               Default value: {@code false}
     * @return the PrintJs object for method chaining
     */
    private PrintJs setBase64(boolean base64) {
        setBooleanParameter(BASE64, base64);
        return this;
    }

    /**
     * Get the Javascript function to be executed before PDF is loaded.
     *
     * @return the Javascript function to be executed.
     */
    public String getOnLoadingStart() {
        return getLiteralParameter(ON_LOADING_START);
    }

    /**
     * Set a Javascript function to be executed before PDF is loaded.
     *
     * @param onLoadingStart a Javascript function to be executed.
     *                       Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setOnLoadingStart(String onLoadingStart) {
        setLiteralParameter(ON_LOADING_START, onLoadingStart);
        return this;
    }

    /**
     * Get the Javascript function to be executed after PDF has loaded.
     *
     * @return the Javascript function to be executed.
     */
    public String getOnLoadingEnd() {
        return getLiteralParameter(ON_LOADING_END);
    }

    /**
     * Set a Javascript function to be executed after PDF has loaded.
     *
     * @param onLoadingEnd a Javascript function to be executed.
     *                     Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setOnLoadingEnd(String onLoadingEnd) {
        setLiteralParameter(ON_LOADING_END, onLoadingEnd);
        return this;
    }

    /**
     * Get the Javascript function to call if the browser is not compatible for printing PDFs.
     *
     * @return the Javascript function to call.
     */
    public String getOnPdfOpen() {
        return getLiteralParameter(ON_PDF_OPEN);
    }

    /**
     * Set a Javascript function to call if the browser is not compatible for printing PDFs.
     *
     * <p>
     * When printing PDF, if the browser is not compatible (check browser
     * compatibility table), the library will open the PDF in a new tab. A
     * callback function can be passed here, which will be executed when this
     * happens. It may be useful in some situations where you want to handle
     * your print flow, update user interface, etc.
     *
     * @param onPdfOpen a Javascript function to call.
     *                  Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setOnPdfOpen(String onPdfOpen) {
        setLiteralParameter(ON_PDF_OPEN, onPdfOpen);
        return this;
    }

    /**
     * Set the Javascript function to call after the browser print dialog is closed.
     *
     * @return the Javascript function to call.
     */
    public String getOnPrintDialogClose() {
        return getLiteralParameter(ON_PRINT_DIALOG_CLOSE);
    }

    /**
     * Set a Javascript function to call after the browser print dialog is closed.
     *
     * @param onPrintDialogClose a Javascript function to call.
     *                           Default value: {@code null}
     * @return the PrintJs object for method chaining
     */
    public PrintJs setOnPrintDialogClose(String onPrintDialogClose) {
        setLiteralParameter(ON_PRINT_DIALOG_CLOSE, onPrintDialogClose);
        return this;
    }

    // actions

    /**
     * Print the document source using the previously-set parameters.
     *
     * @param ui the UI from which to print the document source.
     */
    public void print(UI ui) {
//        ui.getPage().executeJs("printJS(%s)".formatted(getParameterString()));
        ui.getPage().executeJs(String.format("printJS(%s)", getParameterString()));
    }

    private void clearParameter(Parameter parameter) {
        // forbid (ignore) clearing parameters that are only settable during initialization, namely:
        // printable type, printable, and base64 - treat it as a no-op
        if (parameter == TYPE) {
            return;
        }
        parameters.remove(parameter);
    }

    /**
     * Resets the configuration back to its defaults.
     */
    public void reset() {
        // retain parameters that are only settable during initialization, namely:
        // printable type, printable, and base64
        parameters.entrySet()
                .removeIf(parameterEntry ->
                        !Set.of(TYPE, PRINTABLE, BASE64).contains(parameterEntry.getKey()));
    }


    // utility

    /**
     * Get value of a parameter without any added quoting of any kind.
     * Use for getting a parameter that is Javascript code rather than a quoted string.
     *
     * @param parameter the parameter to get
     * @return the literal value, {@code null} if not set
     */
    private String getLiteralParameter(Parameter parameter) {
        return getRawParameter(parameter, Function.identity());
    }

    /**
     * Set value of a parameter without adding any quoting of any kind.
     * Use for setting a parameter that is Javascript code rather than a quoted string.
     *
     * @param parameter the parameter to set
     * @param value a literal value
     */
    private void setLiteralParameter(Parameter parameter, String value) {
        setRawParameter(parameter, value, Function.identity());
    }

    /**
     * Get value of a {@code boolean} parameter.
     *
     * @param parameter the parameter to get
     * @return the {@code boolean} value, {@code false} if not set
     */
    private boolean isBooleanParameter(Parameter parameter) {
        return Optional.ofNullable(getRawParameter(parameter, Boolean::parseBoolean))
                .orElse(Boolean.FALSE);
    }

    /**
     * Set value of a {@code boolean} parameter.
     *
     * @param parameter the parameter to set
     * @param value a {@code boolean} value
     */
    private void setBooleanParameter(Parameter parameter, boolean value) {
        setRawParameter(parameter, value, v -> Boolean.TRUE.equals(v) ? "true" : "false");
    }

    /**
     * Get value of the {@code PrintableType} parameter.
     *
     * @return the {@code PrintableType} value, {@code null} if not set
     */
    private PrintableType getPrintableTypeParameter() {
        return PrintableType.fromKey(getStringParameter(TYPE));
    }

    /**
     * Set value of the {@code PrintableType} parameter.
     *
     * @param printableType a {@code PrintableType} value
     */
    private void setPrintableTypeParameter(PrintableType printableType) {
        setStringParameter(TYPE, printableType.key);
    }

    /**
     * Get value of an {@code Integer} parameter.
     *
     * @param parameter the parameter to get
     * @return the {@code Integer} value, {@code null} if not set
     */
    private Integer getIntegerParameter(Parameter parameter) {
        return getRawParameter(parameter, Integer::parseInt);
    }

    /**
     * Set value of the {@code Integer} parameter.
     *
     * @param parameter the parameter to set
     * @param value an {@code Integer} value
     */
    private void setIntegerParameter(Parameter parameter, Integer value) {
        setRawParameter(parameter, value, Object::toString);
    }

    /**
     * Get value of an {@code String} parameter.
     *
     * @param parameter the parameter to get
     * @return the {@code String} value, {@code null} if not set
     */
    private String getStringParameter(Parameter parameter) {
        return getRawParameter(parameter, PrintJs::unEscapeString);
    }

    /**
     * Set value of the {@code String} parameter.
     *
     * @param parameter the parameter to set
     * @param value a {@code String} value
     */
    private void setStringParameter(Parameter parameter, String value) {
        setRawParameter(parameter, value, PrintJs::escapeString);
    }

    /**
     * Get value of an {@code String} array parameter.
     *
     * @param parameter the parameter to get
     * @return the {@code String} array value, empty array if none set
     */
    private String[] getStringArrayParameter(Parameter parameter) {
        return getRawParameter(parameter, r -> Optional.of(r)
                .filter(a -> !a.matches("\\[ *]"))
                .map(a -> a.substring(2, a.length() - 2))
                .map(a -> a.split("','"))
                .stream()
                .flatMap(Arrays::stream)
//                .map("'%s'"::formatted)
                .map(s -> String.format("'%s'", s))
                .map(PrintJs::unEscapeString)
                .toArray(String[]::new));
    }

    /**
     * Set value of the {@code String} array parameter.
     *
     * @param parameter the parameter to set
     * @param values {@code String} values
     */
    private void setStringArrayParameter(Parameter parameter, String... values) {
        if (values.length == 0) {
            clearParameter(parameter);
        }
        else {
            setRawParameter(parameter, values,
//                    a -> "[%s]".formatted(Arrays.stream(a)
                    a -> String.format("[%s]", Arrays.stream(a)
                            .map(PrintJs::escapeString)
                            .collect(Collectors.joining(","))));
        }
    }

    /**
     * Generic getter used by all above utility methods to get parameter values.
     * If .
     *
     * @param <T> the type of the parameter value.
     * @param parameter the parameter to set
     * @param fromRawConverter the function to use to convert the raw value to the generic type {@code T}.
     * @return the parameter value. If it is not set, return its default value, if the parameter has one.
     * {@code null} otherwise.
     */
    private <T> T getRawParameter(Parameter parameter, Function<String, T> fromRawConverter) {
        if (parameter != TYPE) {
            var printableType = getPrintableType();
            if (!parameter.isSupportedBy(printableType)) {
                throw newIllegalArgumentException(parameter, printableType);
            }
        }

        var value = Optional.ofNullable(parameters.get(parameter))
                .orElse(parameter.defaultValue);
        return Optional.ofNullable(value)
                .map(fromRawConverter)
                .orElse(null);
    }

    /**
     * Generic setter used by all above utility methods to set parameter values.
     */
    private <T> void setRawParameter(Parameter parameter, T value, Function<T, String> toRawConverter) {
        if (parameter != TYPE) {
            var printableType = getPrintableType();
            if (!parameter.isSupportedBy(printableType)) {
                throw newIllegalArgumentException(parameter, printableType);
            }
        }

        if (value != null) {
            parameters.put(parameter, toRawConverter.apply(value));
        }
        else {
            parameters.remove(parameter);
        }
    }

    private static String escapeString(String value) {
        return Optional.ofNullable(value)
                .map(v -> v.replace("\\", "\\\\"))
//                .map("'%s'"::formatted)
                .map(s -> String.format("'%s'", s))
                .orElse(null);
    }

    private static String unEscapeString(String value) {
        return Optional.ofNullable(value)
                .map(v -> v.substring(1, value.length() - 1))
                .map(v -> v.replace("\\\\", "\\"))
                .orElse(null);
    }

    /**
     * Returns the parameters as a Javascript string.
     * @return the parameters as a Javascript string.
     */
    private String getParameterString() {
//        return "{%s}".formatted(parameters.entrySet().stream()
//                .map(parameterEntry -> "%s:%s".formatted(parameterEntry.getKey().key, parameterEntry.getValue()))
//                .collect(Collectors.joining(",")));
        return String.format("{%s}", parameters.entrySet().stream()
                .map(parameterEntry -> parameterEntry.getKey().key + ":" + parameterEntry.getValue())
                .collect(Collectors.joining(",")));
    }

    /**
     * Returns the parameters as a Javascript string.
     * @return the parameters as a Javascript string.
     */
    @Override
    public String toString() {
        return getParameterString();
    }

    private static IllegalArgumentException newIllegalArgumentException(Parameter parameter, PrintableType printableType) {
//        return new IllegalArgumentException("Parameter '%s' is not valid for printable type %s."
//                .formatted(parameter.name(), printableType.name()));
        return new IllegalArgumentException(String.format("Parameter '%s' is not valid for printable type %s.",
                parameter.name(), printableType.name()));
    }


    // internal enums

    /**
     * The type of document source to print.
     */
    enum PrintableType {
        /**
         * The URL of a PDF file on the server or the base64 encoding of a PDF.
         */
        PDF("pdf"),
        /**
         * The URL of an image file on the server.
         */
        IMAGE("image"),
        /**
         * The ID of an HTML element.
         */
        HTML_ELEMENT("html"),
        /**
         * Raw HTML.
         */
        RAW_HTML("raw-html"),
        /**
         * A JSON data object.
         */
        JSON("json");

        public final String key;

        PrintableType(String key) {
            this.key = key;
        }

        public static PrintableType fromKey(String key) {
            return Arrays.stream(values())
                    .filter(printableType -> Objects.equals(printableType.key, key))
                    .findFirst()
                    .orElse(null);
        }
    }

    enum Parameter {
        /**
         * Document source: PDF URL, image URL, HTML element id, raw HTML, or JSON data.
         *
         * <p>
         * Default value: {@code null}
         */
        PRINTABLE("printable",
                PrintableType.values()),

        /**
         * Printable type. Available printable types are:
         * <ul>
         *   <li>{@code PDF} - the URL of a PDF file on the server or the base64 encoding of a PDF</li>
         *   <li>{@code IMAGE} - the URL of an image file on the server</li>
         *   <li>{@code HTML_ELEMENT} - the ID of an HTML element</li>
         *   <li>{@code RAW_HTML} - raw HTML</li>
         *   <li>{@code JSON_DATA} - a JSON data array</li>
         * </ul>
         *
         *
         * <p>
         * Default value: {@code 'pdf'}
         */
        TYPE("type", "'pdf'",
                PrintableType.values()),

        /**
         * Optional header to be used when printing images, HTML, or JSON. It will
         * be placed on the top of the page. This property will accept text or raw
         * HTML.
         *
         * <p>
         * Default value: {@code null}
         */
        HEADER("header",
                PrintableType.IMAGE, PrintableType.HTML_ELEMENT, PrintableType.RAW_HTML, PrintableType.JSON),

        /**
         * Optional header style to be applied to the header text.
         *
         * <p>
         * Default value: {@code 'font-weight: 300;'}
         */
        HEADER_STYLE("headerStyle", "'font-weight: 300;'",
                PrintableType.IMAGE, PrintableType.HTML_ELEMENT, PrintableType.RAW_HTML, PrintableType.JSON),

        /**
         * Max document width in pixels. Change this as you need.
         * Used when printing images, HTML, or JSON.
         *
         * <p>
         * Default value: {@code 800}
         */
        MAX_WIDTH("maxWidth", "800",
                PrintableType.IMAGE, PrintableType.HTML_ELEMENT, PrintableType.RAW_HTML, PrintableType.JSON),

        /**
         * This allows us to pass one or more CSS file URLs that should be applied
         * to the HTML being printed. Value can be a string with a single URL or
         * an array with multiple URLs.
         *
         * <p>
         * Default value: {@code null}
         */
        CSS("css",
                PrintableType.HTML_ELEMENT, PrintableType.RAW_HTML),

        /**
         * This allows us to pass a string with custom style that should be applied
         * to the HTML being printed.
         *
         * <p>
         * Default value: {@code null}
         */
        STYLE("style",
                PrintableType.HTML_ELEMENT, PrintableType.RAW_HTML),

        /**
         * When set to false, the library will not process styles applied to the
         * HTML being printed. Useful when using the CSS parameter.
         *
         * <p>
         * Default value: {@code true}
         */
        SCAN_STYLES("scanStyles", "true",
                PrintableType.HTML_ELEMENT, PrintableType.RAW_HTML),

        /**
         * By default, the library processes some styles only, when printing HTML
         * elements. This option allows you to pass an array of styles that you
         * want to be processed. {@code Ex.: ['padding-top', 'border-bottom']}
         *
         * <p>
         * Default value: {@code null}
         */
        TARGET_STYLE("targetStyle",
                PrintableType.HTML_ELEMENT, PrintableType.RAW_HTML),

        /**
         * Same as TARGET_STYLE, however, this will process any a range of styles.
         * <p>
         * Ex.: {@code ['border', 'padding']}, will include
         * {@code 'border-bottom'}, {@code 'border-top'}, {@code 'border-left'}, {@code 'border-right'},
         * {@code 'padding-top'}, etc.
         * <p>
         * You can also pass {@code ['*']} to process all styles.
         *
         * <p>
         * Default value: {@code null}
         */
        TARGET_STYLES("targetStyles",
                PrintableType.HTML_ELEMENT, PrintableType.RAW_HTML),

        /**
         * Accepts an array of HTML IDs that should be ignored when printing a parent HTML element.
         *
         * <p>
         * Default value: {@code [ ]}
         */
        IGNORE_ELEMENTS("ignoreElements", "[ ]",
                PrintableType.HTML_ELEMENT, PrintableType.RAW_HTML),

        /**
         * Used when printing JSON. These are the object property names.
         *
         * <p>
         * Default value: {@code null}
         */
        JSON_PROPERTIES("properties",
                PrintableType.JSON),

        /**
         * Optional style for the grid header when printing JSON data.
         *
         * <p>
         * Default value: {@code 'font-weight: bold;'}
         */
        JSON_GRID_HEADER_STYLE("gridHeaderStyle", "'font-weight: bold;'",
                PrintableType.JSON),

        /**
         * Optional style for the grid rows when printing JSON data.
         *
         * <p>
         * Default value: {@code 'border: 1px solid lightgray; margin-bottom: -1px;'}
         */
        JSON_GRID_STYLE("gridStyle", "'border: 1px solid lightgray; margin-bottom: -1px;'",
                PrintableType.JSON),

        /**
         * Used when printing JSON data. When set to false,
         * the data table header will show in first page only.
         *
         * <p>
         * Default value: {@code true}
         */
        JSON_REPEAT_TABLE_HEADER("repeatTableHeader", "true",
                PrintableType.JSON),

        /**
         * Enable this option to show feedback to user when retrieving or processing large PDF files.
         *
         * <p>
         * Default value: {@code null}
         */
        SHOW_MODAL("showModal",
                PrintableType.PDF),

        /**
         * Message displayed to users when showModal is set to true.
         *
         * <p>
         * Default value: {@code 'Retrieving Document...'}
         */
        MODAL_MESSAGE("modalMessage", "'Retrieving Document...'",
                PrintableType.PDF),

        /**
         * Function to be executed when PDF is being loaded
         *
         * <p>
         * Default value: {@code null}
         */
        ON_LOADING_START("onLoadingStart",
                PrintableType.PDF),

        /**
         * Function to be executed after PDF has loaded
         *
         * <p>
         * Default value: {@code null}
         */
        ON_LOADING_END("onLoadingEnd",
                PrintableType.PDF),

        /**
         * When printing images, HTML, or JSON, this will be shown as the document title.
         *
         * <p>
         * Default value: {@code 'Document'}
         */
        DOCUMENT_TITLE("documentTitle", "'Document'",
                PrintableType.IMAGE, PrintableType.HTML_ELEMENT, PrintableType.RAW_HTML, PrintableType.JSON),

        /**
         * When printing PDF, if the browser is not compatible (check browser
         * compatibility table), the library will open the PDF in a new tab. This
         * allows you to pass a different PDF document to be opened instead of the
         * original passed in `printable`. This may be useful if you inject
         * javascript in your alternate PDF file.
         *
         * <p>
         * Default value: {@code null}
         */
        FALLBACK_PRINTABLE("fallbackPrintable",
                PrintableType.PDF),

        /**
         * When printing PDF, if the browser is not compatible (check browser
         * compatibility table), the library will open the PDF in a new tab. A
         * callback function can be passed here, which will be executed when this
         * happens. It may be useful in some situations where you want to handle
         * your print flow, update user interface, etc.
         *
         * <p>
         * Default value: {@code null}
         */
        ON_PDF_OPEN("onPdfOpen",
                PrintableType.PDF),

        /**
         * Callback function executed once the browser print dialog is closed.
         *
         * <p>
         * Default value: {@code null}
         */
        ON_PRINT_DIALOG_CLOSE("onPrintDialogClose",
                PrintableType.values()),

        /**
         * Used when printing PDF documents passed as base64 data.
         *
         * <p>
         * Default value: {@code false}
         */
        BASE64("base64", "false",
                PrintableType.PDF);

        private final String key;
        private final String defaultValue;
        private final Set<PrintableType> printableTypes;

        Parameter(String key, String defaultValue, PrintableType... printableTypes) {
            this.key = key;
            this.defaultValue = defaultValue;
            this.printableTypes = Set.of(printableTypes);
        }

        Parameter(String key, PrintableType... printableTypes) {
            this(key, null, printableTypes);
        }

        boolean isSupportedBy(PrintableType printableType) {
            return printableTypes.contains(printableType);
        }
    }

}
