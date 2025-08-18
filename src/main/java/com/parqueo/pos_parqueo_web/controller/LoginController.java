package com.parqueo.pos_parqueo_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parqueo.pos_parqueo_web.entity.Caja;
import com.parqueo.pos_parqueo_web.entity.Usuario;
import com.parqueo.pos_parqueo_web.repository.CajaRepository;
import com.parqueo.pos_parqueo_web.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private CajaRepository cajaRepo;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String usuario, @RequestParam String pasword, Model model) {
        Usuario user = usuarioRepo.findByUsuario(usuario).orElse(null);

        if (user != null && user.getPasword().equals(pasword)) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // --- Rutas para Clientes ---
    @GetMapping("/clientes")
    public String clientes() {
        return "clientes/lista";
    }

    // --- Rutas para Usuarios ---
    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuarios/lista";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario() {
        return "usuarios/nuevo";
    }

    @GetMapping("/usuarios/editar")
    public String editarUsuario() {
        return "usuarios/editar";
    }

    @GetMapping("/usuarios/cambiar-password")
    public String cambiarPassword() {
        return "usuarios/cambiar-password";
    }

    // --- Rutas para Espacios ---
    @GetMapping("/espacios")
    public String espacios() {
        return "espacios/lista";
    }

    @GetMapping("/espacios/nuevo")
    public String nuevoEspacio() {
        return "espacios/nuevo";
    }

    @GetMapping("/espacios/editar")
    public String editarEspacio() {
        return "espacios/editar";
    }

    @GetMapping("/espacios/liberar")
    public String liberarEspacio() {
        return "espacios/liberar";
    }

    // --- Rutas para Más ---
    @GetMapping("/acerca-de")
    public String acercaDe() {
        return "acerca-de";
    }

    @GetMapping("/configuracion")
    public String configuracion() {
        return "configuracion";
    }

    // --- Ruta para Ventas (Abrir Caja) ---
    @GetMapping("/ventas/abrir-caja")
    public String mostrarFormularioAbrirCaja(Model model) {
        return "ventas/abrir-caja"; // Muestra el formulario
    }

    @PostMapping("/ventas/abrir-caja")
    public String abrirCaja(@RequestParam Float monto, Model model) {
        Caja caja = new Caja();
        caja.setFecha(new java.sql.Date(System.currentTimeMillis()));
        caja.setMonto(monto);
        caja.setEstado("abierta");

        cajaRepo.save(caja);

        // Añadir mensaje para mostrar en JavaScript
        model.addAttribute("montoApertura", monto);
        model.addAttribute("mensaje", "Caja abierta con éxito"); 
        return "ventas/registro";
    }

    @GetMapping("/ventas/registro")
    public String registroVenta() {
        return "ventas/registro";
    }
}