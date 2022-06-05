package com.nchomework.netcrackerhw.parsing;

import java.util.UUID;

public final class UUIDParser {

    public static UUID parseUuid(String value) {
        if (value == null) {
            return null;
        }
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException ex) {
           return null;
        }
    }

    public static UUID parseUuidWithError(String value) {
        if (value == null) {
            return null;
        }
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
    }

}
