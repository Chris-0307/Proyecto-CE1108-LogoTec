package com.tallerantlr.simple.interprete.ide;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class ConsoleOutputStream extends OutputStream {
    private final JTextArea area;
    private final boolean isErr;
    private final StringBuilder buf = new StringBuilder();

    public ConsoleOutputStream(JTextArea area, boolean isErr) {
        this.area = area;
        this.isErr = isErr;
    }

    @Override public void write(int b) throws IOException {
        char c = (char) b;
        buf.append(c);
        if (c == '\n') flushLine();
    }

    @Override public void flush() {
        if (buf.length() > 0) flushLine();
    }

    private void flushLine() {
        final String line = (isErr ? "[ERR] " : "") + buf.toString();
        buf.setLength(0);
        SwingUtilities.invokeLater(() -> area.append(line));
    }
}
