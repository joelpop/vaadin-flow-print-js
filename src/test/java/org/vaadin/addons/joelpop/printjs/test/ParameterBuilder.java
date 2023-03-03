package org.vaadin.addons.joelpop.printjs.test;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParameterBuilder {

    private final StringBuilder stringBuilder;
    private String separator;

    private ParameterBuilder() {
        this.stringBuilder = new StringBuilder();
        this.separator = "";
    }

    public static ParameterBuilder create() {
        return new ParameterBuilder();
    }

    public ParameterBuilder add(String parameterName, String value) {
        stringBuilder
                .append(separator)
                .append(parameterName)
                .append(':')
                .append(Optional.ofNullable(value).orElse(""));
        separator = ",";

        return this;
    }

    public ParameterBuilder addWithQuotes(String parameterName, String value) {
        return add(parameterName, Optional.ofNullable(value).map(s -> "'" + s + "'").orElse(null));
    }

    public ParameterBuilder addArrayWithQuotes(String parameterName, String... values) {
        return add(parameterName, String.format("[%s]",
                Arrays.stream(values)
                        .map(v -> String.format("'%s'", v))
                        .collect(Collectors.joining(","))));
    }

    public String getParameters() {
        return stringBuilder.toString();
    }

    public String getParameterObject() {
        return "{" + getParameters() + "}";
    }
}
