package com.parqueo.pos_parqueo_web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.parqueo.pos_parqueo_web.entity.Caja;
import com.parqueo.pos_parqueo_web.entity.Cliente;
import com.parqueo.pos_parqueo_web.entity.Espacio;
import com.parqueo.pos_parqueo_web.entity.Usuario;
import com.parqueo.pos_parqueo_web.repository.CajaRepository;
import com.parqueo.pos_parqueo_web.repository.ClienteRepository;
import com.parqueo.pos_parqueo_web.repository.EspacioRepository;
import com.parqueo.pos_parqueo_web.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private CajaRepository cajaRepo;
    @Autowired
    private EspacioRepository espaciosRepo; 

    

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
        model.addAttribute("mensajeExito", "Caja abierta con éxito"); 
        return "ventas/registro";
    }

    @GetMapping("/ventas/registro")
    public String registroVenta() {
        return "ventas/registro";
    }

    @Autowired
    private ClienteRepository clienteRepo;

    @GetMapping("/clientes/lista")
    public String listaClientes(Model model) {
        //List<Cliente> clientes = clienteRepo.findAll();
        //model.addAttribute("clientes", clientes);
        return "clientes/lista";
    }

    @GetMapping("/clientes/nuevo")
    public String nuevoCliente() {
        return "clientes/nuevo";
    }

    @GetMapping("/espacios/seleccionar")
    public String seleccionarEspacio(Model model) {
        // Simulación de datos (puedes cambiar por datos reales después)
        List<Espacio> espacios = List.of(
            new Espacio(1, "Zona A"),
            new Espacio(2, "Zona B"),
            new Espacio(3, "Zona C")
        );
        model.addAttribute("espacios", espacios);
        return "espacios/seleccionar";
    }


    @GetMapping("/ventas/boleta")
    public void generarBoleta(HttpServletResponse response) throws IOException {
        // Simulación de datos del cliente
        Cliente cliente = new Cliente();
        cliente.setNombreCliente("Juan");
        cliente.setApellidoCliente("Barahona");
        cliente.setRucCliente("76354354");
        cliente.setRazonSCliente("Compra");

        // Configurar respuesta HTTP
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=boleta.pdf");

        // Crear PDF
        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Agregar contenido
        try {
            Image logo = new Image(ImageDataFactory.create("src/main/resources/static/images/logo.jpg"));
            logo.setTextAlignment(TextAlignment.CENTER);
            document.add(logo);
        } catch (Exception e) {
            document.add(new Paragraph("Logo no encontrado"));
        }

        // Encabezado de la empresa
        document.add(new Paragraph("Ferretería tusolutionweb tutos")
            .setTextAlignment(TextAlignment.CENTER)
            .setFontSize(14)
            .setBold());

        // Información de contacto
        document.add(new Paragraph(
            "tusolutionweb tutos - Celular: 95464564 - av. san francisco - Ruc: 20113322"
        )
            .setTextAlignment(TextAlignment.CENTER)
            .setFontSize(10));

        // Número de boleta
        document.add(new Paragraph("Boleta de venta: 000000003")
            .setTextAlignment(TextAlignment.CENTER)
            .setFontSize(10));

        document.add(new Paragraph(
            "Cliente: " + cliente.getNombreCliente() + " " + cliente.getApellidoCliente() + "\n" +
            "Ruc cliente: " + cliente.getRucCliente() + "\n" +
            "Razón social cliente: " + cliente.getRazonSCliente())
        );

        document.add(new Paragraph(
            "Valor operaciones gravadas: -19.0\n" +
            "Impuesto: 19.0\n" +
            "Importe total: 0\n" +
            "CERO 00/100 SOLES.")
        );

        document.close();
    }

    @GetMapping("/ventas/consultas-facturas")
    public String consultasFacturas() {
        return "ventas/consultas-facturas";
    }

    @PostMapping("/vaciar-base-de-datos")
    public String vaciarBaseDeDatos(Model model) {
        // Vaciar todas las tablas importantes
        cajaRepo.deleteAll();
        clienteRepo.deleteAll();
        espaciosRepo.deleteAll(); // Asegúrate de tener este repositorio
        usuarioRepo.deleteAll(); // Opcional: solo si quieres eliminar usuarios

        model.addAttribute("mensajeExito", "la base de datos se vació con éxito");
        return "dashboard"; // Redirige al dashboard
    }

    @GetMapping("/ventas/lista-cajas")
    public String listaCajas(Model model) {
        // No necesitas datos reales por ahora
        return "ventas/lista-cajas";
    }

    @GetMapping("/test-db")
    public String testDb(Model model) {
        model.addAttribute("clientes", clienteRepo.findAll());
        return "clientes/lista";
}

}