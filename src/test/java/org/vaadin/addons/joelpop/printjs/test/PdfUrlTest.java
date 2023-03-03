package org.vaadin.addons.joelpop.printjs.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.vaadin.addons.joelpop.printjs.PrintJs;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PdfUrlTest {
    public static final String PRINTABLE = "doc/default.pdf";
    public static final String PRINTABLE_TYPE = "pdf";
    public static final Supplier<PrintJs> INSTANTIATOR = () -> PrintJs.fromPdfUrl(PRINTABLE);

    private PrintJs printJs;

    @BeforeMethod
    private void instantiate() {
        printJs = INSTANTIATOR.get();
    }

    @Test
    public void checkInstantiation() {
        assertThat(printJs)
                .as("printable for PDF URL and its printable type")
                .hasToString(ParameterBuilder.create()
                        .addWithQuotes("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .getParameterObject());
    }

    @Test
    public void checkInstantiationReset() {
        printJs.reset();

        assertThat(printJs)
                .as("printable for PDF URL and its printable type")
                .hasToString(ParameterBuilder.create()
                        .addWithQuotes("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .getParameterObject());
    }

    @Test
    public void checkFullInstantiationReset() {
        printJs
                .setShowModal(true)
                .setModalMessage("Modal Message")
                .setOnLoadingStart("window.alert('loading start')")
                .setOnLoadingEnd("window.alert('loading end')")
                .setFallbackPrintable("doc/fallback.pdf")
                .setOnPdfOpen("window.alert('PDF open')")
                .setOnPrintDialogClose("window.alert('print dialog close')")
                .reset();

        assertThat(printJs)
                .as("printable for PDF URL and its printable type")
                .hasToString(ParameterBuilder.create()
                        .addWithQuotes("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .getParameterObject());
    }

    @Test
    public void checkShowModal() {
        assertThat(printJs.isShowModal())
                .as("showModal default")
                .isFalse();
        assertThat(printJs.toString())
                .as("showModal default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("showModal", null)
                        .getParameters());

        printJs.setShowModal(true);
        assertThat(printJs.isShowModal())
                .as("showModal set to true")
                .isTrue();
        assertThat(printJs.toString())
                .as("showModal set to true parameter")
                .contains(ParameterBuilder.create()
                        .add("showModal", "true")
                        .getParameters());

        printJs.setShowModal(false);
        assertThat(printJs.isShowModal())
                .as("showModal set to false")
                .isFalse();
        assertThat(printJs.toString())
                .as("showModal set to false parameter")
                .contains(ParameterBuilder.create()
                        .add("showModal", "false")
                        .getParameters());

        printJs.resetShowModal();
        assertThat(printJs.isShowModal())
                .as("showModal reset")
                .isFalse();
        assertThat(printJs.toString())
                .as("showModal reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("showModal", null)
                        .getParameters());
    }

    @Test
    public void checkOnLoadingStart() {
        assertThat(printJs.getOnLoadingStart())
                .as("onLoadingStart default")
                .isNull();
        assertThat(printJs.toString())
                .as("onLoadingStart default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("onLoadingStart", null)
                        .getParameters());

        printJs.setOnLoadingStart("onLoadingStart");
        assertThat(printJs.getOnLoadingStart())
                .as("onLoadingStart set")
                .isEqualTo("onLoadingStart");
        assertThat(printJs.toString())
                .as("onLoadingStart set parameter")
                .contains(ParameterBuilder.create()
                        .add("onLoadingStart", "onLoadingStart")
                        .getParameters());

        printJs.setOnLoadingStart(null);
        assertThat(printJs.getOnLoadingStart())
                .as("onLoadingStart reset")
                .isNull();
        assertThat(printJs.toString())
                .as("onLoadingStart reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("onLoadingStart", null)
                        .getParameters());
    }

    @Test
    public void checkOnLoadingEnd() {
        assertThat(printJs.getOnLoadingEnd())
                .as("onLoadingEnd default")
                .isNull();
        assertThat(printJs.toString())
                .as("onLoadingEnd default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("onLoadingEnd", null)
                        .getParameters());

        printJs.setOnLoadingEnd("onLoadingEnd");
        assertThat(printJs.getOnLoadingEnd())
                .as("onLoadingEnd set")
                .isEqualTo("onLoadingEnd");
        assertThat(printJs.toString())
                .as("onLoadingEnd set parameter")
                .contains(ParameterBuilder.create()
                        .add("onLoadingEnd", "onLoadingEnd")
                        .getParameters());

        printJs.setOnLoadingEnd(null);
        assertThat(printJs.getOnLoadingEnd())
                .as("onLoadingEnd reset")
                .isNull();
        assertThat(printJs.toString())
                .as("onLoadingEnd reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("onLoadingEnd", null)
                        .getParameters());
    }

    @Test
    public void checkFallbackPrintable() {
        assertThat(printJs.getFallbackPrintable())
                .as("fallbackPrintable default")
                .isNull();
        assertThat(printJs.toString())
                .as("fallbackPrintable default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("fallbackPrintable", null)
                        .getParameters());

        printJs.setFallbackPrintable("doc/fallbackPrintable.pdf");
        assertThat(printJs.getFallbackPrintable())
                .as("fallbackPrintable set")
                .isEqualTo("doc/fallbackPrintable.pdf");
        assertThat(printJs.toString())
                .as("fallbackPrintable set parameter")
                .contains(ParameterBuilder.create()
                        .addWithQuotes("fallbackPrintable", "doc/fallbackPrintable.pdf")
                        .getParameters());

        printJs.setFallbackPrintable(null);
        assertThat(printJs.getFallbackPrintable())
                .as("fallbackPrintable reset")
                .isNull();
        assertThat(printJs.toString())
                .as("fallbackPrintable reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("fallbackPrintable", null)
                        .getParameters());
    }

    @Test
    public void checkOnPdfOpen() {
        assertThat(printJs.getOnPdfOpen())
                .as("onPdfOpen default")
                .isNull();
        assertThat(printJs.toString())
                .as("onPdfOpen default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("onPdfOpen", null)
                        .getParameters());

        printJs.setOnPdfOpen("onPdfOpen");
        assertThat(printJs.getOnPdfOpen())
                .as("onPdfOpen set")
                .isEqualTo("onPdfOpen");
        assertThat(printJs.toString())
                .as("onPdfOpen set parameter")
                .contains(ParameterBuilder.create()
                        .add("onPdfOpen", "onPdfOpen")
                        .getParameters());

        printJs.setOnPdfOpen(null);
        assertThat(printJs.getOnPdfOpen())
                .as("onPdfOpen reset")
                .isNull();
        assertThat(printJs.toString())
                .as("onPdfOpen reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("onPdfOpen", null)
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
                .as("header is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getHeaderText());

        assertThatIllegalArgumentException()
                .as("headerStyle is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getHeaderStyle());

        assertThatIllegalArgumentException()
                .as("maxWidth is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getMaxWidth());

        assertThatIllegalArgumentException()
                .as("css is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getCssUrls());

        assertThatIllegalArgumentException()
                .as("style is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getStyle());

        assertThatIllegalArgumentException()
                .as("scanStyles is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.isScanStyles());

        assertThatIllegalArgumentException()
                .as("targetStyle is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getTargetStyles());

        assertThatIllegalArgumentException()
                .as("targetStylePatterns is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getTargetStylePatterns());

        assertThatIllegalArgumentException()
                .as("ignoreElements is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getIgnoreElements());

        assertThatIllegalArgumentException()
                .as("properties is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getJsonProperties());

        assertThatIllegalArgumentException()
                .as("gridHeaderStyle is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getJsonGridHeaderStyle());

        assertThatIllegalArgumentException()
                .as("gridStyle is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getJsonGridStyle());

        assertThatIllegalArgumentException()
                .as("repeatTableHeader is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.isJsonRepeatTableHeader());

        assertThatIllegalArgumentException()
                .as("documentTitle is not a valid parameter for PDF")
                .isThrownBy(() -> printJs.getDocumentTitle());
    }
}
