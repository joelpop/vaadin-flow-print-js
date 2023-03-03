package org.vaadin.addons.joelpop.printjs.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.vaadin.addons.joelpop.printjs.PrintJs;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class RawHtmlTest {
    public static final String PRINTABLE = "<div class=\"raw\">Hello, <span style=\"font-weight:500;\">World</span>!</div>";
    public static final String PRINTABLE_TYPE = "raw-html";
    public static final Supplier<PrintJs> INSTANTIATOR = () -> PrintJs.fromRawHtml(PRINTABLE);

    private PrintJs printJs;

    @BeforeMethod
    private void instantiate() {
        printJs = INSTANTIATOR.get();
    }

    @Test
    public void checkInstantiation() {
        assertThat(printJs)
                .as("printable for raw HTML and its printable type")
                .hasToString(ParameterBuilder.create()
                        .addWithQuotes("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .getParameterObject());
    }

    @Test
    public void checkInstantiationReset() {
        printJs.reset();

        assertThat(printJs)
                .as("printable for raw HTML and its printable type")
                .hasToString(ParameterBuilder.create()
                        .addWithQuotes("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .getParameterObject());
    }

    @Test
    public void checkFullInstantiationReset() {
        printJs
                .setHeaderText("Header Text")
                .setHeaderStyle("Modal Message")
                .setMaxWidth(1000)
                .setCssUrls("CSS URLs")
                .setStyle("border:none;")
                .setScanStyles(false)
                .setTargetStyles("target-style")
                .setTargetStylePatterns("target")
                .setDocumentTitle("Document Title")
                .setOnPrintDialogClose("window.alert('print dialog close')")
                .reset();

        assertThat(printJs)
                .as("printable for raw HTML and its printable type")
                .hasToString(ParameterBuilder.create()
                        .addWithQuotes("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .getParameterObject());
    }

    @Test
    public void checkHeaderText() {
        assertThat(printJs.getHeaderText())
                .as("header default")
                .isNull();
        assertThat(printJs.toString())
                .as("header default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("header", null)
                        .getParameters());

        printJs.setHeaderText("Header Text");
        assertThat(printJs.getHeaderText())
                .as("header set")
                .isEqualTo("Header Text");
        assertThat(printJs.toString())
                .as("header set parameter")
                .contains(ParameterBuilder.create()
                        .addWithQuotes("header", "Header Text")
                        .getParameters());

        printJs.setHeaderText(null);
        assertThat(printJs.getHeaderText())
                .as("header reset")
                .isNull();
        assertThat(printJs.toString())
                .as("header reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("header", null)
                        .getParameters());
    }

    @Test
    public void checkHeaderStyle() {
        assertThat(printJs.getHeaderStyle())
                .as("headerStyle default")
                .isEqualTo("font-weight: 300;");
        assertThat(printJs.toString())
                .as("headerStyle default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("headerStyle", null)
                        .getParameters());

        printJs.setHeaderStyle("border: 3px solid blue;");
        assertThat(printJs.getHeaderStyle())
                .as("headerStyle set")
                .isEqualTo("border: 3px solid blue;");
        assertThat(printJs.toString())
                .as("headerStyle set parameter")
                .contains(ParameterBuilder.create()
                        .addWithQuotes("headerStyle", "border: 3px solid blue;")
                        .getParameters());

        printJs.setHeaderStyle(null);
        assertThat(printJs.getHeaderStyle())
                .as("headerStyle reset")
                .isEqualTo("font-weight: 300;");
        assertThat(printJs.toString())
                .as("headerStyle reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("headerStyle", null)
                        .getParameters());
    }

    @Test
    public void checkMaxWidth() {
        assertThat(printJs.getMaxWidth())
                .as("maxWidth default")
                .isEqualTo(800);
        assertThat(printJs.toString())
                .as("maxWidth default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("maxWidth", null)
                        .getParameters());

        printJs.setMaxWidth(1000);
        assertThat(printJs.getMaxWidth())
                .as("maxWidth set")
                .isEqualTo(1000);
        assertThat(printJs.toString())
                .as("maxWidth set parameter")
                .contains(ParameterBuilder.create()
                        .add("maxWidth", "1000")
                        .getParameters());

        printJs.setMaxWidth(null);
        assertThat(printJs.getMaxWidth())
                .as("maxWidth reset")
                .isEqualTo(800);
        assertThat(printJs.toString())
                .as("maxWidth reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("maxWidth", null)
                        .getParameters());
    }

    @Test
    public void checkCssUrls() {
        assertThat(printJs.getCssUrls())
                .as("css default")
                .isNull();
        assertThat(printJs.toString())
                .as("css default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("css", null)
                        .getParameters());

        printJs.setCssUrls("css/style1.css", "css/style2.css");
        assertThat(printJs.getCssUrls())
                .as("css set")
                .containsOnly("css/style1.css", "css/style2.css");
        assertThat(printJs.toString())
                .as("css set parameter")
                .contains(ParameterBuilder.create()
                        .addArrayWithQuotes("css", "css/style1.css", "css/style2.css")
                        .getParameters());

        printJs.setCssUrls();
        assertThat(printJs.getCssUrls())
                .as("css reset")
                .isNull();
        assertThat(printJs.toString())
                .as("css reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("css", null)
                        .getParameters());
    }

    @Test
    public void checkStyle() {
        assertThat(printJs.getStyle())
                .as("style default")
                .isNull();
        assertThat(printJs.toString())
                .as("style default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("style", null)
                        .getParameters());

        printJs.setStyle("name: value;");
        assertThat(printJs.getStyle())
                .as("style set")
                .isEqualTo("name: value;");
        assertThat(printJs.toString())
                .as("style set parameter")
                .contains(ParameterBuilder.create()
                        .addWithQuotes("style", "name: value;")
                        .getParameters());

        printJs.setStyle(null);
        assertThat(printJs.getStyle())
                .as("style reset")
                .isNull();
        assertThat(printJs.toString())
                .as("style reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("style", null)
                        .getParameters());
    }

    @Test
    public void checkScanStyles() {
        assertThat(printJs.isScanStyles())
                .as("scanStyles default")
                .isTrue();
        assertThat(printJs.toString())
                .as("scanStyles default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("scanStyles", null)
                        .getParameters());

        printJs.setScanStyles(true);
        assertThat(printJs.isScanStyles())
                .as("scanStyles set")
                .isTrue();
        assertThat(printJs.toString())
                .as("scanStyles set parameter")
                .contains(ParameterBuilder.create()
                        .add("scanStyles", "true")
                        .getParameters());

        printJs.setScanStyles(false);
        assertThat(printJs.isScanStyles())
                .as("scanStyles set")
                .isFalse();
        assertThat(printJs.toString())
                .as("scanStyles set parameter")
                .contains(ParameterBuilder.create()
                        .add("scanStyles", "false")
                        .getParameters());

        printJs.resetScanStyles();
        assertThat(printJs.isScanStyles())
                .as("scanStyles reset")
                .isTrue();
        assertThat(printJs.toString())
                .as("scanStyles reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("scanStyles", null)
                        .getParameters());
    }

    @Test
    public void checkTargetStyles() {
        assertThat(printJs.getTargetStyles())
                .as("targetStyles default")
                .isNull();
        assertThat(printJs.toString())
                .as("targetStyles default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("targetStyle", null)
                        .getParameters());

        printJs.setTargetStyles("padding-top", "border-bottom");
        assertThat(printJs.getTargetStyles())
                .as("targetStyles set")
                .containsOnly("padding-top", "border-bottom");
        assertThat(printJs.toString())
                .as("targetStyles set parameter")
                .contains(ParameterBuilder.create()
                        .addArrayWithQuotes("targetStyle", "padding-top", "border-bottom")
                        .getParameters());

        printJs.setTargetStyles();
        assertThat(printJs.getTargetStyles())
                .as("targetStyles reset")
                .isNull();
        assertThat(printJs.toString())
                .as("targetStyles reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("targetStyle", null)
                        .getParameters());
    }

    @Test
    public void checkTargetStylePatterns() {
        assertThat(printJs.getTargetStylePatterns())
                .as("targetStylePatterns default")
                .isNull();
        assertThat(printJs.toString())
                .as("targetStylePatterns default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("targetStyles", null)
                        .getParameters());

        printJs.setTargetStylePatterns("border", "padding");
        assertThat(printJs.getTargetStylePatterns())
                .as("targetStylePatterns set")
                .containsOnly("border", "padding");
        assertThat(printJs.toString())
                .as("targetStylePatterns set parameter")
                .contains(ParameterBuilder.create()
                        .addArrayWithQuotes("targetStyles", "border", "padding")
                        .getParameters());

        printJs.setTargetStylePatterns();
        assertThat(printJs.getTargetStylePatterns())
                .as("targetStylePatterns reset")
                .isNull();
        assertThat(printJs.toString())
                .as("targetStylePatterns reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("targetStyles", null)
                        .getParameters());
    }

    @Test
    public void checkIgnoreElements() {
        assertThat(printJs.getIgnoreElements())
                .as("ignoreElements default")
                .isEmpty();
        assertThat(printJs.toString())
                .as("ignoreElements default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("ignoreElements", null)
                        .getParameters());

        printJs.setIgnoreElements("ignoreElement1", "ignoreElement2");
        assertThat(printJs.getIgnoreElements())
                .as("ignoreElements set")
                .containsOnly("ignoreElement1", "ignoreElement2");
        assertThat(printJs.toString())
                .as("ignoreElements set parameter")
                .contains(ParameterBuilder.create()
                        .addArrayWithQuotes("ignoreElements", "ignoreElement1", "ignoreElement2")
                        .getParameters());

        printJs.setIgnoreElements();
        assertThat(printJs.getIgnoreElements())
                .as("ignoreElements reset")
                .isEmpty();
        assertThat(printJs.toString())
                .as("ignoreElements reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("ignoreElements", null)
                        .getParameters());
    }

    @Test
    public void checkDocumentTitle() {
        assertThat(printJs.getDocumentTitle())
                .as("documentTitle default")
                .isEqualTo("Document");
        assertThat(printJs.toString())
                .as("documentTitle default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("documentTitle", null)
                        .getParameters());

        printJs.setDocumentTitle("Document Title");
        assertThat(printJs.getDocumentTitle())
                .as("documentTitle set")
                .isEqualTo("Document Title");
        assertThat(printJs.toString())
                .as("documentTitle set parameter")
                .contains(ParameterBuilder.create()
                        .addWithQuotes("documentTitle", "Document Title")
                        .getParameters());

        printJs.setDocumentTitle(null);
        assertThat(printJs.getDocumentTitle())
                .as("documentTitle reset")
                .isEqualTo("Document");
        assertThat(printJs.toString())
                .as("documentTitle reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("documentTitle", null)
                        .getParameters());
    }

    @Test
    public void checkOnPrintDialogClose() {
        assertThat(printJs.getOnPrintDialogClose())
                .as("onPrintDialogClose default")
                .isNull();
        assertThat(printJs.toString())
                .as("onPrintDialogClose default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("onPrintDialogClose", null)
                        .getParameters());

        printJs.setOnPrintDialogClose("onPrintDialogClose");
        assertThat(printJs.getOnPrintDialogClose())
                .as("onPrintDialogClose set")
                .isEqualTo("onPrintDialogClose");
        assertThat(printJs.toString())
                .as("onPrintDialogClose set parameter")
                .contains(ParameterBuilder.create()
                        .add("onPrintDialogClose", "onPrintDialogClose")
                        .getParameters());

        printJs.setOnPrintDialogClose(null);
        assertThat(printJs.getOnPrintDialogClose())
                .as("onPrintDialogClose reset")
                .isNull();
        assertThat(printJs.toString())
                .as("onPrintDialogClose reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("onPrintDialogClose", null)
                        .getParameters());
    }

    @Test
    public void checkIllegalArguments() {
        assertThatIllegalArgumentException()
                .as("properties is not a valid parameter for HTML")
                .isThrownBy(() -> printJs.getJsonProperties());

        assertThatIllegalArgumentException()
                .as("gridHeaderStyle is not a valid parameter for HTML")
                .isThrownBy(() -> printJs.getJsonGridHeaderStyle());

        assertThatIllegalArgumentException()
                .as("gridStyle is not a valid parameter for HTML")
                .isThrownBy(() -> printJs.getJsonGridStyle());

        assertThatIllegalArgumentException()
                .as("repeatTableHeader is not a valid parameter for HTML")
                .isThrownBy(() -> printJs.isJsonRepeatTableHeader());

        assertThatIllegalArgumentException()
                .as("showModal is not a valid parameter for HTML")
                .isThrownBy(() -> printJs.isShowModal());

        assertThatIllegalArgumentException()
                .as("modalMessage is not a valid parameter for HTML")
                .isThrownBy(() -> printJs.getModalMessage());

        assertThatIllegalArgumentException()
                .as("onLoadingStart is not a valid parameter for HTML")
                .isThrownBy(() -> printJs.getOnLoadingStart());

        assertThatIllegalArgumentException()
                .as("onLoadingEnd is not a valid parameter for HTML")
                .isThrownBy(() -> printJs.getOnLoadingEnd());

        assertThatIllegalArgumentException()
                .as("fallbackPrintable is not a valid parameter for HTML")
                .isThrownBy(() -> printJs.getFallbackPrintable());

        assertThatIllegalArgumentException()
                .as("onPdfOpen is not a valid parameter for HTML")
                .isThrownBy(() -> printJs.getOnPdfOpen());
    }
}
