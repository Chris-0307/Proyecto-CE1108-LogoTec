package com.tallerantlr.simple.interprete;

import java.awt.Color;



/**
 * Mantiene el estado de la tortuga virtual para la simulación en el lienzo.
 */
public class TurtleState {

    // --- Coordenadas ---
    // (0,0) representa el centro lógico del lienzo.
    // X positivo es hacia la derecha, Y positivo es hacia arriba.
    private double x = 0.0;
    private double y = 0.0;

    // --- Ángulo ---
    // En grados. 0 = derecha, 90 = arriba, 180 = izquierda, 270 = abajo.
    private double angle = 0.0;

    // --- Lápiz ---
    private boolean penDown = false; // El lápiz empieza arriba
    private Color penColor = Color.BLACK; // Color inicial negro

    // --- Visibilidad ---
    private boolean visible = true; // La tortuga empieza visible

    // --- Constructor (valores por defecto) ---
    public TurtleState() {
        // Los valores iniciales ya están definidos arriba
    }

    // --- Métodos de acceso (Getters) ---
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    public boolean isPenDown() {
        return penDown;
    }

    public Color getPenColor() {
        return penColor;
    }

    public boolean isVisible() {
        return visible;
    }

    // --- Métodos de modificación (Setters) ---
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Establece la posición (x, y).
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Establece el ángulo en grados. Se normaliza a [0, 360).
     */
    public void setAngle(double angle) {
        // Normalizar el ángulo a 0 <= angle < 360
        this.angle = angle % 360.0;
        if (this.angle < 0) {
            this.angle += 360.0;
        }
    }

    /**
     * Gira la tortuga a la derecha por `degrees`.
     */
    public void turnRight(double degrees) {
        setAngle(this.angle - degrees); // Restar para girar a la derecha
    }

    /**
     * Gira la tortuga a la izquierda por `degrees`.
     */
    public void turnLeft(double degrees) {
        setAngle(this.angle + degrees); // Sumar para girar a la izquierda
    }

    public void setPenDown(boolean penDown) {
        this.penDown = penDown;
    }

    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Mueve la tortuga hacia adelante una distancia 'dist' en la dirección actual.
     * No dibuja, solo calcula la nueva posición.
     */
    public void moveForward(double dist) {
        // Convertir ángulo a radianes para Math.cos/sin
        double angleRad = Math.toRadians(this.angle);
        this.x += dist * Math.cos(angleRad);
        this.y += dist * Math.sin(angleRad); // Y positivo es arriba
    }

    /**
     * Reinicia el estado a los valores por defecto (centro, 0 grados, pen up, etc.).
     */
    public void reset() {
        this.x = 0.0;
        this.y = 0.0;
        this.angle = 0.0; // Apuntando a la derecha
        this.penDown = false;
        this.penColor = Color.BLACK;
        this.visible = true;
    }

    /**
     * Establece el estado inicial en la esquina superior izquierda
     * (Asumiendo un tamaño de lienzo lógico, por ejemplo -200 a 200).
     * Puedes ajustar estos valores según el tamaño de tu lienzo.
     */
    public void goToOriginTopLeft(double logicalWidth, double logicalHeight) {
        this.x = -logicalWidth / 2.0;
        this.y = logicalHeight / 2.0;
        // Podrías decidir si resetear el ángulo también, por ejemplo a 0 (derecha)
        // setAngle(0.0);
    }
}