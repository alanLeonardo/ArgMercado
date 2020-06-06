package org.example.ArgMercadoTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.ArgMercado.Belly;
import org.example.ArgMercado.modelo.ImagenProducto;
import org.example.ArgMercado.modelo.Producto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.ArgMercado.servicio.ImagenProductoService;
import org.example.ArgMercado.servicio.ProductoService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static org.example.ArgMercado.enums.Categoria.Celulares;


public class ArgMercadoTest extends StepDefinitions {

     @Autowired
     private ProductoService productoService;

     @Autowired
     private ImagenProductoService imagenProductoService;

     private ImagenProducto imagenProducto1 ;
     private ImagenProducto imagenProducto2 ;
     private Producto producto1 ;
     private Producto producto2 ;
     private String pathImagen1 ;
     private String pathImagen2 ;
     private File file1;
     private File file2;
     private List<Producto> productosOrdenadosDeMenorAMayor;

     @Before
     public void setUp() {
         this.imagenProducto1 = new ImagenProducto();
         this.imagenProducto2 = new ImagenProducto();
         this.producto1 = new Producto();
         this.producto2 = new Producto();
         this.pathImagen1 = "/home/alan/Escritorio/samsung-galaxy-j5-prime3.jpg";
         this.pathImagen2 = "/home/alan/Escritorio/j5-prime.jpg";
         this.file1 = new File(pathImagen1);
         this.file2 = new File(pathImagen2);
         this.productosOrdenadosDeMenorAMayor = new ArrayList<>();
     }

     @After
     public void cleanup() {
         this.productoService.borrarTodo();
         this.imagenProductoService.borrarTodo();
     }

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
        this.producto1 = newProducto1();
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
        Assert.assertEquals(this.producto1.getCategoria(),this.productoService.recuperar(this.producto1.getIdProducto()).getCategoria());
        Assert.assertEquals(this.producto1.getStock(),this.productoService.recuperar(this.producto1.getIdProducto()).getStock());

     }

    @Given("productos")
    public void productos() {
        this.producto1 = this.newProducto1();
        this.producto2 = this.newProducto2();
        this.producto2.setPrecio(3000.0);

        this.productoService.guardar(this.producto1);
        this.productoService.guardar(this.producto2);
     }

    @When("se ordenan los productos por menor precio")
    public void se_ordenan_los_productos_por_menor_precio() {
        this.productosOrdenadosDeMenorAMayor = this.productoService.recuperarProductosDeMenorAMayor();
    }

    @Then("se verifica que los producto este ordenado de menor a mayor")
    public void se_verifica_que_los_producto_este_ordenado_de_menor_a_mayor() {
        List<Producto> productosOrdenado = new ArrayList<>();
        productosOrdenado.add(this.producto2);
        productosOrdenado.add(this.producto1);
        Arrays.sort(productosOrdenado.toArray());

        Assert.assertEquals(productosOrdenado.get(0).getIdProducto(),this.productosOrdenadosDeMenorAMayor.get(0).getIdProducto());
    }

    private Producto newProducto1() {
         Producto newProducto = new Producto();
         ImagenProducto imagen1 = new ImagenProducto();
         ImagenProducto imagen2 = new ImagenProducto();

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
        newProducto.setCategoria(Celulares);
        newProducto.setImagen(imagen1);
        newProducto.setImagen(imagen2);
        newProducto.setPrecio(7000.0);
        newProducto.setStock(1);
        return newProducto;
    }

    private Producto newProducto2() {
        Producto newProducto = new Producto();
        ImagenProducto imagen1 = new ImagenProducto();
        ImagenProducto imagen2 = new ImagenProducto();

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
        newProducto.setCategoria(Celulares);
        newProducto.setImagen(imagen1);
        newProducto.setImagen(imagen2);
        newProducto.setPrecio(7000.0);
        newProducto.setStock(1);
        return newProducto;
    }


    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
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
    public static byte[] decompressBytes(byte[] data) {
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
