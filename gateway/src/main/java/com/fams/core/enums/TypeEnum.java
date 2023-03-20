package com.fams.core.enums;

/**
 * Long PC
 * 20/03/2023| 14:44 | 2023
 **/
public class TypeEnum {
    public enum Status {
        OPEN(1),
        CLOSE(0);
        private int code;
        Status(int code) {

        }

        public int getCode() {
            return code;
        }
    }

    public enum Process {
        NEW(1),
        DOING(2),
        DONE(3);
        private int code;

        Process(int code) {

        }

        public int getCode() {
            return code;
        }
    }
    public enum Note{
        PRIVATE,
        PUBLIC,
        PERSON
    }
    public enum Event{
        BET,
        BEER
    }
    public enum EventElementTypeEnum{
        BET
    }

}
