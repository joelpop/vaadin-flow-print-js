package org.vaadin.joelpop.addon.printjs.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.vaadin.joelpop.addon.printjs.PrintJs;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public class Base64PdfTest {
    public static final String PRINTABLE = "not-really-base64";
    public static final String PRINTABLE_TYPE = "pdf";
    public static final Supplier<PrintJs> INSTANTIATOR = () -> PrintJs.fromBase64Pdf(PRINTABLE);

    private PrintJs printJs;

    @BeforeMethod
    private void instantiate() {
        printJs = INSTANTIATOR.get();
    }

    @Test
    public void checkInstantiation() {
        assertThat(printJs)
                .as("printable for base 64 PDF and its printable type")
                .hasToString(ParameterBuilder.create()
                        .add("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .add("base64", "true")
                        .getParameterObject());
    }

    @Test
    public void checkInstantiationReset() {
        printJs.reset();

        assertThat(printJs)
                .as("printable for base 64 PDF and its printable type")
                .hasToString(ParameterBuilder.create()
                        .add("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .add("base64", "true")
                        .getParameterObject());
    }

    @Test
    public void checkFullInstantiationReset() {
        printJs
                .setShowModal(true)
                .setModalMessage("Modal Message")
                .setOnLoadingStart("window.alert('loading start'")
                .setOnLoadingEnd("window.alert('loading end'")
                .setFallbackPrintable("doc/fallback.pdf")
                .setOnPdfOpen("window.alert('PDF open')")
                .setOnPrintDialogClose("window.alert('print dialog close'")
                .reset();

        assertThat(printJs)
                .as("printable for base 64 PDF and its printable type")
                .hasToString(ParameterBuilder.create()
                        .add("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .add("base64", "true")
                        .getParameterObject());
    }
}
