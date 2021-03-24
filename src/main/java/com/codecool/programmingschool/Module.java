package com.codecool.programmingschool;

public enum Module {
    ADVANCED (ProgrammingLanguage.JAVA_EE) {

        @Override
        public Module nextModule() {
            return null;
        }

        @Override
        public String toString() {
            return String.format("Advanced Module (language %s)",language);
        }
    },


    OOP (ProgrammingLanguage.JAVA_SE) {

        @Override
        public Module nextModule() {
            return ADVANCED;
        }

        @Override
        public String toString() {
            return String.format("OOP Module (language %s)",language);
        }
    },


    WEB (ProgrammingLanguage.WEB) {

        @Override
        public Module nextModule() {
            return OOP;
        }

        @Override
        public String toString() {
            return String.format("Web Module (language %s)",language);
        }
    },


    PB (ProgrammingLanguage.PYTHON) {
        @Override
        public Module nextModule() {
            return Module.WEB;
        }

        @Override
        public String toString() {
            return String.format("Programming Basics Module (language %s)",language);
        }
    };


    public final ProgrammingLanguage language;

    Module(ProgrammingLanguage language) {
        this.language = language;
    }

    public abstract Module nextModule();

}
