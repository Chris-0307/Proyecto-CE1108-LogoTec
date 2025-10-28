// runtime/runtime.c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h> // Necesario para strcmp

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

void turtle_ponColorLapiz(const char* colorName) {
    if (colorName != NULL) {
        printf("color=%s\n", colorName);
    } else {
        printf("color=null\n");
    }
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
    printf("pon rumbo = %d grados\n", deg);
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