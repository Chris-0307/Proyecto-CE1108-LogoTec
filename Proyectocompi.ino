#include <SoftwareSerial.h>

// -----------------------
// Configuración Bluetooth
// -----------------------
SoftwareSerial BT(2, 3);  // RX, TX

// -----------------------
// Pines del L298N
// -----------------------
const int ENA = 5;   // PWM motor izquierdo
const int IN1 = 8;
const int IN2 = 9;

const int ENB = 6;   // PWM motor derecho
const int IN3 = 10;
const int IN4 = 11;

// Velocidades independientes (0-255)
int velocidadIzq = 160;  // motor izquierdo
int velocidadDer = 240;  // motor derecho

// ------------ Funciones de movimiento ------------

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

// -----------------------
// Setup
// -----------------------
void setup() {
  pinMode(ENA, OUTPUT);
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  pinMode(ENB, OUTPUT);
  pinMode(IN3, OUTPUT);
  pinMode(IN4, OUTPUT);

  Serial.begin(9600);
  BT.begin(9600);

  detenerCarro();

  Serial.println("======================================");
  Serial.println("   Carrito Bluetooth listo");
  Serial.println("   Comandos por BT:");
  Serial.println("   F=adelante, B=atras, L=izquierda,");
  Serial.println("   R=derecha, S=stop");
  Serial.println("======================================");
}

// -----------------------
// Loop principal
// -----------------------
void loop() {
  // Lectura desde Bluetooth
  if (BT.available()) {
    int n = BT.available();
    Serial.print("[BT] Bytes disponibles: ");
    Serial.println(n);

    char cmd = BT.read();

    Serial.print("[BT] Byte crudo: '");
    Serial.print(cmd);
    Serial.print("'  ASCII=");
    Serial.println((int)cmd);

    if (cmd == '\r' || cmd == '\n') {
      Serial.println("[BT] Salto de linea recibido, se ignora.");
      return;
    }

    // A mayúscula
    if (cmd >= 'a' && cmd <= 'z') {
      cmd = cmd - 'a' + 'A';
      Serial.print("[BT] Convertido a mayuscula: ");
      Serial.println(cmd);
    }

    Serial.print("[BT] Comando procesado: ");
    Serial.println(cmd);

    switch (cmd) {
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
      default:
        Serial.println("[BT] Comando desconocido. Se detiene el carro.");
        detenerCarro();
        break;
    }
  }

  // (Opcional) comandos desde el Serial Monitor:
  if (Serial.available()) {
    char c = Serial.read();

    Serial.print("[PC] Tecla desde Serial: '");
    Serial.print(c);
    Serial.print("' ASCII=");
    Serial.println((int)c);

    if (c == '\r' || c == '\n') {
      Serial.println("[PC] Salto de linea, se ignora.");
    } else {
      if (c >= 'a' && c <= 'z') {
        c = c - 'a' + 'A';
      }

      switch (c) {
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
      }
    }
  }
}
