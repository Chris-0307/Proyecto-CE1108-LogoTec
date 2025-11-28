#include <SoftwareSerial.h>
#include <Servo.h>

// ============================
//  Bluetooth (HC-05)
// ============================
SoftwareSerial BT(2, 3);  // RX, TX (HC-05)

// ============================
//  Pines del L298N (motores)
// ============================
const int ENA = 5;   // PWM motor izquierdo
const int IN1 = 8;
const int IN2 = 9;

const int ENB = 6;   // PWM motor derecho
const int IN3 = 10;
const int IN4 = 11;

// Velocidades independientes (0-255)
int velocidadIzq = 140;  // motor izquierdo
int velocidadDer = 210;  // motor derecho

// ============================
//  Servo de colores / lápiz
// ============================
Servo servoColor;
const int SERVO_PIN = 7;  // Pin del servo

// ⚠️ Ajusta estos ángulos según tu montaje:
// 0°   → marcador ROJO abajo
// 90°  → marcador AZUL abajo
// 180° → marcador NEGRO abajo
// ANG_UP → ángulo donde NINGÚN marcador toca el papel.
const int ANG_ROJO  = 0;     // Rojo abajo
const int ANG_AZUL  = 90;    // Azul abajo
const int ANG_NEGRO = 180;   // Negro abajo
const int ANG_UP    = 45;    // Lápiz arriba (AJUSTAR A MANO)

// ============================
//  Estados de color y lápiz
// ============================
enum Color {
  COLOR_NONE,
  COLOR_ROJO,
  COLOR_AZUL,
  COLOR_NEGRO
};

enum PenState {
  PEN_UP,
  PEN_DOWN
};

// Arrancamos con AZUL seleccionado pero lápiz ARRIBA
Color   colorActual  = COLOR_AZUL;
PenState lapizEstado = PEN_UP;

// ============================
//  Funciones de movimiento
// ============================

void detenerCarro() {
  analogWrite(ENA, 0);
  analogWrite(ENB, 0);

  digitalWrite(IN1, LOW);
  digitalWrite(IN2, LOW);
  digitalWrite(IN3, LOW);
  digitalWrite(IN4, LOW);

  Serial.println("[ACCION] detenerCarro()");
}

void avanzar() {
  analogWrite(ENA, velocidadIzq);
  analogWrite(ENB, velocidadDer);

  digitalWrite(IN1, HIGH);
  digitalWrite(IN2, LOW);

  digitalWrite(IN3, HIGH);
  digitalWrite(IN4, LOW);

  Serial.print("[ACCION] avanzar()  velIzq=");
  Serial.print(velocidadIzq);
  Serial.print("  velDer=");
  Serial.println(velocidadDer);
}

void retroceder() {
  analogWrite(ENA, velocidadIzq);
  analogWrite(ENB, velocidadDer);

  digitalWrite(IN1, LOW);
  digitalWrite(IN2, HIGH);

  digitalWrite(IN3, LOW);
  digitalWrite(IN4, HIGH);

  Serial.print("[ACCION] retroceder()  velIzq=");
  Serial.print(velocidadIzq);
  Serial.print("  velDer=");
  Serial.println(velocidadDer);
}

void girarIzquierda() {
  // Giro sobre el eje:
  //  - Motor izquierdo HACIA ATRÁS
  //  - Motor derecho HACIA ADELANTE
  analogWrite(ENA, velocidadIzq);
  analogWrite(ENB, velocidadDer);

  // Izquierdo atrás
  digitalWrite(IN1, LOW);
  digitalWrite(IN2, HIGH);

  // Derecho adelante
  digitalWrite(IN3, HIGH);
  digitalWrite(IN4, LOW);

  Serial.println("[ACCION] girarIzquierda (pivot)");
}

void girarDerecha() {
  // Giro sobre el eje:
  //  - Motor izquierdo HACIA ADELANTE
  //  - Motor derecho HACIA ATRÁS
  analogWrite(ENA, velocidadIzq);
  analogWrite(ENB, velocidadDer);

  // Izquierdo adelante
  digitalWrite(IN1, HIGH);
  digitalWrite(IN2, LOW);

  // Derecho atrás
  digitalWrite(IN3, LOW);
  digitalWrite(IN4, HIGH);

  Serial.println("[ACCION] girarDerecha (pivot)");
}

// ============================
//  Servo: helpers
// ============================

// Devuelve el ángulo que debe tener el servo
int angleFor(Color c, PenState p) {
  // Si el lápiz está arriba o no hay color, usamos ANG_UP
  if (p == PEN_UP || c == COLOR_NONE) {
    return ANG_UP;
  }

  // Lápiz abajo: elegimos ángulo según color
  switch (c) {
    case COLOR_ROJO:  return ANG_ROJO;
    case COLOR_AZUL:  return ANG_AZUL;
    case COLOR_NEGRO: return ANG_NEGRO;
    default:          return ANG_UP;
  }
}

void aplicarServo() {
  static int lastAngle = -1000;
  int ang = angleFor(colorActual, lapizEstado);

  if (ang != lastAngle) {
    servoColor.write(ang);
    lastAngle = ang;
    Serial.print("[SERVO] angle = ");
    Serial.println(ang);
  }
}

// Cambiar color actual (pero respetar estado del lápiz)
void setColor(Color c) {
  colorActual = c;

  Serial.print("[COLOR] Seleccionado: ");
  switch (c) {
    case COLOR_ROJO:  Serial.println("ROJO");  break;
    case COLOR_AZUL:  Serial.println("AZUL");  break;
    case COLOR_NEGRO: Serial.println("NEGRO"); break;
    default:          Serial.println("NINGUNO"); break;
  }

  aplicarServo();
}

// Sube o baja lápiz manteniendo color
void setLapiz(PenState p) {
  lapizEstado = p;
  if (p == PEN_UP) {
    Serial.println("[LAPIZ] ARRIBA");
  } else {
    Serial.println("[LAPIZ] ABAJO");
  }
  aplicarServo();
}

// ============================
//  Parser de comandos
// ============================
//
//  F = forward
//  B = backward
//  L = left
//  R = right
//  S = stop
//
//  '1' = seleccionar ROJO (no toca lápiz)
//  '2' = seleccionar AZUL
//  '3' = seleccionar NEGRO
//  '4' = lápiz ARRIBA
//  '5' = lápiz ABAJO
//
void handleCommandChar(char raw) {
  // Ignorar saltos de línea
  if (raw == '\r' || raw == '\n') return;

  char c = raw;
  // Pasar letras a mayúsculas
  if (c >= 'a' && c <= 'z') {
    c = c - 'a' + 'A';
  }

  Serial.print("[CMD] Recibido: ");
  Serial.println(c);

  switch (c) {
    // ===== Movimiento un carácter =====
    case 'F':
      avanzar();
      break;
    case 'B':
      retroceder();
      break;
    case 'L':
      girarIzquierda();
      break;
    case 'R':
      girarDerecha();
      break;
    case 'S':
      detenerCarro();
      break;

    // ===== Selección de color =====
    case '1':   // ROJO
      setColor(COLOR_ROJO);
      break;
    case '2':   // AZUL
      setColor(COLOR_AZUL);
      break;
    case '3':   // NEGRO
      setColor(COLOR_NEGRO);
      break;

    // ===== Control de lápiz =====
    case '4':   // Lápiz arriba
      setLapiz(PEN_UP);
      break;
    case '5':   // Lápiz abajo
      setLapiz(PEN_DOWN);
      break;

    default:
      Serial.println("[WARN] Comando desconocido -> STOP por seguridad");
      detenerCarro();
      break;
  }
}

// ============================
//  Setup
// ============================
void setup() {
  pinMode(ENA, OUTPUT);
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  pinMode(ENB, OUTPUT);
  pinMode(IN3, OUTPUT);
  pinMode(IN4, OUTPUT);

  Serial.begin(9600);
  BT.begin(9600);

  servoColor.attach(SERVO_PIN);

  detenerCarro();

  Serial.println("======================================");
  Serial.println("   Carrito Plotter Bluetooth listo");
  Serial.println("   Comandos de movimiento (1 char):");
  Serial.println("     F=adelante, B=atras, L=izq, R=der, S=stop");
  Serial.println("   Comandos de color (1 char):");
  Serial.println("     '1' = rojo");
  Serial.println("     '2' = azul");
  Serial.println("     '3' = negro");
  Serial.println("   Comandos de lápiz (1 char):");
  Serial.println("     '4' = subir lapiz");
  Serial.println("     '5' = bajar lapiz");
  Serial.println("======================================");

  // Estado inicial: color azul seleccionado, lápiz arriba
  aplicarServo();
}

// ============================
//  Loop principal
// ============================
void loop() {
  // Lectura desde Bluetooth (HC-05)
  if (BT.available()) {
    char cmd = BT.read();
    Serial.print("[BT] ");
    handleCommandChar(cmd);
  }

  // Lectura desde Serial (USB) para debug
  if (Serial.available()) {
    char cmd = Serial.read();
    Serial.print("[PC] ");
    handleCommandChar(cmd);
  }

  // Sin delay: todo es reactivo
}
