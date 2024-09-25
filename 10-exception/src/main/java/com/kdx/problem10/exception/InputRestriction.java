package com.kdx.problem10.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputRestriction {
    private final String maxGrade;
    private final String minGrade;

    private InputRestriction(InputRestrictionBuilder builder) {
        this.maxGrade = builder.getMaxGrade();
        this.minGrade = builder.getMinGrade();
    }

    public String getMaxGrade() {
        return maxGrade;
    }

    public String getMinGrade() { return minGrade;}

    public static class InputRestrictionBuilder {
        private String maxGrade;
        private String minGrade;

        public static InputRestrictionBuilder builder() {
            return new InputRestrictionBuilder();
        }

        public InputRestrictionBuilder setMaxGrade(Integer maxGrade) {
            this.maxGrade = maxGrade.toString();
            return this;
        }

        public InputRestrictionBuilder setMinGrade(Integer minGrade) {
            this.minGrade = minGrade.toString();
            return this;
        }

        public InputRestriction build() {
            return new InputRestriction(this);
        }

        String getMaxGrade() {
            return maxGrade;
        }

        String getMinGrade() {
            return minGrade;
        }
    }
}
