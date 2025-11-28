package com.tallerantlr.simple.interprete.ide;

import com.fazecast.jSerialComm.SerialPort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarController {

    private SerialPort port;

    // Cola de comandos, un solo hilo para no mezclar órdenes
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    // === Calibración básica (AJUSTA ESTO EXPERIMENTALMENTE) ===
    private static final double MS_PER_UNIT = 20.0;
    private static final double MS_PER_DEG  = 20.0;

    public CarController(String portName, int baudRate) {
        port = SerialPort.getCommPort(portName);
        port.setComPortParameters(baudRate, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        port.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (!port.openPort()) {
            throw new RuntimeException("No se pudo abrir el puerto serie: " + portName);
        }
        System.out.println("[CAR] Conectado a " + portName);
    }

    public boolean isConnected() {
        return port != null && port.isOpen();
    }

    public void close() {
        // Apagar hilo de comandos
        executor.shutdownNow();
        if (port != null && port.isOpen()) {
            port.closePort();
        }
        System.out.println("[CAR] Conexión cerrada");
    }

    private void sendChar(char c) {
        if (!isConnected()) return;
        try {
            port.getOutputStream().write((byte) c);
            port.getOutputStream().flush();
            System.out.println("[CAR] TX: " + c);
        } catch (Exception e) {
            System.err.println("[CAR ERR] " + e.getMessage());
        }
    }

    /**
     * Versión NO BLOQUEANTE: mete el comando en la cola y
     * el hilo del executor se encarga de sleep + 'S'.
     */
    private void sendTimedCommand(char c, int durationMs) {
        executor.submit(() -> {
            if (!isConnected()) {
                System.out.println("[CAR] Ignorando comando, puerto no conectado");
                return;
            }
            sendChar(c);
            try {
                Thread.sleep(durationMs);
            } catch (InterruptedException ignored) { }
            sendChar('S'); // detener al final
        });
    }

    // === API de alto nivel, en términos de unidades y grados ===

    public void avanzarUnidades(double unidades) {
        int ms = (int) Math.round(unidades * MS_PER_UNIT);
        System.out.println("[CAR] avanzar " + unidades + " unidades ≈ " + ms + " ms");
        sendTimedCommand('F', ms);  // ya no bloquea la UI
    }

    public void retrocederUnidades(double unidades) {
        int ms = (int) Math.round(unidades * MS_PER_UNIT);
        System.out.println("[CAR] retroceder " + unidades + " unidades ≈ " + ms + " ms");
        sendTimedCommand('B', ms);
    }

    public void girarDerechaGrados(double grados) {
        int ms = (int) Math.round(grados * MS_PER_DEG);
        System.out.println("[CAR] girar derecha " + grados + "° ≈ " + ms + " ms");
        sendTimedCommand('R', ms);
    }

    public void girarIzquierdaGrados(double grados) {
        int ms = (int) Math.round(grados * MS_PER_DEG);
        System.out.println("[CAR] girar izquierda " + grados + "° ≈ " + ms + " ms");
        sendTimedCommand('L', ms);
    }

    public void esperaMs(int ms) {
        executor.submit(() -> {
            System.out.println("[CAR] espera " + ms + " ms");
            try {
                Thread.sleep(ms);
            } catch (InterruptedException ignored) { }
        });
    }

    public void detener() {
        executor.submit(() -> sendChar('S'));
    }


    // === API de color / lápiz usando NÚMEROS ===
    //  '1' = rojo + lápiz abajo
    //  '2' = azul + lápiz abajo
    //  '3' = negro + lápiz abajo
    //  '4' = lápiz arriba (mantiene color)
    //  '5' = lápiz abajo (mantiene color)

    public void setColorRojo() {
        executor.submit(() -> {
            System.out.println("[CAR] color ROJO (cmd '1')");
            sendChar('1');
        });
    }

    public void setColorAzul() {
        executor.submit(() -> {
            System.out.println("[CAR] color AZUL (cmd '2')");
            sendChar('2');
        });
    }

    public void setColorNegro() {
        executor.submit(() -> {
            System.out.println("[CAR] color NEGRO (cmd '3')");
            sendChar('3');
        });
    }

    public void lapizArriba() {
        executor.submit(() -> {
            System.out.println("[CAR] lápiz ARRIBA (cmd '4')");
            sendChar('4');
        });
    }

    public void lapizAbajo() {
        executor.submit(() -> {
            System.out.println("[CAR] lápiz ABAJO (cmd '5')");
            sendChar('5');
        });
    }

}
