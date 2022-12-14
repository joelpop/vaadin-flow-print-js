package org.vaadin.joelpop.addon.printjs.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.vaadin.joelpop.addon.printjs.PrintJs;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class JsonTest {
    public static final String PRINTABLE = "[\n" +
            "    {\n" +
            "       name: 'John Doe',\n" +
            "       email: 'john@doe.com',\n" +
            "       phone: '111-111-1111'\n" +
            "    },\n" +
            "    {\n" +
            "       name: 'Barry Allen',\n" +
            "       email: 'barry@flash.com',\n" +
            "       phone: '222-222-2222'\n" +
            "    },\n" +
            "    {\n" +
            "       name: 'Cool Dude',\n" +
            "       email: 'cool@dude.com',\n" +
            "       phone: '333-333-3333'\n" +
            "    }\n" +
            " ]";
    public static final String PRINTABLE_TYPE = "json";
    public static final Supplier<PrintJs> INSTANTIATOR = () -> PrintJs.fromJsonData(PRINTABLE);

    private PrintJs printJs;

    @BeforeMethod
    private void instantiate() {
        printJs = INSTANTIATOR.get();
    }

    @Test
    public void checkInstantiation() {
        assertThat(printJs)
                .as("printable for JSON and its printable type")
                .hasToString(ParameterBuilder.create()
                        .add("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .getParameterObject());
    }

    @Test
    public void checkInstantiationReset() {
        printJs.reset();

        assertThat(printJs)
                .as("printable for JSON and its printable type")
                .hasToString(ParameterBuilder.create()
                        .add("printable", PRINTABLE)
                        .addWithQuotes("type", PRINTABLE_TYPE)
                        .getParameterObject());
    }

    @Test
    public void checkFullInstantiationReset() {
        printJs
                .setHeaderText("Header Text")
                .setHeaderStyle("Modal Message")
                .setMaxWidth(1000)
                .setJsonProperties("JSON Properties")
                .setJsonGridHeaderStyle("JSON Grid Header Style")
                .setJsonGridStyle("JSON Grid Style")
                .setJsonRepeatTableHeader(true)
                .setDocumentTitle("Document Title")
                .setOnPrintDialogClose("window.alert('print dialog close')")
                .reset();

        assertThat(printJs)
                .as("printable for JSON and its printable type")
                .hasToString(ParameterBuilder.create()
                        .add("printable", PRINTABLE)
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
    public void checkJsonProperties() {
        assertThat(printJs.getJsonProperties())
                .as("jsonProperties default")
                .isNull();
        assertThat(printJs.toString())
                .as("jsonProperties default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("properties", null)
                        .getParameters());

        printJs.setJsonProperties("name", "address", "phone");
        assertThat(printJs.getJsonProperties())
                .as("jsonProperties set")
                .containsOnly("name", "address", "phone");
        assertThat(printJs.toString())
                .as("jsonProperties set parameter")
                .contains(ParameterBuilder.create()
                        .addArrayWithQuotes("properties", "name", "address", "phone")
                        .getParameters());

        printJs.setJsonProperties();
        assertThat(printJs.getJsonProperties())
                .as("jsonProperties reset")
                .isNull();
        assertThat(printJs.toString())
                .as("jsonProperties reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("properties", null)
                        .getParameters());
    }

    @Test
    public void checkJsonGridHeaderStyle() {
        assertThat(printJs.getJsonGridHeaderStyle())
                .as("gridHeaderStyle default")
                .isEqualTo("font-weight: bold;");
        assertThat(printJs.toString())
                .as("gridHeaderStyle default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("gridHeaderStyle", null)
                        .getParameters());

        printJs.setJsonGridHeaderStyle("font-size: 16pt;");
        assertThat(printJs.getJsonGridHeaderStyle())
                .as("gridHeaderStyle set")
                .isEqualTo("font-size: 16pt;");
        assertThat(printJs.toString())
                .as("gridHeaderStyle set parameter")
                .contains(ParameterBuilder.create()
                        .addWithQuotes("gridHeaderStyle", "font-size: 16pt;")
                        .getParameters());

        printJs.setJsonGridHeaderStyle(null);
        assertThat(printJs.getJsonGridHeaderStyle())
                .as("gridHeaderStyle reset")
                .isEqualTo("font-weight: bold;");
        assertThat(printJs.toString())
                .as("gridHeaderStyle reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("gridHeaderStyle", null)
                        .getParameters());
    }

    @Test
    public void checkJsonGridStyle() {
        assertThat(printJs.getJsonGridStyle())
                .as("gridStyle default")
                .isEqualTo("border: 1px solid lightgray; margin-bottom: -1px;");
        assertThat(printJs.toString())
                .as("gridStyle default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("gridStyle", null)
                        .getParameters());

        printJs.setJsonGridStyle("background-color: black; color: white;");
        assertThat(printJs.getJsonGridStyle())
                .as("gridStyle set")
                .isEqualTo("background-color: black; color: white;");
        assertThat(printJs.toString())
                .as("gridStyle set parameter")
                .contains(ParameterBuilder.create()
                        .addWithQuotes("gridStyle", "background-color: black; color: white;")
                        .getParameters());

        printJs.setJsonGridStyle(null);
        assertThat(printJs.getJsonGridStyle())
                .as("gridStyle reset")
                .isEqualTo("border: 1px solid lightgray; margin-bottom: -1px;");
        assertThat(printJs.toString())
                .as("gridStyle reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .addWithQuotes("gridStyle", null)
                        .getParameters());
    }

    @Test
    public void checkJsonRepeatTableHeader() {
        assertThat(printJs.isJsonRepeatTableHeader())
                .as("repeatTableHeader default")
                .isTrue();
        assertThat(printJs.toString())
                .as("repeatTableHeader default parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("repeatTableHeader", null)
                        .getParameters());

        printJs.setJsonRepeatTableHeader(true);
        assertThat(printJs.isJsonRepeatTableHeader())
                .as("repeatTableHeader set")
                .isTrue();
        assertThat(printJs.toString())
                .as("repeatTableHeader set parameter")
                .contains(ParameterBuilder.create()
                        .add("repeatTableHeader", "true")
                        .getParameters());

        printJs.setJsonRepeatTableHeader(false);
        assertThat(printJs.isJsonRepeatTableHeader())
                .as("repeatTableHeader set")
                .isFalse();
        assertThat(printJs.toString())
                .as("repeatTableHeader set parameter")
                .contains(ParameterBuilder.create()
                        .add("repeatTableHeader", "false")
                        .getParameters());

        printJs.resetJsonRepeatTableHeader();
        assertThat(printJs.isJsonRepeatTableHeader())
                .as("repeatTableHeader reset")
                .isTrue();
        assertThat(printJs.toString())
                .as("repeatTableHeader reset parameter")
                .doesNotContain(ParameterBuilder.create()
                        .add("repeatTableHeader", null)
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
                .as("css is not a valid parameter for JSON")
                .isThrownBy(() -> printJs.getCssUrls());

        assertThatIllegalArgumentException()
                .as("style is not a valid parameter for JSON")
                .isThrownBy(() -> printJs.getStyle());

        assertThatIllegalArgumentException()
                .as("scanStyles is not a valid parameter for JSON")
                .isThrownBy(() -> printJs.isScanStyles());

        assertThatIllegalArgumentException()
                .as("targetStyle is not a valid parameter for JSON")
                .isThrownBy(() -> printJs.getTargetStyles());

        assertThatIllegalArgumentException()
                .as("targetStyles is not a valid parameter for JSON")
                .isThrownBy(() -> printJs.getTargetStylePatterns());

        assertThatIllegalArgumentException()
                .as("ignoreElements is not a valid parameter for JSON")
                .isThrownBy(() -> printJs.getIgnoreElements());

        assertThatIllegalArgumentException()
                .as("showModal is not a valid parameter for JSON")
                .isThrownBy(() -> printJs.isShowModal());

        assertThatIllegalArgumentException()
                .as("modalMessage is not a valid parameter for JSON")
                .isThrownBy(() -> printJs.getModalMessage());

        assertThatIllegalArgumentException()
                .as("onLoadingStart is not a valid parameter for JSON")
                .isThrownBy(() -> printJs.getOnLoadingStart());

        assertThatIllegalArgumentException()
                .as("onLoadingEnd is not a valid parameter for JSON")
                .isThrownBy(() -> printJs.getOnLoadingEnd());

        assertThatIllegalArgumentException()
                .as("fallbackPrintable is not a valid parameter for JSON")
                .isThrownBy(() -> printJs.getFallbackPrintable());

        assertThatIllegalArgumentException()
                .as("onPdfOpen is not a valid parameter for HTML")
                .isThrownBy(() -> printJs.getOnPdfOpen());
    }
}
