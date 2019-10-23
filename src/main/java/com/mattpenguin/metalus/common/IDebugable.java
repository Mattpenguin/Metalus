package com.mattpenguin.metalus.common;

public interface IDebugable {
    default String getDebugInfo() {
        return "This Block/Item implements the IDebugable interface, but didn't override the getDebugInfo method. How silly!";
    }
}
