// runtime/runtime.c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h> // Necesario para strcmp


static int turtle_heading = 0;
// Estado para el color (0..9)
static int turtle_color = 0;


// ----- println -----
void println_i32(int x) {
    printf("%d\n", x);
}
void println_bool(_Bool b) {
    printf(b ? "true\n" : "false\n");
}
void println_cstr(const char* s) {
    if (s == NULL) {
        puts("(null)");
    } else {
        puts(s);
    }
}

// ----- turtle (stubs) -----
void turtle_avanza(int d)        { printf("la tortuga avanzó %d unidades\n", d); }
void turtle_giraDerecha(int deg) { printf("la tortuga giró a la derecha %d grados\n", deg); }
void turtle_giraIzquierda(int deg){ printf("la tortuga giró a la izquierda %d grados\n", deg); }
void turtle_centro(void)         { printf("tortuga al centro\n"); }
void turtle_ponX(int x)          { printf("tortuga X=%d\n", x); }
void turtle_ponY(int y)          { printf("tortuga Y=%d\n", y); }
void turtle_ponPos(int x,int y)  { printf("tortuga POS=(%d,%d)\n", x,y); }
void turtle_bajaLapiz(void)      { printf("baja lapiz\n"); }
void turtle_subeLapiz(void)      { printf("sube lapiz\n"); }


// Índices usados en la gramática:
// 0=negro, 1=azul, 2=rojo, 3=verde, 4=amarillo,
// 5=blanco, 6=naranja, 7=morado, 8=celeste, 9=magenta
void turtle_ponColorLapiz(int colorCode) {
    static const char* names[] = {
        "negro","azul","rojo","verde","amarillo",
        "blanco","naranja","morado","celeste","magenta"
    };

    turtle_color = colorCode;

    const char* name = "desconocido";
    if (colorCode >= 0 && colorCode < 10) {
        name = names[colorCode];
    }

    printf("color=%s (%d)\n", name, colorCode);
}

void turtle_espera(int ms)       { printf("espera %d ms\n", ms); }

// --- FUNCIÓN MODIFICADA ---
void turtle_oculta(void) {
    printf("oculta tortuga\n");
    fflush(stdout); // <<<--- AÑADIR: Forzar vaciado del buffer de salida
    // volatile int dummy = 0; // Ya no necesitamos el dummy con fflush
    // dummy++;
    return; // <<<--- AÑADIR: Retorno explícito (buena práctica)
}
// --- FIN FUNCIÓN MODIFICADA ---

void turtle_ponRumbo(int deg) {
    turtle_heading = deg;  // guarda el rumbo
    printf("pon rumbo = %d grados\n", deg);
}

void turtle_rumbo(void) {
    printf("rumbo actual = %d grados\n", turtle_heading);
}
void turtle_retrocede(int d) {
    printf("retrocede %d unidades\n", d);
}

// ----- random/azar -----
int runtime_azar(int max) {
    static int seeded = 0;
    if (!seeded) { srand((unsigned)time(NULL)); seeded = 1; }
    if (max <= 0) return 0;
    return rand() % max;
}

// ----- pow entero -----
int runtime_pow(int base, int exp) {
    int r = 1, b = base, e = exp;
    if (e < 0) return 0;
    while (e > 0) {
        if (e & 1) r *= b;
        b *= b;
        e >>= 1;
    }
    return r;
}

/* // main de prueba (sin cambios)
int main() { ... }
*/

// --- FUNCIÓN PRINCIPAL (si necesitas probar runtime.c directamente) ---
/* // Descomenta esto si quieres compilar y probar runtime.c solo
int main() {
    println_i32(123);
    println_bool(1);
    println_cstr("Hola Mundo");
    turtle_avanza(50);
    turtle_ponColorLapiz("rojo"); // Prueba con un string
    turtle_ponColorLapiz("azul");
    return 0;
}
*/