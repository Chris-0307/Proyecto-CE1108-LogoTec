// runtime/runtime.c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// ----- println -----
void println_i32(int x) {
    printf("%d\n", x);
}
void println_bool(_Bool b) {
    printf(b ? "true\n" : "false\n");
}
void println_cstr(const char* s) {
    puts(s);
}

// ----- turtle (stubs por ahora) -----
void turtle_avanza(int d)        { printf("la tortuga avanzó %d unidades\n", d); }
void turtle_giraDerecha(int deg) { printf("la tortuga giró a la derecha %d grados\n", deg); }
void turtle_giraIzquierda(int deg){ printf("la tortuga giró a la izquierda %d grados\n", deg); }
void turtle_centro(void)         { printf("tortuga al centro\n"); }
void turtle_ponX(int x)          { printf("tortuga X=%d\n", x); }
void turtle_ponY(int y)          { printf("tortuga Y=%d\n", y); }
void turtle_ponPos(int x,int y)  { printf("tortuga POS=(%d,%d)\n", x,y); }
void turtle_bajaLapiz(void)      { printf("baja lapiz\n"); }
void turtle_subeLapiz(void)      { printf("sube lapiz\n"); }
void turtle_ponColorLapiz(int c) { printf("color=%d\n", c); }
void turtle_espera(int ms)       { printf("espera %d ms\n", ms); }

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
    if (e < 0) return 0; // fuera de alcance por ahora
    while (e > 0) {
        if (e & 1) r *= b;
        b *= b;
        e >>= 1;
    }
    return r;
}
