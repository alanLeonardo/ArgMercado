package org.example.ArgMercadoTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.checkerframework.checker.units.qual.C;
import org.example.ArgMercado.Belly;
import org.example.ArgMercado.modelo.Categoria;
import org.example.ArgMercado.modelo.ImagenProducto;
import org.example.ArgMercado.modelo.Producto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.ArgMercado.modelo.Usuario;
import org.example.ArgMercado.servicio.CategoriaService;
import org.example.ArgMercado.servicio.ImagenProductoService;
import org.example.ArgMercado.servicio.ProductoService;
import org.example.ArgMercado.servicio.UsuarioService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static org.example.ArgMercado.modelo.Categoria.*;


public class ArgMercadoTest extends StepDefinitions {

     @Autowired
     private ProductoService productoService;

     @Autowired
     private ImagenProductoService imagenProductoService;

     @Autowired
     private UsuarioService usuarioService;

     @Autowired
     private CategoriaService categoriaService;

     private ImagenProducto imagenProducto1 ;
     private ImagenProducto imagenProducto2 ;
     private Producto producto;
     private Producto producto1;
     private Producto producto2;
     private Producto producto3;
     private Producto producto4;
     private Producto producto5;
     private String pathImagen1;
     private String pathImagen2;
     private File file1;
     private File file2;
     private List<Producto> productos;
     private Usuario usuario;
     private Categoria celulares;
     private Categoria celulares2;
     private Categoria celulares3;
     private Categoria muñeco;
     private Categoria deportesYFitness;
     private Categoria electrónica;


     @Before
     public void setUp() {
         this.imagenProducto1 = new ImagenProducto();
         this.imagenProducto2 = new ImagenProducto();
         this.producto = new Producto();
         this.producto1 = new Producto();
         this.producto2 = new Producto();
         this.producto3 = new Producto();
         this.producto4 = new Producto();
         this.producto5 = new Producto();
         this.productos = new ArrayList<>();
         this.usuario = new Usuario();
         this.celulares = new Categoria("Celulares");
         this.celulares2 = new Categoria("Celulares");
         this.celulares3 = new Categoria("Celulares");
         this.muñeco = new Categoria("Muñeco");
         this.deportesYFitness = new Categoria("DeportesYFitness");
         this.electrónica = new Categoria("Electrónica");
     }

     @After
     public void cleanup() {
         this.productoService.borrarTodo();
         this.imagenProductoService.borrarTodo();
         this.usuarioService.borrarTodo();
         this.categoriaService.borrarTodo();
     }

     //este test es para chequear que esta correctamente configurado el cucumber

    @Given("I have {int} cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {
        Belly belly = new Belly();
        belly.eat(cukes);
    }

    @When("I wait {int} hour")
    public void i_wait_hour(Integer int1) {

    }

    @Then("my belly should growl")
    public void my_belly_should_growl() {

    }

    //ArgMercado

    @Given("un producto")
    public void un_producto()  {
        this.producto1 = celular1();
    }


    @When("se publica")
    public void se_publica()  {
        this.productoService.guardar(this.producto1);
    }


    @Then("se verifica que este publicado")
    public void se_verifica_que_este_publicado() {

        Assert.assertEquals(this.producto1.getIdProducto(),this.productoService.recuperar(this.producto1.getIdProducto()).getIdProducto());
        Assert.assertEquals(this.producto1.getDescripcion(),this.productoService.recuperar(this.producto1.getIdProducto()).getDescripcion());
        Assert.assertEquals(this.producto1.getPrecio(),this.productoService.recuperar(this.producto1.getIdProducto()).getPrecio());
        Assert.assertEquals(this.producto1.getTitulo(),this.productoService.recuperar(this.producto1.getIdProducto()).getTitulo());
        Assert.assertEquals(this.producto1.getCategoria().getName(),this.productoService.recuperar(this.producto1.getIdProducto()).getCategoria().getName());
        Assert.assertEquals(this.producto1.getStock(),this.productoService.recuperar(this.producto1.getIdProducto()).getStock());

     }

    @Given("productos")
    public void productos() {
        this.producto1 = this.celular1();
        this.producto2 = this.celular2();
        this.producto2.setPrecio(3000.0);

        this.productoService.guardar(this.producto1);
        this.productoService.guardar(this.producto2);
     }

    @When("se ordenan los productos por menor precio")
    public void se_ordenan_los_productos_por_menor_precio() {
        this.productos = this.productoService.recuperarProductosDeMenorAMayor();
    }

    @Then("se verifica que los producto este ordenado de menor a mayor")
    public void se_verifica_que_los_producto_este_ordenado_de_menor_a_mayor() {
        List<Producto> productosOrdenado = new ArrayList<>();
        productosOrdenado.add(this.producto2);
        productosOrdenado.add(this.producto1);
        Arrays.sort(productosOrdenado.toArray());

        Assert.assertEquals(productosOrdenado.get(0).getIdProducto(),this.productos.get(0).getIdProducto());
    }

    @When("se ordenan los productos por mayor precio")
    public void se_ordenan_los_productos_por_mayor_precio() {
        this.productos = this.productoService.recuperarProductosDeMayorAMenor();
    }


    @Then("se verifica que los producto este ordenado de mayor a menor")
    public void se_verifica_que_los_producto_este_ordenado_de_mayor_a_menor() {
        List<Producto> productosOrdenado = new ArrayList<>();
        productosOrdenado.add(this.producto2);
        productosOrdenado.add(this.producto1);
        productosOrdenado.sort(Comparator.comparing(Producto::getPrecio).reversed());

        Assert.assertEquals(productosOrdenado.get(0).getIdProducto(),this.productos.get(0).getIdProducto());

    }

    @Given("{int} producto")
    public void producto(Integer int1) {
        this.producto1 = this.celular1();
        this.producto2 = this.celular2();
        this.producto3 = this.celular2();
        this.producto2.setPrecio(3000.0);
        this.producto3.setPrecio(10000.0);
        this.producto3.setCategoria(this.celulares3);
    }

    @When("se publican")
    public void se_publican() {
        this.productoService.guardar(this.producto1);
        this.productoService.guardar(this.producto2);
        this.productoService.guardar(this.producto3);
    }
    @Then("se verifica que este publicados")
    public void se_verifica_que_este_publicados() {
        Assert.assertEquals(this.producto1.getIdProducto(),this.productoService.recuperar(this.producto1.getIdProducto()).getIdProducto());
        Assert.assertEquals(this.producto1.getDescripcion(),this.productoService.recuperar(this.producto1.getIdProducto()).getDescripcion());
        Assert.assertEquals(this.producto1.getPrecio(),this.productoService.recuperar(this.producto1.getIdProducto()).getPrecio());
        Assert.assertEquals(this.producto1.getTitulo(),this.productoService.recuperar(this.producto1.getIdProducto()).getTitulo());
        Assert.assertEquals(this.producto1.getCategoria().getIdCategoria(),this.productoService.recuperar(this.producto1.getIdProducto()).getCategoria().getIdCategoria());
        Assert.assertEquals(this.producto1.getStock(),this.productoService.recuperar(this.producto1.getIdProducto()).getStock());

        Assert.assertEquals(this.producto2.getIdProducto(),this.productoService.recuperar(this.producto2.getIdProducto()).getIdProducto());
        Assert.assertEquals(this.producto2.getDescripcion(),this.productoService.recuperar(this.producto2.getIdProducto()).getDescripcion());
        Assert.assertEquals(this.producto2.getPrecio(),this.productoService.recuperar(this.producto2.getIdProducto()).getPrecio());
        Assert.assertEquals(this.producto2.getTitulo(),this.productoService.recuperar(this.producto2.getIdProducto()).getTitulo());
        Assert.assertEquals(this.producto2.getCategoria().getIdCategoria(),this.productoService.recuperar(this.producto2.getIdProducto()).getCategoria().getIdCategoria());
        Assert.assertEquals(this.producto2.getStock(),this.productoService.recuperar(this.producto2.getIdProducto()).getStock());

        Assert.assertEquals(this.producto3.getIdProducto(),this.productoService.recuperar(this.producto3.getIdProducto()).getIdProducto());
        Assert.assertEquals(this.producto3.getDescripcion(),this.productoService.recuperar(this.producto3.getIdProducto()).getDescripcion());
        Assert.assertEquals(this.producto3.getPrecio(),this.productoService.recuperar(this.producto3.getIdProducto()).getPrecio());
        Assert.assertEquals(this.producto3.getTitulo(),this.productoService.recuperar(this.producto3.getIdProducto()).getTitulo());
        Assert.assertEquals(this.producto3.getCategoria().getIdCategoria(),this.productoService.recuperar(this.producto3.getIdProducto()).getCategoria().getIdCategoria());
        Assert.assertEquals(this.producto3.getStock(),this.productoService.recuperar(this.producto3.getIdProducto()).getStock());

    }

    @Given("un producto y un usuario")
    public void un_producto_y_un_usuario() {
        this.producto1 = celular1();
        this.usuario = new Usuario("Leonardo Forte","leo@gmail.com","calleFalsa123");
    }

    @When("se comprar")
    public void se_comprar() {
         this.producto1.disminuirStock();

         this.usuario.comprar(this.producto1);

         this.producto1.setOwner(this.usuario);

         this.usuarioService.guardar(this.usuario);

         this.productoService.guardar(this.producto1);

    }

    @Then("se verifica que el producto fue comprado")
    public void se_verifica_que_el_producto_fue_comprado() {
         //se verifica que el producto fue comprado por el usuario y que dicho producto no se encuentre en la lista de productos a vender
        Usuario u = this.usuarioService.recuperar(this.usuario.getIdUsuario());
        Producto p =  this.productoService.recuperar(this.producto1.getIdProducto());
        Assert.assertEquals(this.producto1.getIdProducto(),u.getProducto(p.getIdProducto()).getIdProducto());
        Assert.assertEquals(0,u.getProducto(p.getIdProducto()).getStock());
        Assert.assertEquals(0,p.getStock());

    }

    @Given("un usuario")
    public void un_usuario() {
      this.usuario = new Usuario("Alan Martinez","alan@gmail.com","callefalsa123");
    }

    @When("se registra")
    public void se_registra() {
       this.usuarioService.guardar(this.usuario);
    }

    @Then("se verifica que se alla registrado correctamente")
    public void se_verifica_que_se_alla_registrado_correctamente() {
        Assert.assertEquals(this.usuario.getNombreCompleto(),this.usuarioService.recuperar(this.usuario.getIdUsuario()).getNombreCompleto());
        Assert.assertEquals(this.usuario.getEmail(),this.usuarioService.recuperar(this.usuario.getIdUsuario()).getEmail());
        Assert.assertEquals(this.usuario.getContraseña(),this.usuarioService.recuperar(this.usuario.getIdUsuario()).getContraseña());
    }

    @Given("un usuario y el sistema")
    public void un_usuario_y_el_sistema() {
        // Write code here that turns the phrase above into concrete actions
        this.usuario = new Usuario("Alan Martinez","alan@gmail.com","Pico1993xD");
    }

    @When("inicia seccion")
    public void inicia_seccion() {
        this.usuarioService.guardar(this.usuario);
    }

    @Then("se verifica que se pueda iniciar seccion correctamente")
    public void se_verifica_que_se_pueda_iniciar_seccion_correctamente() {
         Assert.assertTrue(this.usuarioService.login("alan@gmail.com","Pico1993xD"));

         //chequeo que argMercado que se recupero sea el correcto
         //Assert.assertEquals(this.usuario.getIdUsuario(),usuarioRecuperado.getIdUsuario());
         //Assert.assertEquals(this.usuario.getEmail(),argMercadoRecuperado.login(this.usuario.getEmail(),this.usuario.getContraseña()).getEmail());
         //Assert.assertEquals(this.usuario.getEmail(),argMercadoRecuperado.login(this.usuario.getEmail(),this.usuario.getContraseña()).getContraseña());
    }

    @Given("un producto y una lista de productos")
    public void un_producto_y_una_lista_de_productos() {
         this.producto = new Producto("Muñeco del hombre araña");

         this.producto1 = this.celular1();
         this.producto2 = this.celular2();
         this.producto3 = this.muñecoSpiderman();
         this.producto4 = this.bandaElastica();
         this.producto5 = this.smartTV();

         this.productos.add(this.producto1);
         this.productos.add(this.producto2);
         this.productos.add(this.producto3);
         this.productos.add(this.producto4);
         this.productos.add(this.producto5);
    }


    @When("busca un producto")
    public void busca_un_producto() {
         this.productoService.guardar(producto1);
         this.productoService.guardar(producto2);
         this.productoService.guardar(producto3);
         this.productoService.guardar(producto4);
         this.productoService.guardar(producto5);
    }

    @Then("se verifica que la busqueda devuelva el producto o productos del tipo buscado")
    public void se_verifica_que_la_busqueda_devuelva_el_producto_o_productos_del_tipo_buscado() {
         List<Producto> productos = this.productoService.recuperarTodo();

        Assert.assertEquals(this.producto1.getIdProducto(),productos.get(0).getIdProducto());
        Assert.assertEquals(this.producto2.getIdProducto(),productos.get(1).getIdProducto());
        Assert.assertEquals(this.producto3.getIdProducto(),productos.get(2).getIdProducto());
        Assert.assertEquals(this.producto4.getIdProducto(),productos.get(3).getIdProducto());
        Assert.assertEquals(this.producto5.getIdProducto(),productos.get(4).getIdProducto());

    }

    private Producto celular1() {
        Producto newProducto = new Producto();
        ImagenProducto imagen1 = new ImagenProducto();
        ImagenProducto imagen2 = new ImagenProducto();
        this.pathImagen1 = "/home/alan/Descargas/SamsungJ5.jpg";
        this.pathImagen2 = "/home/alan/Descargas/Samsung-Galaxy-J5-Prime-354.jpg";
        this.file1 = new File(pathImagen1);
        this.file2 = new File(pathImagen2);

        try {
            byte[] fileContent1 = Files.readAllBytes(file1.toPath());
            byte[] fileContent2 = Files.readAllBytes(file2.toPath());

            imagen1.setTipo("imagen/jgp");
            imagen1.setFoto(this.compressBytes(fileContent1));
            imagen1.setNombre(file1.getName());

            imagen2.setTipo("imagen/jgp");
            imagen2.setFoto(this.compressBytes(fileContent2));
            imagen2.setNombre(file2.getName());

            imagen1.setOwner(newProducto);
            imagen2.setOwner(newProducto);

        } catch(IOException e) {
            e.printStackTrace();
        }

        newProducto.setTitulo("Samsung J5 Prime");
        newProducto.setDescripcion("Samsung J5 Prime nuevo barato y facil de usar");
        newProducto.setCategoria(this.celulares);
        newProducto.setImagen(imagen1);
        newProducto.setImagen(imagen2);
        newProducto.setPrecio(7000.0);
        newProducto.setStock(1);

        imagen1.setOwner(newProducto);
        imagen2.setOwner(newProducto);

        return newProducto;
    }

    private Producto celular2() {
        Producto newProducto = new Producto();
        ImagenProducto imagen1 = new ImagenProducto();
        ImagenProducto imagen2 = new ImagenProducto();
        this.pathImagen1 = "/home/alan/Descargas/SamsungJ5.jpg";
        this.pathImagen2 = "/home/alan/Descargas/Samsung-Galaxy-J5-Prime-354.jpg";
        this.file1 = new File(pathImagen1);
        this.file2 = new File(pathImagen2);

        try {
            byte[] fileContent1 = Files.readAllBytes(file1.toPath());
            byte[] fileContent2 = Files.readAllBytes(file2.toPath());

            imagen1.setTipo("imagen/jgp");
            imagen1.setFoto(this.compressBytes(fileContent1));
            imagen1.setNombre(file1.getName());

            imagen2.setTipo("imagen/jgp");
            imagen2.setFoto(this.compressBytes(fileContent2));
            imagen2.setNombre(file2.getName());

            imagen1.setOwner(newProducto);
            imagen2.setOwner(newProducto);

        } catch (IOException e) {
            e.printStackTrace();
        }

        newProducto.setTitulo("Samsung J5 Prime");
        newProducto.setDescripcion("Samsung J5 Prime nuevo barato y facil de usar");
        newProducto.setCategoria(this.celulares2);
        newProducto.setImagen(imagen1);
        newProducto.setImagen(imagen2);
        newProducto.setPrecio(7000.0);
        newProducto.setStock(1);
        return newProducto;
    }

    private Producto muñecoSpiderman() {
        Producto newProducto = new Producto();
        ImagenProducto imagen1 = new ImagenProducto();
        ImagenProducto imagen2 = new ImagenProducto();
        this.pathImagen1 = "/home/alan/Descargas/shoppingMuñecoSpiderman.webp";
        this.pathImagen2 = "/home/alan/Descargas/shoppingMuñecoSpiderman2.webp";
        this.file1 = new File(pathImagen1);
        this.file2 = new File(pathImagen2);

        try {
            byte[] fileContent1 = Files.readAllBytes(file1.toPath());
            byte[] fileContent2 = Files.readAllBytes(file2.toPath());

            imagen1.setTipo("image/webp");
            imagen1.setFoto(this.compressBytes(fileContent1));
            imagen1.setNombre(file1.getName());

            imagen2.setTipo("image/webp");
            imagen2.setFoto(this.compressBytes(fileContent2));
            imagen2.setNombre(file2.getName());

            imagen1.setOwner(newProducto);
            imagen2.setOwner(newProducto);

        } catch (IOException e) {
            e.printStackTrace();
        }

        newProducto.setTitulo("Muñeco del hombre araña ");
        newProducto.setDescripcion("muñeco de hombre araña de un tamaño 29cm, totalmente articulado y con traje negro");
        newProducto.setCategoria(this.muñeco);
        newProducto.setImagen(imagen1);
        newProducto.setImagen(imagen2);
        newProducto.setPrecio(3000.0);
        newProducto.setStock(3);
        return newProducto;
    }

    private Producto bandaElastica() {
        Producto newProducto = new Producto();
        ImagenProducto imagen1 = new ImagenProducto();
        ImagenProducto imagen2 = new ImagenProducto();
        this.pathImagen1 = "/home/alan/Descargas/bandaElastica.webp";
        this.pathImagen2 = "/home/alan/Descargas/bandaElastica2.webp";
        this.file1 = new File(pathImagen1);
        this.file2 = new File(pathImagen2);

        try {
            byte[] fileContent1 = Files.readAllBytes(file1.toPath());
            byte[] fileContent2 = Files.readAllBytes(file2.toPath());

            imagen1.setTipo("image/webp");
            imagen1.setFoto(this.compressBytes(fileContent1));
            imagen1.setNombre(file1.getName());

            imagen2.setTipo("image/webp");
            imagen2.setFoto(this.compressBytes(fileContent2));
            imagen2.setNombre(file2.getName());

            imagen1.setOwner(newProducto);
            imagen2.setOwner(newProducto);

        } catch (IOException e) {
            e.printStackTrace();
        }

        newProducto.setTitulo("Banda elastica para entrenamiento");
        newProducto.setDescripcion("banda elastica enfundada con resistencia media slatix gym, nueva idea para entremiento intesos,y se puede usar tanto en tu casa o en el gym");
        newProducto.setCategoria(this.deportesYFitness);
        newProducto.setImagen(imagen1);
        newProducto.setImagen(imagen2);
        newProducto.setPrecio(3000.0);
        newProducto.setStock(3);
        return newProducto;
    }

    private Producto smartTV() {
        Producto newProducto = new Producto();
        ImagenProducto imagen1 = new ImagenProducto();
        ImagenProducto imagen2 = new ImagenProducto();
        this.pathImagen1 = "/home/alan/Descargas/smartv.webp";
        this.pathImagen2 = "/home/alan/Descargas/smartv2.webp";
        this.file1 = new File(pathImagen1);
        this.file2 = new File(pathImagen2);

        try {
            byte[] fileContent1 = Files.readAllBytes(file1.toPath());
            byte[] fileContent2 = Files.readAllBytes(file2.toPath());

            imagen1.setTipo("image/webp");
            imagen1.setFoto(this.compressBytes(fileContent1));
            imagen1.setNombre(file1.getName());

            imagen2.setTipo("image/webp");
            imagen2.setFoto(this.compressBytes(fileContent2));
            imagen2.setNombre(file2.getName());

            imagen1.setOwner(newProducto);
            imagen2.setOwner(newProducto);

        } catch (IOException e) {
            e.printStackTrace();
        }

        newProducto.setTitulo("Smart TV Hitachi CDH-LE554KSMART20 LED 4K 55");
        newProducto.setDescripcion("Con el Smart TV CDH-LE554KSMART20 vas a acceder a las aplicaciones en las que se encuentran tus contenidos favoritos. Además, podés navegar por Internet, interactuar en redes sociales y divertirte con videojuegos.");
        newProducto.setCategoria(this.electrónica);
        newProducto.setImagen(imagen1);
        newProducto.setImagen(imagen2);
        newProducto.setPrecio(3000.0);
        newProducto.setStock(3);
        return newProducto;
    }

    // compress the image bytes before storing it in the database
    private static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    private static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
            ioe.getStackTrace();
        } catch (DataFormatException e) {
            e.getStackTrace();
        }
        return outputStream.toByteArray();
    }

}
