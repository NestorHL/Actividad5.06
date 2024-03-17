package prog.unidad05.gestionclientes.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import prog.unidad05.gestionclientes.core.Cliente;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class GestionClientesSwingApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionClientesSwingApp frame = new GestionClientesSwingApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public GestionClientesSwingApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 659, 460);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textField = new JTextField();
        textField.setBounds(10, 31, 623, 167);
        contentPane.add(textField);
        textField.setColumns(10);
        mostrarContenidoDelArchivo("Clientes.dat");
        
        JLabel lblNewLabel = new JLabel("Clientes");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 11, 76, 14);
        contentPane.add(lblNewLabel);
        
        JLabel lblNif = new JLabel("NIF:");
        lblNif.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNif.setBounds(10, 209, 76, 14);
        contentPane.add(lblNif);
        
        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblApellidos.setBounds(10, 247, 76, 14);
        contentPane.add(lblApellidos);
        
        JLabel lblNombres = new JLabel("Nombre:");
        lblNombres.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNombres.setBounds(164, 209, 76, 14);
        contentPane.add(lblNombres);
        
        JLabel lblEmpleados = new JLabel("Empleados:");
        lblEmpleados.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblEmpleados.setBounds(398, 209, 93, 14);
        contentPane.add(lblEmpleados);
        
        JLabel lblFacturas = new JLabel("Facturas:");
        lblFacturas.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFacturas.setBounds(398, 249, 76, 14);
        contentPane.add(lblFacturas);
        
        textField_1 = new JTextField();
        textField_1.setBounds(48, 208, 86, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        textField_2 = new JTextField();
        textField_2.setBounds(82, 246, 306, 20);
        contentPane.add(textField_2);
        textField_2.setColumns(10);
        
        textField_3 = new JTextField();
        textField_3.setBounds(231, 209, 157, 20);
        contentPane.add(textField_3);
        textField_3.setColumns(10);
        
        textField_4 = new JTextField();
        textField_4.setBounds(483, 208, 124, 20);
        contentPane.add(textField_4);
        textField_4.setColumns(10);
        
        textField_5 = new JTextField();
        textField_5.setBounds(468, 246, 139, 20);
        contentPane.add(textField_5);
        textField_5.setColumns(10);
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("¿Es nacional de la UE?");
        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        chckbxNewCheckBox.setBounds(10, 285, 200, 23);
        contentPane.add(chckbxNewCheckBox);
        
        JButton btnNewButton = new JButton("Nuevo");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                añadirCliente("Clientes.dat");
            }
        });
        btnNewButton.setBounds(10, 320, 100, 36);
        contentPane.add(btnNewButton);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEliminar.setBounds(192, 320, 100, 36);
        contentPane.add(btnEliminar);
        
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnActualizar.setBounds(360, 320, 114, 36);
        contentPane.add(btnActualizar);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSalir.setBounds(520, 320, 100, 36);
        contentPane.add(btnSalir);
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAceptar.setBounds(10, 367, 100, 36);
        contentPane.add(btnAceptar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCancelar.setBounds(192, 367, 100, 36);
        contentPane.add(btnCancelar);
    }
    
    private void mostrarContenidoDelArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea); 
            }
            textField.setText(contenido.toString()); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void añadirCliente(String nombreArchivo) {
        String dniCliente = textField_1.getText();
        String nombreCliente = textField_3.getText();
        String apellidoCliente = textField_2.getText();
        int empleadosCliente = Integer.parseInt(textField_4.getText());
        double facturacionCliente = Double.parseDouble(textField_5.getText());
        boolean nacionalidadCliente = false; // Por defecto no es de la UE
        JCheckBox chckbxNewCheckBox = new JCheckBox("¿Es nacional de la UE?");
        if (chckbxNewCheckBox.isSelected()) {
            nacionalidadCliente = true;
        }
        Cliente cliente = new Cliente(dniCliente, apellidoCliente, nombreCliente, empleadosCliente,
                facturacionCliente, nacionalidadCliente);
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            salida.println(cliente.getNif() + ": " + cliente.getApellidos() + ", " + cliente.getNombre());
            salida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
