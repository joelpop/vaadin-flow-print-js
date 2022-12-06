package org.vaadin.joelpop.addon.printjs;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.vaadin.joelpop.addon.printjs.PrintJs.Parameter.*;

/**
 * A Java interface to Print.js to set its parameters and print.
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
    public PrintJs() {
        parameters = new EnumMap<>(Parameter.class);
    }

    /**
     * Create a PrintJs object and specify the document source to be printed.
     * <p>
     * If no printable type is subsequently set, the type is PDF.
     *
     * @param printable the path of the document to be printed.
     */
    public PrintJs(String printable) {
        this();
        setPrintable(printable);
    }

    /**
     * Create a PrintJs object and specify the document source and its type to be printed.
     *
     * @param printable the document source to be printed.
     * @param printableType the type of document to be printed.
     */
    public PrintJs(String printable, PrintableType printableType) {
        this();
        setPrintable(printable);
        setPrintableType(printableType);
    }

    // actions

    /**
     * Print the document source using the previously-set parameters.
     *
     * @param ui the UI from which to print the document source.
     */
    public void print(UI ui) {
        ui.getPage().executeJs("printJS(%s)".formatted(getParameterString()));
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
     * <p>
     * If no printable type is set, the document source is assumed to be PDF.
     *
     * <p>
     * Valid printable type are:
     * <ul>
     *   <li>{@code PDF} - the URL of a PDF file on the server</li>
     *   <li>{@code HTML} - the ID of an HTML element</li>
     *   <li>{@code IMAGE} - the URL of an image file on the server</li>
     *   <li>{@code JSON} - a JSON data object</li>
     *   <li>{@code RAW_HTML} - raw HTML</li>
     * </ul>
     *
     * @param printable the document source.
     * Default value: {@code null}
     */
    public void setPrintable(String printable) {
        setStringParameter(PRINTABLE, printable);
    }

    /**
     * Get the printable type.
     *
     * @return the printable type
     */
    public PrintableType getPrintableType() {
        return getPrintableTypeParameter(TYPE);
    }

    /**
     * Set the printable type.
     *
     * @param printableType the printable type.
     * Valid printable types are:
     * <ul>
     *   <li>{@code PDF} - the URL of a PDF file on the server</li>
     *   <li>{@code HTML} - the ID of an HTML element</li>
     *   <li>{@code IMAGE} - the URL of an image file on the server</li>
     *   <li>{@code JSON} - a JSON data object</li>
     *   <li>{@code RAW_HTML} - raw HTML</li>
     * </ul>
     * Default value: {@code PDF}
     */
    public void setPrintableType(PrintableType printableType) {
        setPrintableTypeParameter(TYPE, printableType);
    }

    /**
     * Get the optional header text to be used with HTML, image, or JSON printing.
     *
     * @return the optional header text to be used with HTML, image, or JSON printing
     */
    public String getHeaderText() {
        return getStringParameter(HEADER);
    }

    /**
     * Set optional header text to be used with HTML, image, or JSON printing.
     *
     * <p>
     * It will be placed on the top of the page. This property will accept text or raw.
     * HTML.
     *
     * @param headerText header text.
     * Default value: {@code null}
     */
    public void setHeaderText(String headerText) {
        setStringParameter(HEADER, headerText);
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
     * Default value: {@code 'font-weight: 300;'}
     */
    public void setHeaderStyle(String headerStyle) {
        setStringParameter(HEADER_STYLE, headerStyle);
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
     * Change this as you need. Used when printing HTML, images, or JSON.
     *
     * @param maxDocumentWidth the maximum document width in pixels.
     * Default value: {@code 800}
     */
    public void setMaxWidth(Integer maxDocumentWidth) {
        setIntegerParameter(MAX_WIDTH, maxDocumentWidth);
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
     * Default value: {@code null}
     */
    public void setCssUrls(String... cssUrls) {
        setStringArrayParameter(CSS, cssUrls);
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
     * Default value: {@code null}
     */
    public void setStyle(String style) {
        setStringParameter(STYLE, style);
    }

    /**
     * Get if the library should process styles applied to the HTML being printed.
     *
     * @return
     *  {@code true} if the library should process styles applied to the HTML being printed,
     *  {@code false} if the library should not process styles applied to the HTML being printed.
     */
    public boolean isScanStyles() {
        return getBooleanParameter(SCAN_STYLES);
    }

    /**
     * Set if the library should process styles applied to the HTML being printed.
     *
     * <p>
     * Useful when using the CSS parameter.
     *
     * @param scanStyles
     *  {@code true} if the library should process styles applied to the HTML being printed,
     *  {@code false} if the library should not process styles applied to the HTML being printed.
     * Default value: {@code true}
     */
    public void setScanStyles(Boolean scanStyles) {
        setBooleanParameter(SCAN_STYLES, scanStyles);
    }

    /**
     * Set if the library should process styles applied to the HTML being printed.
     *
     * <p>
     * Useful when using the CSS parameter.
     *
     * @param scanStyles
     *  {@code true} if the library should process styles applied to the HTML being printed,
     *  {@code false} if the library should not process styles applied to the HTML being printed.
     * Default value: {@code true}
     */
    public void setScanStyles(boolean scanStyles) {
        setScanStyles((Boolean) scanStyles);
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
     * @param targetStyle an array of styles to be processed when printing HTML elements.
     * Default value: {@code null}
     */
    public void setTargetStyles(String... targetStyle) {
        setStringArrayParameter(TARGET_STYLE, targetStyle);
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
     * Default value: {@code null}
     */
    public void setTargetStylePatterns(String... targetStyles) {
        setStringArrayParameter(TARGET_STYLE, targetStyles);
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
     * Default value: {@code [ ]}
     */
    public void setIgnoreElements(String... ignoreElements) {
        setStringArrayParameter(IGNORE_ELEMENTS, ignoreElements);
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
     * Default value: {@code null}
     */
    public void setJsonProperties(String... jsonProperties) {
        setStringArrayParameter(JSON_PROPERTIES, jsonProperties);
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
     * Default value: {@code 'font-weight: bold;'}
     */
    public void setJsonGridHeaderStyle(String jsonGridHeaderStyle) {
        setStringParameter(JSON_GRID_HEADER_STYLE, jsonGridHeaderStyle);
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
     * Default value: {@code 'border: 1px solid lightgray; margin-bottom: -1px;'}
     */
    public void setJsonGridStyle(String jsonGridStyle) {
        setStringParameter(JSON_GRID_STYLE, jsonGridStyle);
    }

    /**
     * Get if the JSON data table header should show on all pages or the first page only.
     *
     * @return
     *  {@code true} if the JSON data table header should show on all pages,
     *  {@code false} if the JSON data table header should show on the first page only.
     */
    public boolean isJsonRepeatTableHeader() {
        return getBooleanParameter(JSON_REPEAT_TABLE_HEADER);
    }

    /**
     * Set if the JSON data table header should show on all pages or the first page only.
     *
     * @param jsonRepeatTableHeader
     *  {@code true} if the JSON data table header should show on all pages,
     *  {@code false} if the JSON data table header should show on the first page only.
     * Default value: {@code true}
     */
    public void setJsonRepeatTableHeader(Boolean jsonRepeatTableHeader) {
        setBooleanParameter(JSON_REPEAT_TABLE_HEADER, jsonRepeatTableHeader);
    }

    /**
     * Set if the JSON data table header should show on all pages or the first page only.
     *
     * @param jsonRepeatTableHeader
     *  {@code true} if the JSON data table header should show on all pages,
     *  {@code false} if the JSON data table header should show on the first page only.
     * Default value: {@code true}
     */
    public void setJsonRepeatTableHeader(boolean jsonRepeatTableHeader) {
        setJsonRepeatTableHeader((Boolean) jsonRepeatTableHeader);
    }

    /**
     * Get if the feedback dialog will be shown to user when retrieving or processing large PDF files.
     *
     * @return
     *  {@code true} if feedback dialog should be shown,
     *  {@code false} if no feedback dialog should be shown.
     */
    public boolean isShowModal() {
        return Boolean.TRUE.equals(getBooleanParameter(SHOW_MODAL));
    }

    /**
     * Set if a feedback dialog should be shown to user when retrieving or processing large PDF files.
     *
     * @param showModal
     *  {@code true} if feedback dialog should be shown,
     *  {@code false} if no feedback dialog should be shown.
     * Default value: {@code null}
     */
    public void setShowModal(Boolean showModal) {
        setBooleanParameter(SHOW_MODAL, showModal);
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
     * Default value: {@code 'Retrieving Document...'}
     */
    public void setModalMessage(String modalMessage) {
        setStringParameter(MODAL_MESSAGE, modalMessage);
    }

    /**
     * Set the document title shown when printing HTML, image or JSON.
     *
     * @return the document title.
     */
    public String getDocumentTitle() {
        return getStringParameter(DOCUMENT_TITLE);
    }

    /**
     * Set the document title shown when printing HTML, image or JSON.
     *
     * @param documentTitle the document title.
     * Default value: {@code 'Document'}
     */
    public void setDocumentTitle(String documentTitle) {
        setStringParameter(DOCUMENT_TITLE, documentTitle);
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
     * Default value: {@code null}
     */
    public void setFallbackPrintable(String fallbackPrintable) {
        setStringParameter(FALLBACK_PRINTABLE, fallbackPrintable);
    }

    /**
     * Get if PDF document is encoded as base64 data.
     *
     * @return
     *  {@code true} if PDF document is encoded as base64 data,
     *  {@code false} if PDF document is not encoded.
     */
    public boolean isBase64() {
        return getBooleanParameter(BASE64);
    }

    /**
     * Set if PDF document is encoded as base64 data.
     *
     * @param base64
     *  {@code true} if PDF document is encoded as base64 data,
     *  {@code false} if PDF document is not encoded.
     * Default value: {@code false}
     */
    public void setBase64(Boolean base64) {
        setBooleanParameter(BASE64, base64);
    }

    /**
     * Set if PDF document is encoded as base64 data.
     *
     * @param base64
     *  {@code true} if PDF document is encoded as base64 data,
     *  {@code false} if PDF document is not encoded.
     * Default value: {@code false}
     */
    public void setBase64(boolean base64) {
        setBase64((Boolean) base64);
    }

    /**
     * Get the Javascript function to be executed before PDF is loaded.
     *
     * @return the Javascript function to be executed.
     */
    public String getOnLoadingStart() {
        return getStringParameter(ON_LOADING_START);
    }

    /**
     * Set a Javascript function to be executed before PDF is loaded.
     *
     * @param onLoadingStart a Javascript function to be executed.
     * Default value: {@code null}
     */
    public void setOnLoadingStart(String onLoadingStart) {
        setStringParameter(ON_LOADING_START, onLoadingStart);
    }

    /**
     * Get the Javascript function to be executed after PDF has loaded.
     *
     * @return the Javascript function to be executed.
     */
    public String getOnLoadingEnd() {
        return getStringParameter(ON_LOADING_END);
    }

    /**
     * Set a Javascript function to be executed after PDF has loaded.
     *
     * @param onLoadingEnd a Javascript function to be executed.
     * Default value: {@code null}
     */
    public void setOnLoadingEnd(String onLoadingEnd) {
        setStringParameter(ON_LOADING_END, onLoadingEnd);
    }

    /**
     * Get the Javascript function to call if the browser is not compatible for printing PDFs.
     *
     * @return the Javascript function to call.
     */
    public String getOnPdfOpen() {
        return getStringParameter(ON_PDF_OPEN);
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
     * Default value: {@code null}
     */
    public void setOnPdfOpen(String onPdfOpen) {
        setStringParameter(ON_PDF_OPEN, onPdfOpen);
    }

    /**
     * Set the Javascript function to call after the browser print dialog is closed.
     *
     * @return the Javascript function to call.
     */
    public String getOnPrintDialogClose() {
        return getStringParameter(ON_PRINT_DIALOG_CLOSE);
    }

    /**
     * Set a Javascript function to call after the browser print dialog is closed.
     *
     * @param onPrintDialogClose a Javascript function to call.
     * Default value: {@code null}
     */
    public void setOnPrintDialogClose(String onPrintDialogClose) {
        setStringParameter(ON_PRINT_DIALOG_CLOSE, onPrintDialogClose);
    }

    /**
     * Resets the configuration back to its defaults.
     */
    public void reset() {
        parameters.clear();
    }


    // utility

    private Boolean getBooleanParameter(Parameter parameter) {
        return getRawParameter(parameter, Boolean::parseBoolean);
    }

    private void setBooleanParameter(Parameter parameter, Boolean value) {
        setRawParameter(parameter, value, v -> Boolean.TRUE.equals(v) ? "true" : "false");
    }

    private PrintableType getPrintableTypeParameter(Parameter parameter) {
        return PrintableType.fromKey(getStringParameter(parameter));
    }

    private void setPrintableTypeParameter(Parameter parameter, PrintableType printableType) {
        setStringParameter(parameter, printableType.key);
    }

    private Integer getIntegerParameter(Parameter parameter) {
        return getRawParameter(parameter, Integer::parseInt);
    }

    private void setIntegerParameter(Parameter parameter, Integer value) {
        setRawParameter(parameter, value, Object::toString);
    }

    private String getStringParameter(Parameter parameter) {
        return getRawParameter(parameter, PrintJs::unEscapeString);
    }

    private void setStringParameter(Parameter parameter, String value) {
        setRawParameter(parameter, value, PrintJs::escapeString);
    }

    private String[] getStringArrayParameter(Parameter parameter) {
        return getRawParameter(parameter, r -> Optional.of(r)
                .filter(a -> !a.matches("\\[ *]"))
                .map(a -> a.substring(2, a.length() - 4))
                .map(a -> a.split("','"))
                .stream()
                .flatMap(Arrays::stream)
                .map("'%s'"::formatted)
                .map(PrintJs::unEscapeString)
                .toArray(String[]::new));
    }

    private void setStringArrayParameter(Parameter parameter, String... values) {
        setRawParameter(parameter, values,
                a -> "[%s]".formatted(Arrays.stream(a)
                        .map(PrintJs::escapeString)
                        .collect(Collectors.joining(","))));
    }

    private <T> T getRawParameter(Parameter parameter, Function<String, T> fromRawConverter) {
        var value = Optional.ofNullable(parameters.get(parameter))
                .orElse(parameter.defaultValue);
        return Optional.ofNullable(value)
                .map(fromRawConverter)
                .orElse(null);
    }

    private <T> void setRawParameter(Parameter parameter, T value, Function<T, String> toRawConverter) {
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
                .map("'%s'"::formatted)
                .orElse(null);
    }

    private static String unEscapeString(String value) {
        return Optional.ofNullable(value)
                .map(v -> v.substring(1, value.length() - 2))
                .map(v -> v.replace("\\\\", "\\"))
                .orElse(null);
    }

    private String getParameterString() {
        return "{%s}".formatted(parameters.entrySet().stream()
                .map(parameterEntry -> parameterEntry.getKey().key + ":" + parameterEntry.getValue())
                .collect(Collectors.joining(",")));
    }

    @Override
    public String toString() {
            return getParameterString();
        }


    // internal enums

    /**
     * The type of document source to print.
     */
    public enum PrintableType {
        /**
         * The URL of a PDF file on the server.
         */
        PDF("pdf"),
        /**
         * The ID of an HTML element.
         */
        HTML("html"),
        /**
         * The URL of an image file on the server.
         */
        IMAGE("image"),
        /**
         * A JSON data object.
         */
        JSON("json"),
        /**
         * Raw HTML.
         */
        RAW_HTML("raw-html");

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
         * Document source: PDF URL, image URL, HTML element id, or JSON data object.
         *
         * <p>
         * Default value: {@code null}
         */
        PRINTABLE("printable"),

        /**
         * Printable type. Available print options are: PDF, HTML, image, JSON and raw-HTML.
         *
         * <p>
         * Default value: {@code 'pdf'}
         */
        TYPE("type", "'pdf'"),

        /**
         * Optional header to be used with HTML, image or JSON printing. It will
         * be placed on the top of the page. This property will accept text or raw
         * HTML.
         *
         * <p>
         * Default value: {@code null}
         */
        HEADER("header"),

        /**
         * Optional header style to be applied to the header text.
         *
         * <p>
         * Default value: {@code 'font-weight: 300;'}
         */
        HEADER_STYLE("headerStyle", "'font-weight: 300;'"),

        /**
         * Max document width in pixels. Change this as you need. Used when
         * printing HTML, images or JSON.
         *
         * <p>
         * Default value: {@code 800}
         */
        MAX_WIDTH("maxWidth", "800"),

        /**
         * This allows us to pass one or more CSS file URLs that should be applied
         * to the HTML being printed. Value can be a string with a single URL or
         * an array with multiple URLs.
         *
         * <p>
         * Default value: {@code null}
         */
        CSS("css"),

        /**
         * This allows us to pass a string with custom style that should be applied
         * to the HTML being printed.
         *
         * <p>
         * Default value: {@code null}
         */
        STYLE("style"),

        /**
         * When set to false, the library will not process styles applied to the
         * HTML being printed. Useful when using the CSS parameter.
         *
         * <p>
         * Default value: {@code true}
         */
        SCAN_STYLES("scanStyles", "true"),

        /**
         * By default, the library process some styles only, when printing HTML
         * elements. This option allows you to pass an array of styles that you
         * want to be processed. {@code Ex.: ['padding-top', 'border-bottom']}
         *
         * <p>
         * Default value: {@code null}
         */
        TARGET_STYLE("targetStyle"),

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
        TARGET_STYLES("targetStyles"),

        /**
         * Accepts an array of HTML IDs that should be ignored when printing a parent HTML element.
         *
         * <p>
         * Default value: {@code [ ]}
         */
        IGNORE_ELEMENTS("ignoreElements", "[ ]"),

        /**
         * Used when printing JSON. These are the object property names.
         *
         * <p>
         * Default value: {@code null}
         */
        JSON_PROPERTIES("properties"),

        /**
         * Optional style for the grid header when printing JSON data.
         *
         * <p>
         * Default value: {@code 'font-weight: bold;'}
         */
        JSON_GRID_HEADER_STYLE("gridHeaderStyle", "'font-weight: bold;'"),

        /**
         * Optional style for the grid rows when printing JSON data.
         *
         * <p>
         * Default value: {@code 'border: 1px solid lightgray; margin-bottom: -1px;'}
         */
        JSON_GRID_STYLE("gridStyle", "'border: 1px solid lightgray; margin-bottom: -1px;'"),

        /**
         * Used when printing JSON data. When set to false,
         * the data table header will show in first page only.
         *
         * <p>
         * Default value: {@code true}
         */
        JSON_REPEAT_TABLE_HEADER("repeatTableHeader", "true"),

        /**
         * Enable this option to show feedback to user when retrieving or processing large PDF files.
         *
         * <p>
         * Default value: {@code null}
         */
        SHOW_MODAL("showModal"),

        /**
         * Message displayed to users when showModal is set to true.
         *
         * <p>
         * Default value: {@code 'Retrieving Document...'}
         */
        MODAL_MESSAGE("modalMessage", "'Retrieving Document...'"),

        /**
         * Function to be executed when PDF is being loaded
         *
         * <p>
         * Default value: {@code null}
         */
        ON_LOADING_START("onLoadingStart"),

        /**
         * Function to be executed after PDF has loaded
         *
         * <p>
         * Default value: {@code null}
         */
        ON_LOADING_END("onLoadingEnd"),

        /**
         * When printing HTML, image or JSON, this will be shown as the document title.
         *
         * <p>
         * Default value: {@code 'Document'}
         */
        DOCUMENT_TITLE("documentTitle", "'Document'"),

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
        FALLBACK_PRINTABLE("fallbackPrintable"),

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
        ON_PDF_OPEN("onPdfOpen"),

        /**
         * Callback function executed once the browser print dialog is closed.
         *
         * <p>
         * Default value: {@code null}
         */
        ON_PRINT_DIALOG_CLOSE("onPrintDialogClose"),

        /**
         * Used when printing PDF documents passed as base64 data.
         *
         * <p>
         * Default value: {@code false}
         */
        BASE64("base64", "false");

        private final String key;
        private final String defaultValue;

        Parameter(String key, String defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
        }

        Parameter(String key) {
            this(key, null);
        }
    }

}
