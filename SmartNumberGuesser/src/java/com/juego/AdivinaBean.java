package com.juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "adivinaBean")
@SessionScoped
public class AdivinaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int MAX_VIDAS = 5;

    // Variables de configuración de la partida
    private String nivel = "BASICO"; 
    private int rangoMin;
    private int rangoMax;
    private int numeroSecreto;
    private int vidasRestantes;
    private boolean juegoTerminado;
    private List<Integer> intentosPartidaActual;
    
    // Entrada del usuario (String para interceptar basura antes de que rompa JSF)
    private String numeroUsuario;
    
    // Feedback de interfaz
    private String mensaje;
    private String feedbackCalor;
    private String feedbackCalorEstilo;

    // Métricas globales (Persisten entre partidas)
    private int partidasJugadas = 0;
    private int partidasGanadas = 0;
    private int intentosTotales = 0;
    private long totalTiempoMilisegundos = 0;
    private long inicioIntentoHora;

    // Modos especiales
    private boolean modoExperto = false;

    // Historial general
    private List<Partida> historialPartidas = new ArrayList<>();
    private int idPartidaContador = 1;

    @PostConstruct
    public void init() {
        nuevaPartida();
    }

    public void nuevaPartida() {
        // Configurar rangos según nivel
        switch (nivel) {
            case "INTERMEDIO":
                this.rangoMin = 100;
                this.rangoMax = 500;
                break;
            case "AVANZADO":
                this.rangoMin = 500;
                this.rangoMax = 1000;
                break;
            case "BASICO":
            default:
                this.rangoMin = 0;
                this.rangoMax = 99;
                break;
        }

        // Generar número secreto aleatorio en el rango inclusivo
        Random rand = new Random();
        this.numeroSecreto = rand.nextInt((rangoMax - rangoMin) + 1) + rangoMin;
        
        // Reset de estado de partida
        this.vidasRestantes = MAX_VIDAS;
        this.juegoTerminado = false;
        this.intentosPartidaActual = new ArrayList<>();
        this.numeroUsuario = "";
        this.feedbackCalor = "";
        this.feedbackCalorEstilo = "";
        this.mensaje = "¡Nueva partida iniciada! Introduce un número.";
        
        // Arranca el cronómetro para el primer intento
        this.inicioIntentoHora = System.currentTimeMillis();
    }

    public String procesarIntento() {
        if (juegoTerminado) {
            return null;
        }

        // Calcular el tiempo que le tomó este intento específico
        long tiempoEsteIntento = System.currentTimeMillis() - inicioIntentoHora;
        totalTiempoMilisegundos += tiempoEsteIntento;

        try {
            // 1. Validación de vacío
            if (numeroUsuario == null || numeroUsuario.trim().isEmpty()) {
                mensaje = "¡No puedes enviar respuestas vacías! Penalizado por despistado.";
                bajarVida();
                return null;
            }

            // 2. Validación de formato (Captura strings, letras, caracteres extraños)
            long numeroParseado;
            try {
                numeroParseado = Long.parseLong(numeroUsuario.trim());
            } catch (NumberFormatException e) {
                mensaje = "¡Eso ni siquiera es un número válido! Corazón menos por intentar romper el sistema.";
                bajarVida();
                return null;
            }

            // 3. Validación de rango (Evita desbordamientos y números absurdos)
            if (numeroParseado < rangoMin || numeroParseado > rangoMax) {
                mensaje = "¡Fuera de rango! El límite es de " + rangoMin + " a " + rangoMax + ". Castigado.";
                bajarVida();
                return null;
            }

            // Si pasa todos los filtros, es una jugada legal
            int intentoValido = (int) numeroParseado;
            intentosTotales++;
            intentosPartidaActual.add(intentoValido);

            // 4. Procesamiento del juego
            if (intentoValido == numeroSecreto) {
                mensaje = "¡BRUTAL! Has acertado el número secreto.";
                juegoTerminado = true;
                partidasGanadas++;
                partidasJugadas++;
                historialPartidas.add(0, new Partida(idPartidaContador++, nivel, intentosPartidaActual.size(), numeroSecreto, "GANADA"));
                feedbackCalor = "";
                feedbackCalorEstilo = "";
            } else {
                // Calcular proximidad (Termómetro)
                int distancia = Math.abs(numeroSecreto - intentoValido);
                if (distancia <= 5) {
                    feedbackCalor = "¡HIERVE! Estás ridículamente cerca.";
                    feedbackCalorEstilo = "MuyCaliente";
                } else if (distancia <= 15) {
                    feedbackCalor = "Caliente. Vas por buen camino.";
                    feedbackCalorEstilo = "Caliente";
                } else if (distancia <= 35) {
                    feedbackCalor = "Tibio. Ni frío ni calor.";
                    feedbackCalorEstilo = "Tibio";
                } else {
                    feedbackCalor = "Congelado. Estás lejísimos.";
                    feedbackCalorEstilo = "Frio";
                }

                // Pistas del modo normal / experto
                if (modoExperto) {
                    mensaje = "Fallaste. La IA te observa.";
                } else {
                    if (intentoValido < numeroSecreto) {
                        mensaje = "El número secreto es MAYOR que " + intentoValido + ".";
                    } else {
                        mensaje = "El número secreto es MENOR que " + intentoValido + ".";
                    }
                }
                
                bajarVida();
            }

        } finally {
            // Truco arcade: Limpiamos la caja de texto y reiniciamos el reloj para el próximo Enter directo
            this.numeroUsuario = "";
            this.inicioIntentoHora = System.currentTimeMillis();
        }

        return null;
    }

    private void bajarVida() {
        if (vidasRestantes > 0) {
            vidasRestantes--;
        }
        
        if (vidasRestantes == 0) {
            mensaje = "GAME OVER. El número secreto era el " + numeroSecreto + ".";
            juegoTerminado = true;
            partidasJugadas++;
            historialPartidas.add(0, new Partida(idPartidaContador++, nivel, intentosPartidaActual.size(), numeroSecreto, "PERDIDA"));
            feedbackCalor = "";
            feedbackCalorEstilo = "";
        }
    }

    // --- GETTERS Y SETTERS EXIGIDOS POR EL INDEX.XHTML ---
    
    public List<String> getListaCorazones() {
        List<String> corazones = new ArrayList<>();
        for (int i = 0; i < vidasRestantes; i++) {
            corazones.add("❤️");
        }
        return corazones;
    }

    public int getPorcentajeVidasBarra() {
        return (vidasRestantes * 100) / MAX_VIDAS;
    }

    public double getPorcentajeAciertos() {
        if (partidasJugadas == 0) return 0.0;
        return ((double) partidasGanadas / partidasJugadas) * 100.0;
    }

    public double getTiempoPromedioIntento() {
        if (intentosTotales == 0) return 0.0;
        return (double) (totalTiempoMilisegundos / 1000.0) / intentosTotales;
    }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public int getRangoMin() { return rangoMin; }
    public int getRangoMax() { return rangoMax; }
    
    public int getVidasRestantes() { return vidasRestantes; }
    public boolean isJuegoTerminado() { return juegoTerminado; }
    public List<Integer> getIntentosPartidaActual() { return intentosPartidaActual; }

    public String getNumeroUsuario() { return numeroUsuario; }
    public void setNumeroUsuario(String numeroUsuario) { this.numeroUsuario = numeroUsuario; }

    public String getMensaje() { return mensaje; }
    public String getFeedbackCalor() { return feedbackCalor; }
    public String getFeedbackCalorEstilo() { return feedbackCalorEstilo; }

    public int getPartidasJugadas() { return partidasJugadas; }
    public int getIntentosTotales() { return intentosTotales; }
    public boolean isModoExperto() { return modoExperto; }
    public void setModoExperto(boolean modoExperto) { this.modoExperto = modoExperto; }

    public List<Partida> getHistorialPartidas() { return historialPartidas; }

    // --- ESTRUCTURA INTERNA DE REGISTRO DE PARTIDAS ---
    public static class Partida implements Serializable {
        private int id;
        private String modoNivel;
        private int intentos;
        private int numSecreto;
        private String resultado;

        public Partida(int id, String modoNivel, int intentos, int numSecreto, String resultado) {
            this.id = id;
            this.modoNivel = modoNivel;
            this.intentos = intentos;
            this.numSecreto = numSecreto;
            this.resultado = resultado;
        }

        public int getId() { return id; }
        public String getModoNivel() { return modoNivel; }
        public int getIntentos() { return intentos; }
        public int getNumSecreto() { return numSecreto; }
        public String getResultado() { return resultado; }
    }
}