package it.unisa.bdsir_takearound.framework;

import it.unisa.bdsir_takearound.framework.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
