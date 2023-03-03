package org.vaadin.addons.joelpop.printjs.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.vaadin.addons.joelpop.printjs.PrintJs;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ImageUrlTest {
    public static final String PRINTABLE = "doc/default.png";
    public static final String PRINTABLE_TYPE = "image";
    public static final Supplier<PrintJs> INSTANTIATOR = () -> PrintJs.fromImageUrl(PRINTABLE);

    private PrintJs printJs;

    @BeforeMethod
    private void instantiate() {
        printJs = INSTANTIATOR.get();
    }

    @Test
    public void checkInstantiation() {
        assertThat(printJs)
                .as("printable for Image URL and its printable type")
                .hasToString(ParameterBuilder.create()
                        .addArrayWithQuotes("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .getParameterObject());
    }

    @Test
    public void checkInstantiationReset() {
        printJs.reset();

        assertThat(printJs)
                .as("printable for Image URL and its printable type")
                .hasToString(ParameterBuilder.create()
                        .addArrayWithQuotes("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .getParameterObject());
    }

    @Test
    public void checkFullInstantiationReset() {
        printJs
                .setHeaderText("Header Text")
                .setHeaderStyle("Header Style")
                .setMaxWidth(1000)
                .setDocumentTitle("window.alert('loading end')")
                .setOnPrintDialogClose("window.alert('print dialog close')")
                .reset();

        assertThat(printJs)
                .as("printable for Image URL and its printable type")
                .hasToString(ParameterBuilder.create()
                        .addArrayWithQuotes("printable", PRINTABLE)
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

        printJs.setHeaderStyle("font-size: 16pt;");
        assertThat(printJs.getHeaderStyle())
                .as("headerStyle set")
                .isEqualTo("font-size: 16pt;");
        assertThat(printJs.toString())
                .as("headerStyle set parameter")
                .contains(ParameterBuilder.create()
                        .addWithQuotes("headerStyle", "font-size: 16pt;")
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
                .as("css is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getCssUrls());

        assertThatIllegalArgumentException()
                .as("style is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getStyle());

        assertThatIllegalArgumentException()
                .as("scanStyles is not a valid parameter for Image")
                .isThrownBy(() -> printJs.isScanStyles());

        assertThatIllegalArgumentException()
                .as("targetStyle is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getTargetStyles());

        assertThatIllegalArgumentException()
                .as("targetStylePatterns is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getTargetStylePatterns());

        assertThatIllegalArgumentException()
                .as("ignoreElements is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getIgnoreElements());

        assertThatIllegalArgumentException()
                .as("properties is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getJsonProperties());

        assertThatIllegalArgumentException()
                .as("gridHeaderStyle is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getJsonGridHeaderStyle());

        assertThatIllegalArgumentException()
                .as("gridStyle is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getJsonGridStyle());

        assertThatIllegalArgumentException()
                .as("repeatTableHeader is not a valid parameter for Image")
                .isThrownBy(() -> printJs.isJsonRepeatTableHeader());

        assertThatIllegalArgumentException()
                .as("showModal is not a valid parameter for Image")
                .isThrownBy(() -> printJs.isShowModal());

        assertThatIllegalArgumentException()
                .as("modalMessage is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getModalMessage());

        assertThatIllegalArgumentException()
                .as("onLoadingStart is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getOnLoadingStart());

        assertThatIllegalArgumentException()
                .as("onLoadingEnd is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getOnLoadingEnd());

        assertThatIllegalArgumentException()
                .as("fallbackPrintable is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getFallbackPrintable());

        assertThatIllegalArgumentException()
                .as("onPdfOpen is not a valid parameter for Image")
                .isThrownBy(() -> printJs.getOnPdfOpen());
    }
}
