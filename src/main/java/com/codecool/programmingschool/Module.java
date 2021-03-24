package com.codecool.programmingschool;

public enum Module {
    ADVANCED (ProgrammingLanguage.JAVA_EE) {

        @Override
        public Module nextModule() {
            return null;
        }
    },


    OOP (ProgrammingLanguage.JAVA_SE) {

        @Override
        public Module nextModule() {
            return ADVANCED;
        }
    },


    WEB (ProgrammingLanguage.WEB) {

        @Override
        public Module nextModule() {
            return OOP;
        }
    },


    PB (ProgrammingLanguage.PYTHON) {
        @Override
        public Module nextModule() {
            return Module.WEB;
        }
    };


    public final ProgrammingLanguage language;

    Module(ProgrammingLanguage language) {
        this.language = language;
    }

    public abstract Module nextModule();

}
